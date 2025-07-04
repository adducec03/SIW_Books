package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    public String showAutori(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cognome,
            @RequestParam(required = false) String nazionalita,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails,
            Principal principal) {

        List<Autore> autori;

        boolean hasFiltri = (nome != null && !nome.isBlank()) ||
                (nazionalita != null && !nazionalita.isBlank());

        if (hasFiltri) {
            autori = autoreService.findByFiltri(nome, cognome, nazionalita); // cognome ignorato
        } else {
            autori = autoreService.getAllAutoriOrdinati();
        }

        model.addAttribute("autori", autori);
        model.addAttribute("nomeUtente", userDetails);

        // Logica utente/admin (riutilizza esattamente come nel controller dei libri)
        if (principal instanceof OAuth2AuthenticationToken token) {
            Map<String, Object> attributes = token.getPrincipal().getAttributes();
            String username = (String) attributes.get("login");
            if (username == null) {
                username = (String) attributes.get("email");
            }

            Optional<Credentials> credentials = credentialsService.getCredentials(username);
            model.addAttribute("nomeUtente", credentials.map(c -> c.getUtente().getNome()).orElse(username));
        } else if (principal != null) {
            String username = principal.getName();
            Optional<Credentials> optional = credentialsService.getCredentials(username);
            model.addAttribute("nomeUtente", optional.map(c -> c.getUtente().getNome()).orElse(username));
        }

        if (userDetails != null) {
            Credentials credentials = credentialsService.getCredentials(userDetails.getUsername()).orElse(null);
            if (credentials != null && "ADMIN".equals(credentials.getRuolo())) {
                return "admin/autori"; // Pagina HTML per admin
            }
        }

        return "autori"; // Pagina HTML per utenti normali
    }

    @PostMapping("/autore")
    public String addAutore(@Valid @ModelAttribute("autore") Autore autore,
            BindingResult bindingResult,
            @RequestParam("immagine") MultipartFile immagine,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            return "formNewAutore.html";
        }

        String uploadDir = "uploads/autori";

        if (!immagine.isEmpty()) {
            try {
                String fileName = UUID.randomUUID() + "_" + immagine.getOriginalFilename();
                Path path = Paths.get(uploadDir + fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, immagine.getBytes());
                autore.setPercorsoImmagine("/uploads/autori" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        autoreService.save(autore);

        return "redirect:/admin/autore/" + autore.getId();
    }

    @GetMapping("/formNewAutore")
    public String formNewAutore(Model model) {
        model.addAttribute("autore", new Autore());
        return "formNewAutore.html";
    }

}
