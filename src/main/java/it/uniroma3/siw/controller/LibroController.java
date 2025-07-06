package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.LibroService;
import it.uniroma3.siw.service.RecensioneService;
import it.uniroma3.siw.service.UtenteService;
import jakarta.validation.Valid;

@Controller
public class LibroController {

    @Autowired
    LibroService libroService;

    @Autowired
    AutoreService autoreService;

    @Autowired
    RecensioneService recensioneService;

    @Autowired
    UtenteService utenteService;

    @Autowired
    CredentialsService credentialsService;

    @GetMapping("/libro/{id}")
    public String getLibro(@PathVariable("id") Long id, Model model, Principal principal,
            @AuthenticationPrincipal UserDetails userDetails) {

        Libro libro = this.libroService.getLibroById(id);
        model.addAttribute("libro", libro);
        List<Recensione> recensioni = recensioneService.findByLibroOrderByDataCreazioneDesc(libro);
        model.addAttribute("recensioni", recensioni);

        if (userDetails != null) {
            Optional<Credentials> optional = credentialsService.getCredentials(userDetails.getUsername());
            if (optional.isPresent()) {
                Credentials credentials = optional.get();
                model.addAttribute("nomeUtente", credentials.getUtente().getNome());
                model.addAttribute("utente", credentials.getUtente());
            }
        } else if (principal instanceof OAuth2AuthenticationToken token) {
            Map<String, Object> attributes = token.getPrincipal().getAttributes();
            String username = (String) attributes.get("login");
            if (username == null) {
                username = (String) attributes.get("email");
            }

            Optional<Credentials> optional = credentialsService.getCredentials(username);
            if (optional.isPresent()) {
                Credentials credentials = optional.get();
                model.addAttribute("nomeUtente", credentials.getUtente().getNome());
                model.addAttribute("utente", credentials.getUtente());
            } else {
                model.addAttribute("nomeUtente", username);
            }
        }

        return "libro.html";
    }

    @PostMapping("/libro")
    public String newLibro(@Valid @ModelAttribute("libro") Libro libro,
            BindingResult bindingResult,
            @RequestParam("immagini") List<MultipartFile> immagini,
            @RequestParam(name = "autori", required = false) List<Long> idAutori,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("autori", autoreService.getAllAutori());
            return "admin/formNewLibro";
        }

        if (idAutori == null || idAutori.isEmpty()) {
            bindingResult.reject("libro.autori.obbligatori", "È necessario selezionare almeno un autore.");
            model.addAttribute("autoriError", "Seleziona almeno un autore.");
            model.addAttribute("autori", autoreService.getAllAutori());
            return "admin/formNewLibro";
        }

        String uploadDir = "uploads/copertine";

