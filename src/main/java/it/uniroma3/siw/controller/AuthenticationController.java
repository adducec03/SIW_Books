package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UtenteService;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private UtenteService utenteService;


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("credentials", new Credentials());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("credentials") Credentials credentials,
            BindingResult bindingResult,
            Model model) {

        if (utenteService.emailExists(credentials.getUtente().getEmail())) {
            bindingResult.rejectValue("utente.email", "duplicate", "Esiste già un utente con questa email.");
        }

        if (credentialsService.usernameExists(credentials.getUsername())) {
            bindingResult.rejectValue("username", "duplicate", "Username già in uso.");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        credentialsService.saveCredentials(credentials);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Questo deve corrispondere a login.html in /templates
    }
}