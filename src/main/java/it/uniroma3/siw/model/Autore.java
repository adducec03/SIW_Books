package it.uniroma3.siw.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Autore {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String nome;
    private String cognome;
    @NotNull
    private LocalDate dataNascita;
    private LocalDate dataMorte;
    @NotBlank
    private String nazionalita;
    private String percorsoImmagine;
    @Column(columnDefinition = "TEXT")
    private String descrizione;

    // ----------------------------METODI-----------------------------//

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public LocalDate getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(LocalDate dataMorte) {
        this.dataMorte = dataMorte;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public String getPercorsoImmagine() {
        return percorsoImmagine;
    }

    public void setPercorsoImmagine(String urlImmagine) {
        this.percorsoImmagine = urlImmagine;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
        result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
        result = prime * result + ((dataMorte == null) ? 0 : dataMorte.hashCode());
        result = prime * result + ((nazionalita == null) ? 0 : nazionalita.hashCode());
        result = prime * result + ((percorsoImmagine == null) ? 0 : percorsoImmagine.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Autore other = (Autore) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cognome == null) {
            if (other.cognome != null)
                return false;
        } else if (!cognome.equals(other.cognome))
            return false;
        if (dataNascita == null) {
            if (other.dataNascita != null)
                return false;
        } else if (!dataNascita.equals(other.dataNascita))
            return false;
        if (dataMorte == null) {
            if (other.dataMorte != null)
                return false;
        } else if (!dataMorte.equals(other.dataMorte))
            return false;
        if (nazionalita == null) {
            if (other.nazionalita != null)
                return false;
        } else if (!nazionalita.equals(other.nazionalita))
            return false;
        if (percorsoImmagine == null) {
            if (other.percorsoImmagine != null)
                return false;
        } else if (!percorsoImmagine.equals(other.percorsoImmagine))
            return false;
        return true;
    }

}
