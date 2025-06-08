package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Libro;

public interface LibroRepository extends CrudRepository<Libro, Long> {
    public List<Libro> findByAnno(Integer anno);

    public boolean existsByTitoloAndAnno(String titolo, Integer anno);

    @Query("SELECT l FROM Libro l JOIN l.autori a WHERE LOWER(l.titolo) LIKE LOWER(CONCAT('%', :titolo, '%'))")
    List<Libro> findByTitoloContainingIgnoreCase(@Param("titolo") String titolo);

    @Query("SELECT l FROM Libro l JOIN l.autori a WHERE LOWER(a.nome) = LOWER(:nome) AND LOWER(a.cognome) = LOWER(:cognome)")
    List<Libro> findByAutoreNomeECognome(@Param("nome") String nome, @Param("cognome") String cognome);

    @Query("SELECT l FROM Libro l JOIN l.autori a WHERE LOWER(a.nome) = LOWER(:nome) AND LOWER(a.cognome) = LOWER(:cognome) AND l.anno = :anno")
    List<Libro> findByAutoreNomeCognomeEAnno(@Param("nome") String nome, @Param("cognome") String cognome,
            @Param("anno") Integer anno);

}
