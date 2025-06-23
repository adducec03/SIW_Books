package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.Utente;

public interface RecensioneRepository extends CrudRepository<Recensione, Long> {
    List<Recensione> findByLibroId(Long libroId);
    Optional<Recensione> findByUtenteAndLibro(Utente utente, Libro libro);
    List<Recensione> findByLibroOrderByDataCreazioneDesc(Libro libro);
    List<Recensione> findTop3ByOrderByDataCreazioneDesc();
}