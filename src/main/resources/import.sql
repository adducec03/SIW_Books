-- AUTORI
INSERT INTO autore (id, nome, cognome, data_nascita, data_morte, nazionalita, url_immagine) VALUES (1001, 'Italo', 'Calvino', '1923-10-15', '1985-09-19', 'Italiana', 'https://upload.wikimedia.org/wikipedia/commons/7/7b/Italo-Calvino-i-Oslo_07-04-1961_Fotograf-Johan-Brun.jpg'), (1002, 'Umberto', 'Eco', '1932-01-05', '2016-02-19', 'Italiana', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Italiaanse_schrijver_Umberto_Eco%2C_portret.jpg/250px-Italiaanse_schrijver_Umberto_Eco%2C_portret.jpg'), (1003, 'Neil', 'Gaiman', '1960-11-10', NULL, 'Britannica', 'https://img.ilgcdn.com/sites/default/files/styles/xl/public/foto/2018/11/18/1542534485-7337930.jpg?_=1542534485'), (1004, 'Terry', 'Pratchett', '1948-04-28', '2015-03-12', 'Britannica', 'https://media.npr.org/assets/img/2015/03/12/prat_9780385538305_jkt_ap1_r2_wide-1f04cb85377ab1c6d33b3f4789df67d79d96124e.jpg?s=1100&c=50&f=jpeg');

-- LIBRI
INSERT INTO libro (id, titolo, anno) VALUES (1001, 'Il nome della rosa', 1980), (1002, 'Le città invisibili', 1972), (1003, 'Good Omens', 1990);

-- URL IMMAGINI per libri (Elemento collezione)
INSERT INTO libro_url_immagini (libro_id, url_immagini) VALUES (1001, 'https://m.media-amazon.com/images/I/61Aa9Yic8AL._AC_UF1000,1000_QL80_.jpg'), (1001, 'https://m.media-amazon.com/images/I/71ergCWXyNL._AC_UF1000,1000_QL80_.jpg'), (1002, 'https://www.ibs.it/images/9788804668022_0_0_536_0_75.jpg'), (1003, 'https://www.oscarmondadori.it/content/uploads/2019/03/.webp978880471202HIG.webp');

-- RELAZIONE LIBRO ↔ AUTORE (molti a molti simulata via join table libro_autori)
INSERT INTO libro_autori (libro_id, autori_id) VALUES (1001, 1002), (1002, 1001), (1003, 1003), (1003, 1004);