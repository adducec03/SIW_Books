package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.validator.AutoreValidator;
import jakarta.validation.Valid;

@Controller
public class AutoreController {

    @Autowired
    AutoreService autoreService;

    @Autowired
    CredentialsService credentialsService;

    @Autowired
    private AutoreValidator autoreValidator;

    @InitBinder("autore")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(autoreValidator);
    }

    @GetMapping("/autore/{id}")
    public String getAutore(@PathVariable("id") Long id, Model model) {
        model.addAttribute("autore", this.autoreService.getAutoreById(id));
        return "autore.html";
    }

    @GetMapping("/autori")
    public String showAutori(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("autori", this.autoreService.getAllAutori());
        if (userDetails != null) {
            Credentials credentials = credentialsService.getCredentials(userDetails.getUsername()).orElse(null);
            if ("ADMIN".equals(credentials.getRuolo())) {
                return "admin/autori.html";
            }
        }
        return "autori.html";
    }

    @PostMapping("/autori")
    public String addAutore(@ModelAttribute("autore") Autore autore, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "formNewAutore.html";
        }
        autoreService.save(autore);
        model.addAttribute("autore", autore);
        return "redirect:/autore/" + autore.getId();
    }

    @GetMapping("/formNewAutore")
    public String formNewAutore(Model model) {
        model.addAttribute("autore", new Autore());
        return "formNewAutore.html";
    }

    @PostMapping("/autore")
    public String newAutore(@Valid @ModelAttribute("autore") Autore autore, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) { // sono emersi errori nel binding
            return "formNewAutore.html";
        } else { // NON sono emersi errori nel binding
            this.autoreService.save(autore);
            model.addAttribute("autore", autore);
            return "redirect:autore/" + autore.getId();
        }
    }
}
