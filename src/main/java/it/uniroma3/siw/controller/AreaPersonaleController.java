package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UtenteService;

@RequestMapping("/areaPersonale")
@Controller
public class AreaPersonaleController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String areaPersonale(Model model) {
        Utente utente = credentialsService.getUtenteCorrente();

        model.addAttribute("utente", utente);
        model.addAttribute("libriSalvati", utente.getLibriSalvati());
        model.addAttribute("recensioni", utente.getRecensioni());

        return "areaPersonale";
    }

    @GetMapping("/modifica")
    public String mostraFormModifica(Model model) {
        Utente utente = credentialsService.getUtenteCorrente();
        Credentials credentials = credentialsService.getCredentialsUtente(utente);

        boolean isOAuth = "oauth".equalsIgnoreCase(credentials.getProvider());
        
        model.addAttribute("isOAuth", isOAuth);
        model.addAttribute("utente", utente);
        model.addAttribute("credentials", credentials);

        return "modificaUtente";
    }

    @PostMapping("/modifica")
    public String modificaUtente(
            @RequestParam String nome,
            @RequestParam String cognome,
            @RequestParam(required = false) Integer telefono,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {

        Utente utente = credentialsService.getUtenteCorrente();
        Credentials credentials = credentialsService.getCredentialsUtente(utente);

        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setNumeroTelefonico(telefono);

        boolean isOAuth = credentials.getPassword() == null || credentials.getPassword().isBlank();

        if (!isOAuth) {
            utente.setEmail(email);
            credentials.setUsername(username);
            if (password != null && !password.isBlank()) {
                credentials.setPassword(passwordEncoder.encode(password));
            }
            credentialsService.saveCredentials(credentials);
        }

        utenteService.saveUser(utente);
        return "redirect:/areaPersonale";
    }
}
