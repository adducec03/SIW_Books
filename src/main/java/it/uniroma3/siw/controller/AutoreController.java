package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import it.uniroma3.siw.service.AutoreService;

@Controller
public class AutoreController {

    @Autowired
    AutoreService autoreService;
    
    @GetMapping("/autore/{id}")
    public String getAutore(@PathVariable("id") Long id, Model model) {
        model.addAttribute("autore", this.autoreService.getAutoreById(id));
        return "autore.html";
    }
    @GetMapping("/autori")
    public String showAutori(Model model) {
        model.addAttribute("autori", this.autoreService.getAllAutori());
        return "autori.html";
    }
}
