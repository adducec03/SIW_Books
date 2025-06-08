package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository userRepository;

    public Optional<Utente> getUser(Long id){
        return userRepository.findById(id);
    }

    public void saveUser(Utente user){
        userRepository.save(user);
    }

}
