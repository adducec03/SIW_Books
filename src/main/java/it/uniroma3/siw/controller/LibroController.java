package it.uniroma3.siw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String getLibro(@PathVariable("id") Long id, Model model) {
        Libro libro = this.libroService.getLibroById(id);
        model.addAttribute("libro", libro);
        List<Recensione> recensioni = recensioneService.findByLibroOrderByDataCreazioneDesc(libro);
        model.addAttribute("recensioni", recensioni);
        return "libro.html";
    }

    @GetMapping("/formNewLibro")
    public String formNewLibro(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("autori", autoreService.getAllAutori());
        return "formNewLibro.html";
    }

    @PostMapping("/libro")
    public String newLibro(@Valid @ModelAttribute("libro") Libro libro, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) { // sono emersi errori nel binding
            model.addAttribute("autori", autoreService.getAllAutori());
            return "formNewLibro.html";
        } else { // NON sono emersi errori nel binding
            this.libroService.save(libro);
            model.addAttribute("libro", libro);
            return "redirect:libro/" + libro.getId();
        }
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
            Model model, @AuthenticationPrincipal UserDetails userDetails) {

        List<Libro> libri;

        if ((titolo != null && !titolo.isBlank()) ||
                (autore != null && !autore.isBlank()) ||
                anno != null || (genere != null && !genere.isBlank())) {
            libri = libroService.findByFiltri(titolo, autore, anno, genere);
        } else {
            libri = (List<Libro>) libroService.getAllLibri();
        }

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

        if (userDetails != null) {
            Credentials credentials = credentialsService.getCredentials(userDetails.getUsername()).orElse(null);
            if ("ADMIN".equals(credentials.getRuolo())) {
                return "admin/libri.html"; 
            }
        }
        return "libri.html";

    }

    @GetMapping("/libri/salvati")
    public String mostraLibriPiuSalvati(Model model) {
        aggiungiMedieEVoti(model, libroService.trovaLibriPiuSalvati());
        return "libri";
    }

    @GetMapping("/libri/piu-votati")
    public String mostraLibriPiuVotati(Model model) {
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
    public String salvaLibro(@PathVariable("id") Long idLibro, RedirectAttributes redirectAttributes) {
        Libro libro = this.libroService.getLibroById(idLibro);
        Utente utente = this.credentialsService.getUtenteCorrente();

        if (libro != null && utente != null) {
            this.utenteService.salvaLibroPerUtente(utente, libro);
            redirectAttributes.addFlashAttribute("success", "Libro salvato!");
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
