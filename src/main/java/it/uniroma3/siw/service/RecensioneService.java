package it.uniroma3.siw.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.RecensioneRepository;

@Service
public class RecensioneService {

    @Autowired
    private RecensioneRepository recensioneRepository;

    public List<Recensione> findByLibro(Long libroId) {
        return recensioneRepository.findByLibroId(libroId);
    }

    public Recensione salva(Recensione nuovaRecensione, Utente utente) {

        Optional<Recensione> esistente = recensioneRepository.findByUtenteAndLibro(utente, nuovaRecensione.getLibro());

        if (esistente.isPresent()) {
            Recensione r = esistente.get();
            r.setTitolo(nuovaRecensione.getTitolo());
            r.setDescrizione(nuovaRecensione.getDescrizione());
            r.setVoto(nuovaRecensione.getVoto());
            r.setDataCreazione(LocalDateTime.now());
            return recensioneRepository.save(r);
        } else {
            nuovaRecensione.setUtente(utente);
            nuovaRecensione.setDataCreazione(LocalDateTime.now());
            return recensioneRepository.save(nuovaRecensione);
        }

    }

    public Recensione findById(Long id){
        return recensioneRepository.findById(id).orElse(null);
    }

    public void delete(Recensione recensione){
        recensioneRepository.delete(recensione);
    }

    public List<Recensione> findByLibroOrderByDataCreazioneDesc(Libro libro) {
        return recensioneRepository.findByLibroOrderByDataCreazioneDesc(libro);
    }

    public List<Recensione> getRecensioniRecenti() {
        return recensioneRepository.findTop3ByOrderByDataCreazioneDesc();
    }

}