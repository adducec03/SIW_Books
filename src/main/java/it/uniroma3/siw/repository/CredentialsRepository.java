package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Utente;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {

    public Optional<Credentials> findByUsername(String username);

    @Query("SELECT c FROM Credentials c WHERE c.utente = :utente")
    Optional<Credentials> findByUtente(@Param("utente") Utente utente);
}
