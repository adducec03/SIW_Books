

<h1 align="center">📚 SIW Books</h1>
<p align="center"><i>Un'applicazione web per la gestione di libri e autori.</i></p>

---

## 🖼️ Descrizione

SIW Books è un applicazione web sviluppata per la gestione e consultazione di libri, autori e recensioni. Progettato come progetto accademico per il corso di Sistemi Informativi su Web (Università degli Studi Roma Tre)

---

## 🚀 Funzionalità

- Visualizzazione della lista dei libri
- Visualizzazione della lista degli autori
- Scheda dettagliata per ogni libro con immagini e autori
- Scheda dettagliata per ogni autore con biografia e nazionalità
- Inserimento di nuovi libri, associando più autori esistenti
- Ricerca per anno di pubblicazione

---

## 🛠️ Tecnologie

- Java 17
- Spring Boot
- Spring MVC + Thymeleaf
- Spring Data JPA + Hibernate
- PostgreSQL
- HTML/CSS

---

## 📦 Come eseguire il progetto

1. Clona la repository:

```bash
git clone https://github.com/adducec03/SIW_Books.git
cd SIW_Books
/usr/bin/env /Library/Java/JavaVirtualMachines/jdk-23.jdk/Contents/Home/bin/java @/var/folders/20/448h2f1933x4jpn5p3l2lb7c0000gn/T/cp_cxkkbgct5v6z79wr6xm2mz6yl.argfile it.uniroma3.siw.SiwBooksApplication
```

---

## 📊 Modello di Dominio
![Modello di Dominio](/others/modello_di_dominio.png)

---


## 📄 Casi d'uso

**Caso d’uso UC1: Inserimento nuovo Libro - Attore primario: un’Amministratore** 
1. Un Ammistratore vuole inserire un nuovo libro con i relativi dati.
2. l’Amministratore inserisce il proprio username e la propria password. Il sistema autentica l’Amministratore e mostra l’hompage.
3. L'Ammistratore clicca sulla sezione “libri”. Il sistema mostra il catalogo dei libri aggiunti più di recente.
4. L’Amministratore sceglie l’opzione “aggiungi nuovo libro” nella pagina dei libri. Il sistema mostra la pagina per l'inserimento di un nuovo Libro.
5. L’Amministratore inserisce il titolo e l’anno di pubblicazione del Libro.
6. L’Amministratore sceglie l’opzione “inserisci autori”. Il sistema mostra una lista di Autore registrati.
7. L’Amministratore sceglie uno degli autori registrati e lo aggiunge alla lista di autori del libro.<br>*L'Amministratore ripete i passi 6-7 finche non indica che ha terminato*.
8. L'Ammistratore sceglie l’opzione “carica immagini”. Il sistema mostra una finestra per il   caricamento delle immagini.
9. L'Amministratore carica l’immagine dal suo computer.<br>*L'Amministratore ripete i passi 8-9 finche non indica che ha terminato.*
10. L'Amministratore sceglie l’opzione “conferma inserimento libro”. Il sistema registra il libro. D’ora in poi tutti gli utenti del sistema potranno vedere il libro e le sue relative informazioni. Inoltre il libro comparirà in prima posizione tra i libri aggiunti di recente.


<br>


**Caso d’uso UC2: Aggiornamento dati di un Autore - Attore primario: un Aminstratore**
1. L’Amministratore vuole aggiornare i dati di uno specifico Autore.
2. L’Amministratore inserisce il proprio username e password nel form di login. Il sistema autentica l’Amministratore e mostra l’hompage.
3. L’Amministratore clicca sulla sezione “autori”. Il sistema mostra il catalogo degli autori.
4. L’Amministratore clicca sull’autore che vuole modificare. Il sistema mostra la pagina con i dettagli dell’autore.
5. L’Amministratore clicca sul tasto “modifica autore”. il sistema mostra la pagina per la modifica di un Autore.
6. L’Amministratore inserisce nome, cognome, nazionalità e biografia dell’autore.
7. L’Amministratore sceglie l’opzione “carica immagine”. Il sistema mostra una finestra per il caricamento dell'immagine.
8. L’Amministratore seleziona una sola immagine da assegnare all’autore e preme il tasto carica.
9. L’Ammministratore sceglie l’opzione “conferma inserimento autore”. Il sistema registra l'Autore. D’ora in poi tutti gli utenti del sistema potranno vedere l’Autore e le sue relative informazioni. Inoltre l’autore comparirà in prima posizione tra gli autori aggiunti di recente


