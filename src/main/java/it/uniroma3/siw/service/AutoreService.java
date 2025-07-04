package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.repository.AutoreRepository;
import it.uniroma3.siw.repository.LibroRepository;
import jakarta.transaction.Transactional;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private LibroRepository libroRepository;

    public Autore getAutoreById(Long id) {
        return autoreRepository.findById(id).orElse(null);
    }

    public List<Autore> getAllAutori() {
        return (List<Autore>) autoreRepository.findAll();
    }

    public void save(Autore autore) {
        autoreRepository.save(autore);
    }

    public Iterable<Autore> findAllById(List<Long> autori) {
        return autoreRepository.findAllById(autori);
    }

    public List<Autore> findByFiltri(String nome, String cognome, String nazionalita) {
        return autoreRepository.findByFiltri(nome, nazionalita);
    }

    public List<Autore> getAllAutoriOrdinati() {
        return autoreRepository.findAllByOrderByIdAsc(); // o findAllByOrderByIdAsc()
    }

    @Transactional
    public void eliminaAutoreECascadeLibri(Autore autore) {
        List<Libro> libri = libroRepository.findLibriByAutoreId(autore.getId());

        for (Libro libro : libri) {
            libroRepository.delete(libro);
        }

        autoreRepository.delete(autore);
    }
}
