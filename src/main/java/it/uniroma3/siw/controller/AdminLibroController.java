package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.service.LibroService;

// AdminLibroController.java
@Controller
@RequestMapping("/admin")
public class AdminLibroController {

    @Autowired
    private LibroService libroService;

    // Lista libri per admin
    @GetMapping("/libri")
    public String adminLibri(Model model) {
        model.addAttribute("libri", libroService.getAllLibri());
        return "admin/libri";
    }

    // Dettagli libro per admin
    @GetMapping("/libro/{id}")
    public String adminDettaglioLibro(@PathVariable("id") Long id, Model model) {
        model.addAttribute("libro", libroService.getLibroById(id));
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
        libroEsistente.setAutori(libro.getAutori());
        libroEsistente.setTitolo(libro.getTitolo());
        libroEsistente.setAnno(libro.getAnno());

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
}
