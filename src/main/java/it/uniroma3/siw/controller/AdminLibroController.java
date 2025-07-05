package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.LibroService;
import it.uniroma3.siw.service.RecensioneService;

// AdminLibroController.java
@Controller
@RequestMapping("/admin")
public class AdminLibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private RecensioneService recensioneService;

    @Autowired
    private AutoreService autoreService;

    // Lista libri per admin
    /*
     * @GetMapping("/libri")
     * public String adminLibri(Model model) {
     * model.addAttribute("libri", libroService.getAllLibri());
     * return "admin/libri";
     * }
     */

    // Dettagli libro per admin
    @GetMapping("/libro/{id}")
    public String adminDettaglioLibro(@PathVariable("id") Long id, Model model,
            @AuthenticationPrincipal UserDetails userDetails) {
        Libro libro = this.libroService.getLibroById(id);
        model.addAttribute("libro", libroService.getLibroById(id));
        List<Recensione> recensioni = recensioneService.findByLibroOrderByDataCreazioneDesc(libro);
        model.addAttribute("recensioni", recensioni);
        if (userDetails != null) {
            Credentials cred = credentialsService.getCredentials(userDetails.getUsername()).orElse(null);
            model.addAttribute("utente", cred.getUtente());
        }
        Double media = libroService.calcolaMediaVoti(libro);
        model.addAttribute("mediaVoto", media);
        return "admin/libro";
    }

    // Form nuovo libro
    @GetMapping("/formNewLibro")
    public String formNuovoLibro(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("autori", autoreService.getAllAutori());
        return "admin/formNewLibro";
    }

    // Form modifica libro
    @GetMapping("/modificaLibro/{id}")
    public String formModificaLibro(@PathVariable("id") Long id, Model model) {
        Libro libro = libroService.getLibroById(id);
        model.addAttribute("libro", libro);
        model.addAttribute("autori", autoreService.getAllAutori());
        return "admin/modificaLibro.html";
    }

    // Salvataggio (new o modificato)
    @PostMapping("/libro")
    public String salvaLibro(@ModelAttribute("libro") Libro libro) {
        libroService.save(libro);
        return "redirect:/admin/libri";
    }

    @PostMapping("/libro/{id}")
    public String aggiornaLibro(@PathVariable Long id,
            @ModelAttribute("libro") Libro libro,
            @RequestParam(name = "existingImages", required = false) List<String> existingImages,
            @RequestParam(name = "removeIndexes", required = false) List<Integer> removeIndexes,
            @RequestParam(name = "immagini", required = false) List<MultipartFile> nuoveImmagini) throws IOException {

        Libro libroEsistente = libroService.getLibroById(id);
        if (libroEsistente == null)
            return "redirect:/admin/libri";

        // aggiorna dati base
        libroEsistente.setTitolo(libro.getTitolo());
        libroEsistente.setGenere(libro.getGenere());
        libroEsistente.setAnno(libro.getAnno());
        libroEsistente.setDescrizione(libro.getDescrizione());
        libroEsistente.setAutori(libro.getAutori());

        // GESTIONE AGGIUNT/RIMOZIONE AUTORI
        // immagini esistenti
        List<String> immagini = existingImages != null ? new ArrayList<>(existingImages) : new ArrayList<>();

        // rimozione immagini
        if (removeIndexes != null) {
            removeIndexes.sort(Collections.reverseOrder());
            for (Integer i : removeIndexes) {
                if (i >= 0 && i < immagini.size()) {
                    immagini.remove(i.intValue());
                }
            }
        }

        // Caricamento nuove immagini
        if (nuoveImmagini != null && !nuoveImmagini.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/uploads/copertine"; // cartella relativa alla
                                                                                      // directory del progetto
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                try {
                    Files.createDirectories(uploadPath);
                } catch (IOException e) {
                    throw new RuntimeException("Impossibile creare la cartella di upload: " + uploadPath, e);
                }
            }

            for (MultipartFile file : nuoveImmagini) {
                if (!file.isEmpty()) {
                    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                    Path filePath = uploadPath.resolve(fileName);

                    try {
                        file.transferTo(filePath.toFile()); // salva nel file system
                        immagini.add("/uploads/copertine/" + fileName); // path da usare nella visualizzazione
                    } catch (IOException e) {
                        throw new RuntimeException("Errore nel salvataggio del file: " + fileName, e);
                    }
                }
            }
        }

        libroEsistente.setPercorsiImmagini(immagini);

        libroEsistente.setAutori(libro.getAutori());

        libroService.save(libroEsistente);
        return "redirect:/admin/libro/" + id;
    }

    @GetMapping("/libri/salvati")
    public String mostraLibriPiuSalvati(Model model) {
        aggiungiMedieEVoti(model, libroService.trovaLibriPiuSalvati());
        model.addAttribute("filtro", "salvati");

        return "admin/libri";
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
        model.addAttribute("filtro", "votati");

        return "admin/libri";
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

    @PostMapping("/libro/{id}/delete")
    public String deleteLibro(@PathVariable Long id) {
        Libro libro = libroService.getLibroById(id);
        if (libro != null) {
            libroService.deleteById(id);
        }
        return "redirect:/admin/libri";
    }

    @PostMapping("/recensione/{id}/delete")
    public String eliminaRecensione(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Recensione recensione = recensioneService.findById(id);
        if (recensione != null) {
            Long idLibro = recensione.getLibro().getId();
            recensioneService.delete(recensione);
            redirectAttributes.addFlashAttribute("success", "Recensione eliminata con successo!");
            return "redirect:/admin/libro/" + idLibro;
        }
        return "redirect:/";
    }
}
