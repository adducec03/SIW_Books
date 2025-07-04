package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;

import java.security.Principal;

@Controller
public class RecensioneController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private RecensioneService recensioneService;

    @Autowired
    private CredentialsService credentialsService;

    @GetMapping("/libro/{id}/recensioni")
    public String recensioniLibro(@PathVariable("id") Long id, Model model) {
        Libro libro = libroService.getLibroById(id);
        model.addAttribute("libro", libro);
        model.addAttribute("recensioni", recensioneService.findByLibro(id));
        model.addAttribute("recensione", new Recensione());
        return "recensioni";
    }

    @PostMapping("/libro/{id}/recensioni")
    @PreAuthorize("isAuthenticated()")
    public String aggiungiRecensione(@PathVariable("id") Long id,
            @ModelAttribute("recensione") Recensione recensione,
            Principal principal) {

        // Prende lo username dell’utente loggato
        String username = principal.getName();

        // Recupera le credenziali e poi l’utente associato
        Credentials credentials = this.credentialsService.getCredentials(username)
                .orElseThrow(() -> new RuntimeException("Credenziali non trovate"));

        Utente utente = credentials.getUtente();

        recensione.setLibro(libroService.getLibroById(id));
        recensione.setUtente(utente);
        recensioneService.salva(recensione,utente);

        return "redirect:/libro/" + id + "/recensioni";
    }

    @GetMapping("/recensioni/nuova")
    public String mostraFormRecensione(@RequestParam("libroId") Long libroId, Model model, Principal principal) {
        Libro libro = libroService.getLibroById(libroId);
        Recensione recensione = new Recensione();
        recensione.setLibro(libro);
        
        Credentials credentials = credentialsService.getCredentials(principal.getName()).orElseThrow(() -> new RuntimeException("Credenziali non trovate"));
        recensione.setUtente(credentials.getUtente());

        model.addAttribute("recensione", recensione);
        return "formNewRecensione";
    }

    @PostMapping("/recensioni")
    public String salvaRecensione(@ModelAttribute("recensione") Recensione recensione, Principal principal) {
        Credentials credentials = credentialsService.getCredentials(principal.getName()).orElseThrow(() -> new RuntimeException("Credenziali non trovate"));
        Utente utente= credentials.getUtente();

        recensioneService.salva(recensione,utente);

        if(credentials.getRuolo().equals("ADMIN")){
            return "redirect:/admin/libro/" + recensione.getLibro().getId();
        }

        return "redirect:/libro/" + recensione.getLibro().getId();
    }
}