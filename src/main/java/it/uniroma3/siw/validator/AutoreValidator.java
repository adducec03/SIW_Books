package it.uniroma3.siw.validator;

import java.time.LocalDate;

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

    LocalDate oggi = LocalDate.now();
    LocalDate nascita = autore.getDataNascita();
    LocalDate morte = autore.getDataMorte();
    boolean inVita = autore.isInVita();

    if (nascita == null) {
        errors.rejectValue("dataNascita", "autore.dataNascita.null",
            "La data di nascita è obbligatoria.");
        return;
    }

    if (nascita.isAfter(oggi)) {
        errors.rejectValue("dataNascita", "autore.dataNascita.futura",
            "La data di nascita non può essere nel futuro.");
        return;
    }

    if (!inVita && morte == null) {
        errors.rejectValue("dataMorte", "autore.dataMorte.obbligatoria",
            "Se l'autore non è in vita, è obbligatorio indicare la data di morte.");
        return;
    }

    if (morte != null) {
        if (morte.isAfter(oggi)) {
            errors.rejectValue("dataMorte", "autore.dataMorte.futura",
                "La data di morte non può essere nel futuro.");
            return;
        }

        if (nascita.isAfter(morte)) {
            errors.rejectValue("dataMorte", "autore.dateOrder.invalid",
                "La data di morte non può essere precedente alla data di nascita.");
            return;
        }
    }
}

  @Override
  public boolean supports(@NonNull Class<?> aClass) {
    return Autore.class.equals(aClass);
  }
}
