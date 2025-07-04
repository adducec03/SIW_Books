package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Autore;

public interface AutoreRepository extends CrudRepository<Autore, Long> {

    @Query("SELECT a FROM Autore a WHERE " +
            "(:nome IS NULL OR LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%')) " +
            "OR LOWER(a.cognome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:nazionalita IS NULL OR LOWER(a.nazionalita) LIKE LOWER(CONCAT('%', :nazionalita, '%')))")
    List<Autore> findByFiltri(
            @Param("nome") String nome,
            @Param("nazionalita") String nazionalita);

    List<Autore> findAllByOrderByIdAsc();
}
