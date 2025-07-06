package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;
import jakarta.validation.Valid;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

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
    public String aggiungiRecensione(@Valid @ModelAttribute("recensione") Recensione recensione,
            BindingResult bindingResult,
            @PathVariable("id") Long id,
            Principal principal,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {

        // Recupera informazioni utente loggato (sia standard che OAuth2)
        String nomeUtente = null;
        Utente utente = null;

        if (userDetails != null) {
            Optional<Credentials> optional = credentialsService.getCredentials(userDetails.getUsername());
            if (optional.isPresent()) {
                utente = optional.get().getUtente();
                nomeUtente = utente.getNome();
            }
        } else if (principal instanceof OAuth2AuthenticationToken token) {
            Map<String, Object> attributes = token.getPrincipal().getAttributes();
            String username = (String) attributes.get("login");
            if (username == null)
                username = (String) attributes.get("email");

            Optional<Credentials> optional = credentialsService.getCredentials(username);
            if (optional.isPresent()) {
                utente = optional.get().getUtente();
                nomeUtente = utente.getNome();
            } else {
                nomeUtente = username;
            }
        }

        // Inserisce l'utente e il nome nel model per ricaricare correttamente la navbar
        model.addAttribute("nomeUtente", nomeUtente);
        model.addAttribute("utente", utente);
        model.addAttribute("libro", libroService.getLibroById(id)); // importante se la view lo usa

        if (bindingResult.hasErrors()) {
            return "formNewRecensione";
        }

        recensione.setLibro(libroService.getLibroById(id));
        recensione.setUtente(utente);
        recensioneService.salva(recensione, utente);

        return "redirect:/libro/" + id + "/recensioni";
    }

    @GetMapping("/recensioni/nuova")
    public String mostraFormRecensione(@RequestParam("libroId") Long libroId, Model model, Principal principal,
            @AuthenticationPrincipal UserDetails userDetails) {
        Libro libro = libroService.getLibroById(libroId);
        Recensione recensione = new Recensione();
        recensione.setLibro(libro);

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

        model.addAttribute("recensione", recensione);
        return "formNewRecensione";
    }

    @PostMapping("/recensioni")
    public String salvaRecensione(@Valid @ModelAttribute("recensione") Recensione recensione,
            BindingResult bindingResult, Principal principal, @AuthenticationPrincipal UserDetails userDetails,
            Model model) {
        Credentials credentials = credentialsService.getCredentials(principal.getName())
                .orElseThrow(() -> new RuntimeException("Credenziali non trovate"));

        String nomeUtente = null;
        Utente utente = null;

        if (userDetails != null) {
            Optional<Credentials> optional = credentialsService.getCredentials(userDetails.getUsername());
            if (optional.isPresent()) {
                utente = optional.get().getUtente();
                nomeUtente = utente.getNome();
            }
        } else if (principal instanceof OAuth2AuthenticationToken token) {
            Map<String, Object> attributes = token.getPrincipal().getAttributes();
            String username = (String) attributes.get("login");
            if (username == null)
                username = (String) attributes.get("email");

            Optional<Credentials> optional = credentialsService.getCredentials(username);
            if (optional.isPresent()) {
                utente = optional.get().getUtente();
                nomeUtente = utente.getNome();
            } else {
                nomeUtente = username;
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("nomeUtente", nomeUtente);
            model.addAttribute("utente", utente);
            model.addAttribute("libro", recensione.getLibro());
            return "formNewRecensione"; // torna al form con gli errori
        }

        recensioneService.salva(recensione, utente);

        if (credentials.getRuolo().equals("ADMIN")) {
            return "redirect:/admin/libro/" + recensione.getLibro().getId();
        }

        return "redirect:/libro/" + recensione.getLibro().getId();
    }
}