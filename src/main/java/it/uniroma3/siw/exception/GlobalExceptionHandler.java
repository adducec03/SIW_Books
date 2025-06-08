package it.uniroma3.siw.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("globalError", "Valore non valido inserito. Assicurati che l’anno sia un numero.");
        return "redirect:/libri";
    }
    /* 
    @ExceptionHandler(Exception.class)
    public String handleGenericError(Exception ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("globalError", "Si è verificato un errore imprevisto.");
        return "redirect:/libri";
    }
    */
}
