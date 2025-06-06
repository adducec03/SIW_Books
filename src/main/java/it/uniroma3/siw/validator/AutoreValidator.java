package it.uniroma3.siw.validator;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import it.uniroma3.siw.model.Autore;

import org.springframework.validation.Validator;


@Component
public class AutoreValidator implements Validator {

  @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        Autore autore = (Autore) o;

        if (autore.getDataNascita() != null && autore.getDataMorte() != null &&
            autore.getDataNascita().isAfter(autore.getDataMorte())) {
            errors.rejectValue("dataNascita", "autoredates.invalid", 
                "La data di nascita deve essere precedente a quella di morte.");
        }
    }
  
  @Override
    public boolean supports(@NonNull Class<?> aClass) {
      return Autore.class.equals(aClass);
    }
}

