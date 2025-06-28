package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.service.AutoreService;
import jakarta.validation.Valid;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/autore")
public class AdminAutoreController {

    @Autowired
    private AutoreService autoreService;

    // Mostra dettaglio autore
    @GetMapping("/{id}")
    public String getAutore(@PathVariable("id") Long id, Model model) {
        Autore autore = autoreService.getAutoreById(id);
        if (autore == null) {
            return "redirect:/admin/autori";
        }
        model.addAttribute("autore", autore);
        return "admin/autore";
    }

    // Mostra form modifica
    @GetMapping("/{id}/modifica")
    public String mostraFormModifica(@PathVariable("id") Long id, Model model) {
        Autore autore = autoreService.getAutoreById(id);
        if (autore == null) {
            return "redirect:/admin/autori";
        }
        model.addAttribute("autore", autore);
        return "admin/modificaAutore";
    }

    // Gestione submit modifica autore
    @PostMapping("/{id}/modifica")
    public String modificaAutore(@PathVariable("id") Long id,
            @RequestParam String nome,
            @RequestParam(required = false) String cognome,
            @RequestParam String nazionalita,
            @RequestParam String dataNascita,
            @RequestParam(required = false) String dataMorte,
            @RequestParam(required = false) String urlImmagine,
            @RequestParam(required = false) String descrizione,
            Model model) {

        Autore autore = autoreService.getAutoreById(id);
        if (autore == null) {
            return "redirect:/admin/autori";
        }

        // Parsing delle date
        LocalDate nascita = LocalDate.parse(dataNascita);
        LocalDate morte = null;
        if (dataMorte != null && !dataMorte.trim().isEmpty()) {
            morte = LocalDate.parse(dataMorte);

            if (morte.isBefore(nascita)) {
                model.addAttribute("autore", autore);
                model.addAttribute("dataError", "La data di morte non può essere precedente alla data di nascita.");
                return "admin/modificaAutore";
            }
        }

        // Aggiornamento autore
        autore.setNome(nome);
        autore.setCognome(cognome);
        autore.setNazionalita(nazionalita);
        autore.setDataNascita(nascita);
        autore.setDataMorte(morte);
        autore.setUrlImmagine(urlImmagine);
        autore.setDescrizione(descrizione);

        autoreService.save(autore);
        return "redirect:/admin/autore/" + id;
    }

    @GetMapping("/formNewAutore")
    public String mostraFormNuovoAutore(Model model) {
        model.addAttribute("autore", new Autore());
        return "admin/formNewAutore";
    }

    // Gestisce il submit del nuovo autore
    @PostMapping("/autore")
    public String aggiungiAutore(@Valid @ModelAttribute("autore") Autore autore,
            BindingResult bindingResult,
            Model model) {
        // Controllo logico: la data di morte non deve essere prima della nascita
        if (autore.getDataMorte() != null && autore.getDataMorte().isBefore(autore.getDataNascita())) {
            bindingResult.rejectValue("dataMorte", "invalid", "La data di morte non può precedere quella di nascita.");
        }

        if (bindingResult.hasErrors()) {
            return "admin/formNewAutore";
        }

        autoreService.save(autore);
        return "redirect:/admin/autori";
    }
}
