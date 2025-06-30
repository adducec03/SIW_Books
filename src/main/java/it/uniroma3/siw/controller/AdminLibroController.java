package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Recensione;
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

    // Lista libri per admin
    @GetMapping("/libri")
    public String adminLibri(Model model) {
        model.addAttribute("libri", libroService.getAllLibri());
        return "admin/libri";
    }

    // Dettagli libro per admin
    @GetMapping("/libro/{id}")
    public String adminDettaglioLibro(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Libro libro = this.libroService.getLibroById(id);
        model.addAttribute("libro", libroService.getLibroById(id));
        List<Recensione> recensioni = recensioneService.findByLibroOrderByDataCreazioneDesc(libro);
        model.addAttribute("recensioni", recensioni);
        if (userDetails != null) {
            Credentials cred = credentialsService.getCredentials(userDetails.getUsername()).orElse(null);
            model.addAttribute("utente", cred.getUtente());
        }
        return "admin/libro";
    }

    // Form nuovo libro
    @GetMapping("/formNewLibro")
    public String formNuovoLibro(Model model) {
        model.addAttribute("libro", new Libro());
        return "admin/formNewLibro";
    }

    // Form modifica libro
    @GetMapping("/modificaLibro/{id}")
    public String formModificaLibro(@PathVariable("id") Long id, Model model) {
        Libro libro = libroService.getLibroById(id);
        model.addAttribute("libro", libro);
        return "admin/modificaLibro";
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
            @RequestParam(name = "newImages", required = false) List<String> newImages) {

        // 1. Carica il libro originale
        Libro libroEsistente = libroService.getLibroById(id);
        if (libroEsistente == null)
            return "redirect:/admin/libri"; // gestione fallback

        // 2. Aggiorna i campi base
        libroEsistente.setId(libro.getId());
        libroEsistente.setAutori(libro.getAutori());
        libroEsistente.setTitolo(libro.getTitolo());
        libroEsistente.setAnno(libro.getAnno());
        libroEsistente.setDescrizione(libro.getDescrizione());

        // 3. Gestione immagini
        List<String> immagini = existingImages != null ? new ArrayList<>(existingImages) : new ArrayList<>();

        if (removeIndexes != null) {
            // Ordina desc per evitare problemi con rimozione per indice
            removeIndexes.sort(Collections.reverseOrder());
            for (Integer i : removeIndexes) {
                if (i >= 0 && i < immagini.size()) {
                    immagini.remove(i.intValue());
                }
            }
        }

        if (newImages != null) {
            for (String url : newImages) {
                if (url != null && !url.trim().isEmpty()) {
                    immagini.add(url.trim());
                }
            }
        }

        libroEsistente.setUrlImmagini(immagini);

        // 4. Salva
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
}
