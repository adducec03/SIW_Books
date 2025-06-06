package it.uniroma3.siw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.LibroService;
import jakarta.validation.Valid;

@Controller
public class LibroController {

    @Autowired
    LibroService libroService;

    @Autowired
    AutoreService autoreService;

    @GetMapping("/libro/{id}")
    public String getLibro(@PathVariable("id") Long id, Model model) {
        model.addAttribute("libro", this.libroService.getLibroById(id));
        return "libro.html";
    }

    @GetMapping("/libri")
    public String showLibri(Model model) {
        model.addAttribute("libri", this.libroService.getAllLibri());
        return "libri.html";
    }

    @GetMapping("/formNewLibro")
    public String formNewLibro(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("autori", autoreService.getAllAutori());
        return "formNewLibro.html";
    }

    @PostMapping("/libro")
    public String newLibro(@Valid @ModelAttribute("libro") Libro libro, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) { // sono emersi errori nel binding
            model.addAttribute("autori", autoreService.getAllAutori());
            return "formNewLibro.html";
        } else {                         // NON sono emersi errori nel binding
            this.libroService.save(libro);
            model.addAttribute("libro", libro);
            return "redirect:libro/" + libro.getId();
        }
    }

    @GetMapping("/formSearchLibri")
    public String formSearchLibri() {
        return "formSearchLibri.html";
    }

    @PostMapping("/searchLibri")
    public String searchLibri(Model model, @RequestParam Integer anno) {
        model.addAttribute("libri", this.libroService.findByAnno(anno));
        return "foundLibri.html";
    }
    /*
    @PostMapping("/libro")
    public String salvaLibro(@ModelAttribute Libro libro, @RequestParam List<Long> autori) {
        libro.setId(null);
        List<Autore> listaAutori = (List<Autore>) autoreService.findAllById(autori);
        libro.setAutori(listaAutori);
        libroService.save(libro);
        return "redirect:/libri";
    }
    */
}
