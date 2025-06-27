package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredentialsService;

@RequestMapping("/areaPersonale")
@Controller
public class AreaPersonaleController {
    @Autowired
    private CredentialsService credenzialiService;

    @GetMapping
    public String areaPersonale(Model model) {
        Utente utente = credenzialiService.getUtenteCorrente();

        model.addAttribute("utente", utente);
        model.addAttribute("libriSalvati", utente.getLibriSalvati());
        model.addAttribute("recensioni", utente.getRecensioni());

        return "areaPersonale"; // → areaPersonale.html
    }
}
