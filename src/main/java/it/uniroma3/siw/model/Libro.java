package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String titolo;
    @NotNull
    @Min(0)
    @Max(2023)
    private Integer anno;
    private String genere;
    @Column(columnDefinition = "TEXT")
    private String descrizione;

    @ElementCollection
    @CollectionTable(name = "immagini_libro", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "path_immagine")
    private List<String> percorsiImmagini = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "libro_autore", joinColumns = @JoinColumn(name = "libro_id"), inverseJoinColumns = @JoinColumn(name = "autore_id"))
    private List<Autore> autori = new ArrayList<>();
    @OneToMany(mappedBy = "libro", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Recensione> recensioni = new ArrayList<>();
    @ManyToMany(mappedBy = "libriSalvati")
    private List<Utente> utentiCheHannoSalvato;

    // ----------------------------METODI-----------------------------//

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Utente> getUtentiCheHannoSalvato() {
        return utentiCheHannoSalvato;
    }

    public void setUtentiCheHannoSalvato(List<Utente> utentiCheHannoSalvato) {
        this.utentiCheHannoSalvato = utentiCheHannoSalvato;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public List<String> getPercorsiImmagini() {
        return percorsiImmagini;
    }

    public void setPercorsiImmagini(List<String> percorsiImmagini) {
        this.percorsiImmagini = percorsiImmagini;
    }

    public List<Autore> getAutori() {
        return autori;
    }

    public void setAutori(List<Autore> autori) {
        this.autori = autori;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
        result = prime * result + ((anno == null) ? 0 : anno.hashCode());
        result = prime * result + ((autori == null) ? 0 : autori.hashCode());
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
        Libro other = (Libro) obj;
        if (titolo == null) {
            if (other.titolo != null)
                return false;
        } else if (!titolo.equals(other.titolo))
            return false;
        if (anno == null) {
            if (other.anno != null)
                return false;
        } else if (!anno.equals(other.anno))
            return false;
        if (autori == null) {
            if (other.autori != null)
                return false;
        } else if (!autori.equals(other.autori))
            return false;
        return true;
    }

}
