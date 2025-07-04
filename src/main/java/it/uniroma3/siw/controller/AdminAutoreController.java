package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.service.AutoreService;
import jakarta.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/admin/autore")
public class AdminAutoreController {

    @Autowired
    private AutoreService autoreService;

    // Mostra dettaglio autore
    @GetMapping("/{id}")
    public String getAutore(@PathVariable("id") Long id, Model model) {
        Autore autore = autoreService.getAutoreById(id);
        if (autore == null) {
            return "redirect:/admin/autori";
        }
        model.addAttribute("autore", autore);
        return "admin/autore";
    }

    // Mostra form modifica
    @GetMapping("/{id}/modifica")
    public String mostraFormModifica(@PathVariable("id") Long id, Model model) {
        Autore autore = autoreService.getAutoreById(id);
        if (autore == null) {
            return "redirect:/admin/autori";
        }
        model.addAttribute("autore", autore);
        return "admin/modificaAutore";
    }

    // Gestione submit modifica autore
    @PostMapping("/{id}/modifica")
    public String modificaAutore(@PathVariable("id") Long id,
            @RequestParam String nome,
            @RequestParam(required = false) String cognome,
            @RequestParam String nazionalita,
            @RequestParam String dataNascita,
            @RequestParam(required = false) String dataMorte,
            @RequestParam(required = false) String descrizione,
            @RequestParam(name = "immagine", required = false) MultipartFile nuovaImmagine,
            Model model) throws IOException {

        Autore autore = autoreService.getAutoreById(id);
        if (autore == null) {
            return "redirect:/admin/autori";
        }

        // Parsing delle date
        LocalDate nascita = LocalDate.parse(dataNascita);
        LocalDate morte = null;
        if (dataMorte != null && !dataMorte.trim().isEmpty()) {
            morte = LocalDate.parse(dataMorte);

            if (morte.isBefore(nascita)) {
                model.addAttribute("autore", autore);
                model.addAttribute("dataError", "La data di morte non può essere precedente alla data di nascita.");
                return "admin/modificaAutore";
            }
        }

        // Caricamento nuova immagine
        if (nuovaImmagine != null && !nuovaImmagine.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/uploads/autori/"; // cartella relativa alla directory
                                                                                    // del progetto
            Path uploadPath = Paths.get(uploadDir);

            // creazione cartella se non esiste
            if (!Files.exists(uploadPath)) {
                try {
                    Files.createDirectories(uploadPath);
                } catch (IOException e) {
                    throw new RuntimeException("Impossibile creare la cartella di upload: " + uploadPath, e);
                }
            }

            if (!nuovaImmagine.isEmpty()) {
                System.out.println("prova");
                String fileName = UUID.randomUUID() + "_" + nuovaImmagine.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);

                try {
                    nuovaImmagine.transferTo(filePath.toFile()); // salva nel file system
                    autore.setPercorsoImmagine("/uploads/autori/" + fileName);// path da usare nella visualizzazione
                } catch (IOException e) {
                    throw new RuntimeException("Errore nel salvataggio del file: " + fileName, e);
                }
            }

        }

        // Aggiornamento autore
        autore.setNome(nome);
        autore.setCognome(cognome);
        autore.setNazionalita(nazionalita);
        autore.setDataNascita(nascita);
        autore.setDataMorte(morte);
        autore.setDescrizione(descrizione);

        autoreService.save(autore);
        return "redirect:/admin/autore/" + id;
    }

    @GetMapping("/formNewAutore")
    public String mostraFormNuovoAutore(Model model) {
        model.addAttribute("autore", new Autore());
        return "admin/formNewAutore";
    }

    // Gestisce il submit del nuovo autore
    @PostMapping("/autore")
    public String aggiungiAutore(@Valid @ModelAttribute("autore") Autore autore,
            BindingResult bindingResult,
            Model model) {
        // Controllo logico: la data di morte non deve essere prima della nascita
        if (autore.getDataMorte() != null && autore.getDataMorte().isBefore(autore.getDataNascita())) {
            bindingResult.rejectValue("dataMorte", "invalid", "La data di morte non può precedere quella di nascita.");
        }

        if (bindingResult.hasErrors()) {
            return "admin/formNewAutore";
        }

        autoreService.save(autore);
        return "redirect:/admin/autore" + autore.getId();
    }

    @PostMapping("/{id}/elimina")
    public String eliminaAutore(@PathVariable Long id) {
        Autore autore = autoreService.getAutoreById(id);

        if (autore != null) {
            autoreService.eliminaAutoreECascadeLibri(autore);
        }

        return "redirect:/autori";
    }

}
