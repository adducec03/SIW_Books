package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;
import jakarta.transaction.Transactional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Optional<Utente> getUser(Long id) {
        return utenteRepository.findById(id);
    }

    public void saveUser(Utente user) {
        utenteRepository.save(user);
    }

    public boolean emailExists(String email) {
        return utenteRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public void salvaLibroPerUtente(Utente utente, Libro libro) {
        utente.salvaLibro(libro);
        this.utenteRepository.save(utente);
    }

    @Transactional
    public void rimuoviLibroSalvato(Utente utente, Libro libro) {
        utente.rimuoviLibro(libro);
        this.utenteRepository.save(utente);
    }

}
