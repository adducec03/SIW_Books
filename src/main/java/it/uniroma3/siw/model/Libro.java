package it.uniroma3.siw.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Libro {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    private String titolo;
    private int annoPubblicazione;
    private List<String> urlImmagini;
    private List<Autore> autori;




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
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }
    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }
    public List<String> getUrlImmagini() {
        return urlImmagini;
    }
    public void setUrlImmagini(List<String> urlImmagini) {
        this.urlImmagini = urlImmagini;
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
        result = prime * result + annoPubblicazione;
        result = prime * result + ((urlImmagini == null) ? 0 : urlImmagini.hashCode());
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
        if (annoPubblicazione != other.annoPubblicazione)
            return false;
        if (urlImmagini == null) {
            if (other.urlImmagini != null)
                return false;
        } else if (!urlImmagini.equals(other.urlImmagini))
            return false;
        if (autori == null) {
            if (other.autori != null)
                return false;
        } else if (!autori.equals(other.autori))
            return false;
        return true;
    }

}
