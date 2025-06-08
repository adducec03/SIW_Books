package it.uniroma3.siw.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.service.CredentialsService;

@Controller
public class HomeController {

    @Autowired
    private CredentialsService credentialsService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal != null) {
            Credentials credentials = credentialsService.getCredentials(principal.getName());
            model.addAttribute("nomeUtente", credentials.getUtente().getNome());
        }
        return "index";
    }
}
