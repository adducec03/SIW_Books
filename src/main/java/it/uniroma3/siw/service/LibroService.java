package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public Libro getLibroById(Long id) {
        return libroRepository.findById(id).get();
    }

    public List<Libro> getLibriOrdinatiPerId() {
        return libroRepository.findAllByOrderByIdDesc();
    }

    public Iterable<Libro> getAllLibri() {
        return libroRepository.findAll();
    }

    public void save(Libro libro) {
        libroRepository.save(libro);
    }

    public Object findByAnno(Integer anno) {
        return libroRepository.findByAnno(anno);
    }

    public void deleteById(Long Id) {
        libroRepository.deleteById(Id);
    }

    public List<Libro> findByFiltri(String titolo, String autore, Integer anno, String genere) {
        // Filtro per titolo (prioritario)
        if (titolo != null && !titolo.isBlank()) {
            return libroRepository.findByTitoloContainingIgnoreCase(titolo);
        }

        // Filtro completo: autore + anno + genere
        if (autore != null && !autore.isBlank() && anno != null && genere != null && !genere.isBlank()) {
            String[] parts = autore.split(" ");
            if (parts.length >= 2) {
                return libroRepository.findByAutoreNomeCognomeAnnoGenere(parts[0], parts[1], anno, genere);
            }
        }

        // Autore + anno
        if (autore != null && !autore.isBlank() && anno != null) {
            String[] parts = autore.split(" ");
            if (parts.length >= 2) {
                return libroRepository.findByAutoreNomeCognomeEAnno(parts[0], parts[1], anno);
            }
        }

        // Autore + genere
        if (autore != null && !autore.isBlank() && genere != null && !genere.isBlank()) {
            String[] parts = autore.split(" ");
            if (parts.length >= 2) {
                return libroRepository.findByAutoreNomeCognomeGenere(parts[0], parts[1], genere);
            }
        }

        // Solo autore
        if (autore != null && !autore.isBlank()) {
            String[] parts = autore.split(" ");
            if (parts.length >= 2) {
                return libroRepository.findByAutoreNomeECognome(parts[0], parts[1]);
            }
        }

        // Solo anno + genere
        if (anno != null && genere != null && !genere.isBlank()) {
            return libroRepository.findByAnnoAndGenereIgnoreCase(anno, genere);
        }

        // Solo anno
        if (anno != null) {
            return libroRepository.findByAnno(anno);
        }

        // Solo genere
        if (genere != null && !genere.isBlank()) {
            return libroRepository.findByGenereIgnoreCase(genere);
        }

        return new ArrayList<>();
    }

    public List<String> trovaTuttiIGeneri() {
        return libroRepository.findDistinctGeneri();
    }

    public List<Libro> trovaLibriPiuSalvati() {
        return libroRepository.findLibriPiuSalvati();
    }

    public List<Libro> trovaLibriPiuRecenti() {
        return libroRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<Libro> trovaLibriPiuVotati() {
        List<Libro> all = libroRepository.findAll();
        return all.stream()
                .sorted(Comparator.comparing(
                        libro -> {
                            Double media = calcolaMediaVoti(libro);
                            return media != null ? -media : 0.0;
                        }))
                .collect(Collectors.toList());
    }

    public Double calcolaMediaVoti(Libro libro) {
        List<Recensione> recensioni = libro.getRecensioni();
        if (recensioni == null || recensioni.isEmpty()) {
            return 0.0; // Imposta a zero per i libri senza recensioni
        }
        return recensioni.stream()
                .filter(r -> r.getVoto() != null)
                .mapToDouble(Recensione::getVoto)
                .average()
                .orElse(0.0);
    }
}
