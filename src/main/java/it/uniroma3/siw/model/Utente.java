package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @NotBlank
    @Column(nullable = false)
    private String cognome;
    private Integer numeroTelefonico;
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
    name = "utente_libri_salvati",
    joinColumns = @JoinColumn(name = "utente_id"),
    inverseJoinColumns = @JoinColumn(name = "libro_id"))
    private List<Libro> libriSalvati;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recensione> recensioni = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public enum Ruolo {
        USER,
        ADMIN
    }

    @PreRemove
    private void removeAssociations() {
        this.libriSalvati.clear();
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
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

    public Integer getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(Integer numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
        result = prime * result + ((numeroTelefonico == null) ? 0 : numeroTelefonico.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
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
        Utente other = (Utente) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
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
        if (numeroTelefonico == null) {
            if (other.numeroTelefonico != null)
                return false;
        } else if (!numeroTelefonico.equals(other.numeroTelefonico))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    public List<Libro> getLibriSalvati() {
        return libriSalvati;
    }

    public void setLibriSalvati(List<Libro> libriSalvati) {
        this.libriSalvati = libriSalvati;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    public void salvaLibro(Libro libro) {
        this.libriSalvati.add(libro);
    }

    public void rimuoviLibro(Libro libro) {
        this.libriSalvati.remove(libro);
    }

}
