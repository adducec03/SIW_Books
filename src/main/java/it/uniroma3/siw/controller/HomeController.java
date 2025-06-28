package it.uniroma3.siw.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.LibroService;
import it.uniroma3.siw.service.RecensioneService;

@Controller
public class HomeController {

    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private LibroService libroService;
    @Autowired
    private RecensioneService recensioneService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal instanceof OAuth2AuthenticationToken token) {
            // Estrai attributi da GitHub o Google
            Map<String, Object> attributes = token.getPrincipal().getAttributes();
            String username = (String) attributes.get("login"); // GitHub
            if (username == null) {
                username = (String) attributes.get("email"); // Google
            }

            Optional<Credentials> optional = credentialsService.getCredentials(username);
            if (optional.isPresent()) {
                model.addAttribute("nomeUtente", optional.get().getUtente().getNome());
            } else {
                model.addAttribute("nomeUtente", username); // fallback
            }

        } else if (principal != null) {
            // Login classico con username
            String username = principal.getName();
            Optional<Credentials> optional = credentialsService.getCredentials(username);
            if (optional.isPresent()) {
                model.addAttribute("nomeUtente", optional.get().getUtente().getNome());
            } else {
                model.addAttribute("nomeUtente", username); // fallback
            }
        }

        List<String> generi = libroService.trovaTuttiIGeneri(); // metodo da creare
        model.addAttribute("generi", generi);

        List<Recensione> recensioniRecenti = recensioneService.getRecensioniRecenti();
        model.addAttribute("recensioniRecenti", recensioniRecenti);

        return "indexProva";
    }
}
