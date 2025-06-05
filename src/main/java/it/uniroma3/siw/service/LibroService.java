package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public Libro getLibroById(Long id) {
        return libroRepository.findById(id).get();
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

}
