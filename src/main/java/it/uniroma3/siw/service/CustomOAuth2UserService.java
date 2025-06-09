package it.uniroma3.siw.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.CredentialsRepository;
import jakarta.transaction.Transactional;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oauthUser = delegate.loadUser(userRequest);

        Map<String, Object> attributes = oauthUser.getAttributes();
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String username = null;
        String nome = null;
        String cognome = null;
        String email = null;

        if ("github".equalsIgnoreCase(registrationId)) {
            username = (String) attributes.get("login"); // GitHub username
            email = (String) attributes.get("email"); // GitHub email
            String fullName = (String) attributes.get("name");
            if (fullName != null && !fullName.isBlank()) {
                String[] parts = fullName.trim().split(" ", 2);
                nome = parts[0];
                cognome = (parts.length > 1) ? parts[1] : "GitHub";
            } else {
                nome = "GitHub";
                cognome = "User";
            }
        } else if ("google".equalsIgnoreCase(registrationId)) {
            email = (String) attributes.get("email"); // Google email
            username = email; // usiamo l'email come username
            nome = (String) attributes.get("given_name");
            cognome = (String) attributes.get("family_name");
            if (nome == null)
                nome = "Google";
            if (cognome == null)
                cognome = "User";
        }

        if (username == null) {
            throw new OAuth2AuthenticationException("Username/email non trovati dal provider OAuth");
        }

        // Se le credenziali esistono già, ritorno direttamente
        Optional<Credentials> optional = credentialsRepository.findByUsername(username);
        if (optional.isPresent()) {
            return oauthUser;
        }

        // Creo nuovo utente
        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setEmail(email != null ? email : "no-email@oauth.local");

        // Creo nuove credenziali
        Credentials cred = new Credentials();
        cred.setUsername(username);
        cred.setPassword("oauth"); // Dummy password
        cred.setRuolo(Credentials.DEFAULT_ROLE);
        cred.setUtente(utente);

        credentialsRepository.save(cred);

        return oauthUser;
    }
}