package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.CredentialsRepository;

@Service
public class CredentialsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialsRepository credentialsRepository;

    public Credentials getCredentials(Long id) {
        return credentialsRepository.findById(id).orElse(null);
    }

    public Optional<Credentials> getCredentials(String username) {
        return credentialsRepository.findByUsername(username);
    }

    public Credentials saveCredentials(Credentials credentials) {
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        return credentialsRepository.save(credentials);
    }

    public boolean usernameExists(String username) {
        return credentialsRepository.findByUsername(username).isPresent();
    }

    public Credentials getCredentialsUtente(Utente utente) {
        return credentialsRepository.findByUtente(utente)
                .orElseThrow(() -> new UsernameNotFoundException("Credenziali non trovate per l’utente"));
    }

    public Utente getUtenteCorrente() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;

        if (principal instanceof UserDetails) {
            // Utente autenticato con login classico
            username = ((UserDetails) principal).getUsername();
        } else if (principal instanceof OAuth2User) {
            // Utente autenticato con OAuth
            OAuth2User oauthUser = (OAuth2User) principal;

            // Adatta questo campo a come salvi lo username (es: "email" o "login")
            username = (String) oauthUser.getAttribute("email");
            if (username == null) {
                username = (String) oauthUser.getAttribute("login"); // fallback per GitHub
            }
        }

        if (username != null) {
            Credentials credentials = this.credentialsRepository.findByUsername(username).orElse(null);
            if (credentials != null)
                return credentials.getUtente();
        }

        return null;
    }

}
