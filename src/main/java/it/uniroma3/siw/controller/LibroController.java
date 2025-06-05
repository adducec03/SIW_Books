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
import it.uniroma3.siw.service.LibroService;

@Controller
public class LibroController {

    @Autowired
    LibroService libroService;

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
        return "formNewLibro.html";
    }

    @PostMapping("/libri")
    public String newLibro(@ModelAttribute("libro") Libro libro, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "formNewLibro.html";
        }
        else{
            this.libroService.save(libro);
            model.addAttribute("libro", libro);
            return "redirect:/libro/"+libro.getId();
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
}
