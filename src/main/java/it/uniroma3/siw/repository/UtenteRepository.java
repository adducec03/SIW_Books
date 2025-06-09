package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Utente;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface UtenteRepository extends CrudRepository<Utente, Long> {

    public Optional<Utente> findByEmail(String email);
}