<br>


**Caso d’uso UC3: Inserimento nuova recensione - Attore primario: un Utente Registrato**
1. Un utente Registrato vuole aggiungere una nuova recensione per uno specifico Libro.
2. L’Utente il proprio username e la propria password nel form di login. Il sistema autentica l’Utente e mostra l'homepage.
3. L’Utente clicca sul pulsante “libri”. Il sistema mostra la pagina relativa al catalogo dei libri
4. L’Utente clicca sul Libro a cui vuole lasciare una Recensione. Il sistema mostra la pagina con i dettagli del Libro.
5. L’Utente clicca sul pulsante "aggiungi recensione". Il sistema mostra la pagina la pagina per l'aggiunta di una nuova Recensione.
6. L’Utente inserisce il titolo della Recensione, il contenuto della Recensione e una votazione da 1 a 5 stelle.
7. L’Utente sceglie l’opzione “pubblica recensione”. Il sistema registra la nuova Recensione relativa a quel Libro. Inoltre la recensione sarà mostrata come Recensione più recente relativa a quel Libro e modificherà la votazione complessiva del Libro. Inoltre la recensione comparirà come Recensione più recente nell’area personale dell’Utente.


<br>


**Caso d’uso UC4: Modifica dati nell’area personale - Attore primario: un Utente Registrato**
1. Un Utente Registrato vuole modificare i dati nella propria area personale.
2. L’Utente inserisce il proprio username e la propria password nel form di login. Il sistema autentica l’Utente e mostra l'homepage.
3. L’Utente clicca sul pulsante “area personale”. Il sistema mostra l’area personale dell’Utente, con le Recensioni e i Libri salvati.
5. L’Utente sceglie l’opzione “modifica i tuoi dati”. Il sistema mostra la pagina di modifca del profilo
6. L’Utente modifica i dati del suo profilo quali nome, cognome, numero di telefono e le sue credenziali quali email, username e password.
7. L’Utente sceglie l’opzione "salva modifiche". Il sistema mostra l’area personale dell’Utente.


<br>


**Caso d’uso UC5: Ricerca di un Autore per nazionalità - Attore primario un Utente Occasionale**
1. Un Utente Occasionale vuole fare una ricerca per nazionalità degli autori.
2. L’utente clicca sul pulsante autori nella homepage. Il sistema mostra il catalogo degli autori registrati nel sistema.
3. L’utente inserisce la nazionalità che gli interessa. Il sistema mostra l’elenco degli autori di quella nazionalità.
4. L’utente clicca sull’Autore interessato. Il sistema mostra i dettagli relativi a quell’Autore.


<br>


**Caso d’uso UC6: Ricerca di un Libro per Autore e per genere - Attore primario un Utente Occasionale**
1. Un Utente Occasionale vuole fare una ricerca per autore e per genere dei libri.
2. L’Utente clicca sul pulsante libri nella homepage. Il sistema mostra il catalogo dei libri registrati nel sistema.
3. L’Utente inserisce il nome di un Autore e il genere del Libro che vuole cercare. Il sistema mostra tutti i libri che hanno quell’Autore nella lista dei propri autori e che sono relativi al genere inserito.
4. L’Utente clicca sul Libro interessato. Il sistema mostra la pagina con i dettagli relativi a quel Libro.
