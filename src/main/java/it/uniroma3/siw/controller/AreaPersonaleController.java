package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import it.uniroma3.siw.DTO.ModificaUtenteDTO;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UtenteService;
import jakarta.validation.Valid;

@RequestMapping("/areaPersonale")
@Controller
public class AreaPersonaleController {

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        Credentials cred = credentialsService.getCredentialsUtente(utente);

        ModificaUtenteDTO dto = new ModificaUtenteDTO();
        dto.setNome(utente.getNome());
        dto.setCognome(utente.getCognome());
        dto.setNumeroTelefonico(utente.getNumeroTelefonico());
        dto.setEmail(utente.getEmail());
        dto.setUsername(cred.getUsername());

        model.addAttribute("modificaUtenteDTO", dto);
        model.addAttribute("isOAuth", cred.getPassword() == null || cred.getPassword().isBlank());

        return "modificaUtente";
    }

    @PostMapping("/modifica")
    public String modificaUtente(
            @Valid @ModelAttribute("modificaUtenteDTO") ModificaUtenteDTO form,
            BindingResult bindingResult,
            Model model) {

        Utente utente = credentialsService.getUtenteCorrente();
        Credentials credentials = credentialsService.getCredentialsUtente(utente);

        boolean isOAuth = credentials.getPassword() == null || credentials.getPassword().isBlank();

        // Sempre necessari per la view
        model.addAttribute("isOAuth", isOAuth);
        model.addAttribute("modificaUtenteDTO", form);

        // Validazione: username già esistente (se diverso da quello attuale)
        if (!form.getUsername().equals(credentials.getUsername()) &&
                credentialsService.usernameExists(form.getUsername())) {
            bindingResult.rejectValue("username", "error.username", "Questo username è già in uso");
        }

        if (!form.getEmail().equals(utente.getEmail()) &&
                utenteService.emailGiaInUsoDaAltri(form.getEmail(), utente.getId())) {
            bindingResult.rejectValue("email", "error.email", "Questa email è già associata ad un altro account");
        }

        // In caso di errori (inclusi quelli appena aggiunti)
        if (bindingResult.hasErrors()) {
            return "modificaUtente";
        }

        // Aggiorna i dati personali
        utente.setNome(form.getNome());
        utente.setCognome(form.getCognome());
        utente.setNumeroTelefonico(form.getNumeroTelefonico());

        if (!isOAuth) {
            utente.setEmail(form.getEmail());
            credentials.setUsername(form.getUsername());

            boolean anyPasswordFieldFilled = form.getOldPassword() != null && !form.getOldPassword().isBlank() ||
                    form.getNewPassword() != null && !form.getNewPassword().isBlank() ||
                    form.getConfirmPassword() != null && !form.getConfirmPassword().isBlank();

            if (anyPasswordFieldFilled) {
                if (!passwordEncoder.matches(form.getOldPassword(), credentials.getPassword())) {
                    model.addAttribute("passwordError", "La vecchia password non è corretta");
                    return "modificaUtente";
                }

                if (!form.getNewPassword().equals(form.getConfirmPassword())) {
                    model.addAttribute("passwordError", "Le nuove password non coincidono");
                    return "modificaUtente";
                }

                credentials.setPassword(form.getNewPassword()); // verrà criptata nel service
            }

            credentialsService.saveCredentials(credentials);
        }

        utenteService.saveUser(utente);
        return "redirect:/areaPersonale";
    }
}