        for (MultipartFile file : immagini) {
            if (!file.isEmpty()) {
                try {
                    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                    Path path = Paths.get(uploadDir + fileName);
                    Files.createDirectories(path.getParent());
                    Files.write(path, file.getBytes());
                    libro.getPercorsiImmagini().add("/uploads/copertine/" + fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Associa autori
        List<Autore> autoriSelezionati = new ArrayList<>();
        for (Long id : idAutori) {
            Autore autore = autoreService.getAutoreById(id);
            if (autore != null) {
                autoriSelezionati.add(autore);
            }
        }
        libro.setAutori(autoriSelezionati);

        libroService.save(libro);

        return "redirect:/admin/libro/" + libro.getId();
    }

    @GetMapping("/formSearchLibri")
    public String formSearchLibri() {
        return "formSearchLibri.html";
    }

    @GetMapping("/libri")
    public String showLibri(@RequestParam(required = false) String titolo,
            @RequestParam(required = false) String autore,
            @RequestParam(required = false) Integer anno,
            @RequestParam(required = false) String genere,
            @RequestParam(required = false) String sort,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails, Principal principal) {

        List<Libro> libri;

        boolean hasFiltri = (titolo != null && !titolo.isBlank()) ||
                (autore != null && !autore.isBlank()) ||
                (genere != null && !genere.isBlank()) ||
                (anno != null);

        // 1. Gestione ordinamento
        if ("piu-votati".equalsIgnoreCase(sort)) {
            libri = libroService.trovaLibriPiuVotati();
        } else if ("salvati".equalsIgnoreCase(sort)) {
            libri = libroService.trovaLibriPiuSalvati();
        }
        // 2. Altrimenti applica i filtri se presenti
        else if (hasFiltri) {
            libri = libroService.findByFiltri(titolo, autore, anno, genere);
        }
        // 3. Nessun filtro, nessun ordinamento: mostra tutti
        else {
            libri = (List<Libro>) libroService.getLibriOrdinatiPerId();
        }

        // Calcola voti medi
        Map<Long, Double> medieVoti = new HashMap<>();
        Map<Long, Integer> stelle = new HashMap<>();

        for (Libro libro : libri) {
            Double media = libroService.calcolaMediaVoti(libro);
            medieVoti.put(libro.getId(), media);
            stelle.put(libro.getId(), (media != null) ? (int) Math.round(media) : 0);
        }

        // Aggiungi al model
        model.addAttribute("nomeUtente", userDetails);
        model.addAttribute("libri", libri);
        model.addAttribute("medieVoti", medieVoti);
        model.addAttribute("stelle", stelle);
        model.addAttribute("filtro", "recenti");

        if (principal instanceof OAuth2AuthenticationToken token) {
            // Estrai attributi da GitHub o Google
            Map<String, Object> attributes = token.getPrincipal().getAttributes();
            String username = (String) attributes.get("login"); // GitHub
            if (username == null) {
                username = (String) attributes.get("email"); // Google
            }

            Optional<Credentials> optional = credentialsService.getCredentials(username);
            if (optional.isPresent()) {
                model.addAttribute("nomeUtente", optional.get().getUtente().getNome());
            } else {
                model.addAttribute("nomeUtente", username); // fallback
            }

        } else if (principal != null) {
            // Login classico con username
            String username = principal.getName();
            Optional<Credentials> optional = credentialsService.getCredentials(username);
            if (optional.isPresent()) {
                model.addAttribute("nomeUtente", optional.get().getUtente().getNome());
            } else {
                model.addAttribute("nomeUtente", username); // fallback
            }
        }

        // Admin o utente normale
        if (userDetails != null) {
            Credentials credentials = credentialsService.getCredentials(userDetails.getUsername()).orElse(null);
            if ("ADMIN".equals(credentials.getRuolo())) {
                return "admin/libri";
            }
        }

        return "libri";
    }

    @GetMapping("/libri/frammento")
    public String getLibriByFiltro(@RequestParam String tipo, Model model) {
        List<Libro> libri;

        switch (tipo) {
            case "votati":
                libri = libroService.trovaLibriPiuVotati();
                break;
            case "salvati":
                libri = libroService.trovaLibriPiuSalvati();
                break;
            default:
                libri = (List<Libro>) libroService.getAllLibri();
        }

        aggiungiMedieEVoti(model, libri);
        return "fragments/libri :: booksGrid";
    }

    @GetMapping("/libri/salvati")
    public String mostraLibriPiuSalvati(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        aggiungiMedieEVoti(model, libroService.trovaLibriPiuSalvati());
        model.addAttribute("filtro", "salvati");

        if (userDetails != null) {
            Credentials credentials = credentialsService.getCredentials(userDetails.getUsername()).orElse(null);
            if ("ADMIN".equals(credentials.getRuolo())) {
                return "admin/libri";
            }
        }

        return "libri";
    }

    @GetMapping("/libri/piu-votati")
    public String mostraLibriPiuVotati(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<Libro> libri = libroService.trovaLibriPiuVotati();

        Map<Long, Double> medieVoti = new HashMap<>();
        Map<Long, Integer> stelle = new HashMap<>();

        for (Libro libro : libri) {
            Double media = libroService.calcolaMediaVoti(libro);
            medieVoti.put(libro.getId(), media);
            stelle.put(libro.getId(), (media != null) ? (int) Math.round(media) : 0);
        }

        model.addAttribute("libri", libri);
        model.addAttribute("medieVoti", medieVoti);
        model.addAttribute("stelle", stelle);
        model.addAttribute("filtro", "votati");

        if (userDetails != null) {
            Credentials credentials = credentialsService.getCredentials(userDetails.getUsername()).orElse(null);
            if ("ADMIN".equals(credentials.getRuolo())) {
                return "admin/libri";
            }
        }

        return "libri";
    }

    private void aggiungiMedieEVoti(Model model, List<Libro> libri) {
        Map<Long, Double> medieVoti = new HashMap<>();
        Map<Long, Integer> stelle = new HashMap<>();

        for (Libro libro : libri) {
            Double media = libroService.calcolaMediaVoti(libro);
            medieVoti.put(libro.getId(), media);
            stelle.put(libro.getId(), (media != null) ? (int) Math.round(media) : 0);
        }

        model.addAttribute("libri", libri);
        model.addAttribute("medieVoti", medieVoti);
        model.addAttribute("stelle", stelle);
    }

    @PostMapping("/libro/{id}/salva")
    public String salvaLibro(@PathVariable("id") Long idLibro, RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails userDetails) {
        Libro libro = this.libroService.getLibroById(idLibro);
        Utente utente = this.credentialsService.getUtenteCorrente();

        if (libro != null && utente != null) {
            this.utenteService.salvaLibroPerUtente(utente, libro);
            redirectAttributes.addFlashAttribute("success", "Libro salvato!");
        }

        if (userDetails != null) {
            Credentials credentials = credentialsService.getCredentials(userDetails.getUsername()).orElse(null);
            if ("ADMIN".equals(credentials.getRuolo())) {
                return "redirect:/admin/libro/" + idLibro;
            }
        }
        return "redirect:/libro/" + idLibro;
    }

    @PostMapping("/libro/{id}/unsave")
    public String unsaveLibro(@PathVariable("id") Long id) {
        Utente utente = credentialsService.getUtenteCorrente();
        Libro libro = libroService.getLibroById(id);

        utente.getLibriSalvati().remove(libro);
        utenteService.saveUser(utente); // salva la modifica

        return "redirect:/areaPersonale";
    }

}
