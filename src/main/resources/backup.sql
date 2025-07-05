--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-07-05 21:20:25 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3670 (class 2613 OID 60974)
-- Name: 60974; Type: BLOB METADATA; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('60974');

ALTER LARGE OBJECT 60974 OWNER TO postgres;

--
-- TOC entry 3662 (class 0 OID 64461)
-- Dependencies: 223
-- Data for Name: autore; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.autore VALUES (NULL, '1949-01-12', 6, 'Murakami', 'Haruki Murakami (1949‑) è uno degli scrittori contemporanei più influenti. Giapponese, autore di successi come “Norwegian Wood” e “Kafka sulla spiaggia”, miscela realismo urbano, mistero e surrealismo, con protagonisti spesso alienati e in bilico tra mondi. La sua scrittura è evocativa, intrisa di cultura occidentale (musica, cinema) e alimentata da atmosfere oniriche. I suoi romanzi affrontano la solitudine, il destino e l’inadeguatezza esistenziale, raggiungendo un pubblico globale. Premio Yomiuri e Jerusalem, Murakami esplora l’inconscio attraverso un linguaggio semplice e poetico, capace di affascinare lettori di ogni latitudine.', 'Giapponese', 'Haruki', '/uploads/autori/7bef4d26-5382-475b-9120-0f8f69bdc9bb_haruki_murakami.jpg');
INSERT INTO public.autore VALUES (NULL, '1977-09-15', 7, 'Ngozi Adichie', 'Chimamanda Ngozi Adichie (1977‑), nigeriana, è una voce potente del femminismo contemporaneo e della diaspora africana. Con romanzi come “Americanah”, ha raccontato l’esperienza di immigrati in Occidente, le tensioni razziali e identitarie. Il saggio “We Should All Be Feminists”, trasformato in TED Talk virale, ha ridefinito la conversazione globale sul genere. Lo stile di Adichie è diretto, emotivamente coinvolgente, e fonde tradizione culturale e modernità. Attraverso personaggi femminili complessi, esplora amore, potere, migrazione e famiglia, con un approccio universale che unisce intimità personale e denuncia sociale.', 'Nigeriana', 'Chimamanda', '/uploads/autori/b1efc3f2-52c2-4e6f-85e5-1fc79300ffa0_eyevine03723829.webp');
INSERT INTO public.autore VALUES (NULL, '1939-11-18', 9, 'Atwood', 'Margaret Atwood (1939‑), canadese, è una delle maggiori scrittrici di fantascienza speculativa e narrativa contemporanea. Con “L’altra Grace” e soprattutto con “The Handmaid’s Tale”, ha creato mondi distopici al femminile, centrati su potere, corpo e controllo. Autrice e poetessa, mescola critica sociale e sovversione letteraria. Attivista ambientale e femminista, Atwood usa satira e tensione psicologica per indagare ingiustizie di genere e autoritarismi. Premio Booker e prix Goncourt, continua a immaginare futuri inquietanti, partendo da dinamiche politiche attuali, invitando a riflettere su libertà, ecologia e diritti umani.', 'Canadese', 'Margaret', '/uploads/autori/27d374dc-69ea-49c4-8509-f91bb3ed1238_ATWOOD3.LMora_-600x600.jpg');
INSERT INTO public.autore VALUES (NULL, '1954-11-08', 8, 'Ishiguro', 'Kazuo Ishiguro (1954‑), giapponese naturalizzato britannico, è celebre per la sua prosa elegante, sommessa e toccante. In “The Remains of the Day” ha esplorato rimorso e lealtà, in “Quel che resta del giorno” la decadenza morale e il sacrificio personale. Vincitore del Nobel nel 2017, affronta ricordi, identità e memoria, con scenario spesso interiore e nostalgico. In “Non lasciarmi” propone una distopia silenziosa e tragica sul valore della vita umana. Ishiguro mescola introspezione psicologica e riflessione filosofica, creando mondi emotivamente realistici in cui il passato non può essere dimenticato.', 'Giapponese', 'Kazuo', '/uploads/autori/59630cb7-a662-4569-8d05-86ec68c88803_KazuoIshiguro.jpg');
INSERT INTO public.autore VALUES ('1985-09-19', '1923-10-15', 1, 'Calvino', 'Italo Calvino (1923–1985) è stato uno dei più grandi scrittori italiani del Novecento. Nato a Santiago de Las Vegas, Cuba, e cresciuto a Sanremo, ha sperimentato generi diversi: dalla neorealismo (con “Il sentiero dei nidi di ragno”) alle favole filosofiche (“Il barone rampante”, “Il visconte dimezzato”), fino ai racconti metafisici (“Le città invisibili”). Apprezzato per la leggerezza e l’ingegno narrativo, ha esplorato la letteratura come costruzione di mondi possibili. Saggista raffinato, ha scritto “Lezioni americane”, lasciando in eredità un’indagine profonda sulla memoria, l’immaginazione e il ruolo dell’autore nella società moderna.', 'Italiana', 'Italo', '/uploads/autori/86ccf77d-52d4-4948-a8e9-364e169fc3e4_Italo-Calvino-i-Oslo_07-04-1961_Fotograf-Johan-Brun.jpg');
INSERT INTO public.autore VALUES ('1950-01-21', '1903-06-25', 3, 'Orwell', 'George Orwell, al secolo Eric Arthur Blair (1903–1950), è celebre per le sue opere satiriche e distopiche. Con “1984” ha realizzato una critica feroce al totalitarismo e al controllo delle masse, mentre ne “La fattoria degli animali” ha denunciato le derive burocratiche e ideologiche. I suoi testi combinano precisione linguistica, impegno etico e capacità narrativa. Cofondatore della cultura del giornalismo impegnato, ha saputo raccontare con chiarezza politica e psicologica la fragilità delle democrazie e gli abusi del potere, restando un punto di riferimento per chi studia la libertà e la manipolazione mediatica.', 'Britannica', 'George', '/uploads/autori/c7b93e90-46b0-4d57-8071-5049a7972e83_orwell-e1691835559440.webp');
INSERT INTO public.autore VALUES (NULL, '1943-01-01', 2, 'Ferrante', 'Elena Ferrante è lo pseudonimo di una misteriosa scrittrice italiana nata nel 1943. Famosa per la tetralogia dell’“Amica geniale”, ha conquistato pubblico e critica grazie alla rappresentazione intensa del legame tra due amiche, Lila e Lenù, nella Napoli del dopoguerra. I suoi romanzi, scritti con stile immediato e psicologico, affrontano temi di identità, femminilità, sessualità e lotta di classe. Pur restando anonima, la sua voce, potente e autentica, ha avuto un impatto mondiale, ridefinendo la narrativa contemporanea italiana con uno sguardo profondamente personale e sociale, che unisce introspezione a analisi culturale.', 'Italiana', 'Elena', '/uploads/autori/1f27727c-9881-497d-8fb4-fbd321d7c520_Elena-Ferrante.webp');
INSERT INTO public.autore VALUES (NULL, '1967-07-11', 14, 'Lahiri', 'Jhumpa Lahiri (1967‑) è un’autrice americana di origini bengalesi. Ha vinto il premio Pulitzer per “L’interprete dei malanni”, una raccolta di racconti intensi su identità, migrazione e distacco culturale. Nei suoi romanzi (come “In altre parole”, scritto in italiano) esplora la lingua, la nostalgia e la costruzione del sé tra due mondi. La sua prosa è elegante, misurata, intima. Lahiri indaga l’appartenenza e la lingua come forma di radicamento. Attraverso storie delicate ma profonde, crea un ponte tra realtà culturali, mostrando la bellezza e la fatica della vita di ponteggi interculturali.', 'Statunitense', 'Jhumpa', '/uploads/autori/9854b5bf-9038-4ef1-ae8f-58488e79d588_JHUMPA-LAHIRI.jpg');
INSERT INTO public.autore VALUES ('2008-09-12', '1962-02-21', 15, 'Foster Wallace', 'David Foster Wallace (1962–2008) è mancato troppo presto, ma ha lasciato una traccia indelebile nella letteratura americana contemporanea. “Infinite Jest” è il suo capolavoro: un romanzo monumentale su dipendenza, intrattenimento, banalità e cultura pop. La sua prosa mescola alta cultura, humor, note a piè di pagina vertiginose e riflessioni filosofiche. Autore di saggi (britannici o statunitensi), racconti e reportage, Wallace esplora ansia, alienazione, ossessione. Il suo stile iperrealista, intellettuale e metanarrativo ha influenzato generazioni successive, riflettendo la complessità del presente con autenticità spiazzante.', 'Statunitense', 'David', '/uploads/autori/057a122a-caa2-4752-a7fd-161f406f60ed_960px-David_Foster_Wallace.jpg');
INSERT INTO public.autore VALUES ('1941-03-28', '1882-01-25', 4, 'Woolf', 'Virginia Woolf (1882–1941) è stata una pioniera della scrittura modernista. Autrice di capolavori come “Mrs Dalloway” e “Gita al faro”, ha introdotto la tecnica del flusso di coscienza per esplorare le interiorità dei personaggi. Fondatrice, insieme al marito Leonard, della casa editrice Hogarth Press, ha promosso la letteratura femminile e controcorrente. I suoi saggi, in particolare “Una stanza tutta per sé”, definiscono la riflessione sul ruolo della donna nella cultura. Con sensibilità psicologica e sperimentazione formale, ha segnato un punto di svolta nella narrativa del Novecento, affrontando fragilità, identità e oppressione.', 'Britannica', 'Virginia', '/uploads/autori/a88465bc-0906-422f-8699-1edb15e6d007_Virginia-Woolf-Internal.jpg');
INSERT INTO public.autore VALUES (NULL, '1931-07-10', 12, 'Munro', 'Alice Munro (1931‑2023) è ente vivente della letteratura contemporanea, vincitrice del Nobel per la Letteratura nel 2013. Canadese, è considerata una delle massime interpreti del racconto breve, capace di cogliere cortocircuiti emotivi e psicologici nella quotidianità. In raccolte come “Festa in casa delle bambole” e “Le vergini”, esplora temi di memoria, identità femminile e rapporti familiari. La sua scrittura è essenziale e profonda, con finale spesso sorprendente. Munro ha ridefinito il racconto breve moderno, fondendo realtà semplice e rivelazione profonda con maestria narrativa e empatia.', 'Canadese', 'Alice', '/uploads/autori/1eae76ba-6e05-417f-9e13-e9ff7358e1b4_MV5BOGZjYmVkNzAtZWI0Yi00ZDJmLWJjZDQtYWQyZDZkZWY3ZjM0XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg');
INSERT INTO public.autore VALUES (NULL, '1969-11-06', 16, 'Whitehead', 'Colson Whitehead (1969‑), statunitense, vincitore del Premio Pulitzer con “The Underground Railroad” (premio per la schiavitù americana) e “The Nickel Boys” (segregazione). La sua scrittura unisce ricerca storica e potenza narrativa. Ha esplorato comunità nere e identità americana con linguaggio vivido, empatico e critico. In altri romanzi (es. “Zone One”, “Harlem Shuffle”) miscela generi, dal post-apocalittico al noir urbano. Whitehead interroga passato e presente, cercando radici e memoria collettiva, con uno stile limpido che parla di ingiustizia, resilienza e speranza attraverso voci autentiche e complesse.', 'Statunitense', 'Colson', '/uploads/autori/52e270af-eee5-495e-ace2-6b59c3ee9cfc_MV5BZmY5MTBjMjUtMzVmOC00ZTVlLThmYjctMmM0MjIyNDBlODgwXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg');
INSERT INTO public.autore VALUES ('2016-02-19', '1932-01-05', 18, 'Eco', 'Umberto Eco (1932–2016) è stato filosofo, semiologo e romanziere italiano di fama mondiale. Il suo romanzo di esordio “Il nome della rosa” ha unito storia medievale, giallo intellettuale e riflessioni filosofiche, diventando un classico globale. Seguono opere come “Il pendolo di Foucault” e saggi fondamentali (“Apocalittici e integrati”) sul ruolo dei media e della cultura di massa. Eco è stato anche professore universitario e figura di riferimento nel mondo culturale, noto per la sua erudizione, sense of humour e capacità di coniugare divulgazione e rigore teorico.', 'Italiana', 'Umberto', '/uploads/autori/fa7f8ccc-0c39-4b81-bef2-effa705e34b0_banner1.avif');
INSERT INTO public.autore VALUES (NULL, '1975-10-25', 17, 'Smith', 'Zadie Smith (1975‑), britannica di origini giamaicane e inglesi, ha esordito giovanissima con “Denti bianchi”, un ritratto caotico e brillante della Londra multietnica. Il suo stile unisce ironia, riflessione sociale e grande attenzione ai dettagli umani. In romanzi successivi (come “Swing Time”), esplora razza, classe, femminilità e identità. Saggista acuta (es. “Feel Free”), Smith analizza cultura e politica contemporanea con energia, intelligenza e humor. La sua scrittura è brillante, emozionante e piena di empatia, con personaggi vividi e situazioni spesso surreali.', 'Britannica', 'Zadie', '/uploads/autori/a3b49990-bd6b-472c-831a-299abd2b3619_zadiesmith-hero.png');
INSERT INTO public.autore VALUES (NULL, '1940-09-01', 19, 'Ernaux', 'Annie Ernaux (1940‑), francese, è una voce innovativa nell’autofiction e nella scrittura memorialistica. In libri come “Gli anni”, “La ragazza” e “I fatti”, racconta la vita quotidiana, la classe sociale e la propria storia personale con stile lucido e rigore storiografico. Utilizza una prosa essenziale, senza retorica, ma carica di verità emotiva. Ernaux indaga memoria, malattia e separazione, rendendo visibile il privato e il politico. Premio Nobel per la Letteratura nel 2022, continua a influenzare autrici e autori per la sua capacità di fondere intimità e riflessione sociale.', 'Francese', 'Annie', '/uploads/autori/ced857b2-dcb5-4f2e-a494-420d134bebf0_Annie_Ernaux_Keystone_04_extend.jpg');
INSERT INTO public.autore VALUES (NULL, '1957-12-09', 20, 'Carrère', 'Emmanuel Carrère (1957‑), scrittore e regista francese, ama la narrazione ibrida tra biografia, romanzo e reportage. In “La vita scritta”, “Un roman russe” e “Limonov”, intreccia storie personali e vicende reali, creando una scrittura riflessiva e intensa. Le sue opere esplorano identità, follia, verità e finzione, portando il narratore al centro del racconto. Carrère lavora spesso con autobiografia e inchiesta, in un tono spesso bello e inquietante. Premio Renaudot e alpinista narrativo, ha ridefinito la narrativa contemporanea francese con rigore e introspezione.', 'Francese', 'Emmanuel', '/uploads/autori/b49485a3-f95f-4490-b439-50d828e98ea6_carrere.jpg');
INSERT INTO public.autore VALUES (NULL, '1959-08-17', 11, 'Franzen', 'Jonathan Franzen (1959‑) è uno dei principali narratori americani contemporanei. Con “Le correzioni” e “Libertà” ha esplorato disfunzioni familiari, critica sociale e legami personali, diventando un punto di riferimento della grande narrativa statunitense. Le sue opere analizzano anche consumismo, ambiente e media. Franzen è anche saggista: il famoso articolo “Perché scriviamo?” riflette sul senso della letteratura oggi. Con stile realistico, dettagliato, talvolta satirico, unisce epicità familiare e riflessioni sul presente. Riceve premi importanti (James Tait Black, National Book Award) e contribuisce al dibattito pubblico su libri e cultura.', 'Statunitense', 'Jonathan', '/uploads/autori/bac49ae7-4717-4fab-b236-132293f45b75_1jon-640x420.jpg');
INSERT INTO public.autore VALUES (NULL, '1982-12-19', 13, 'Giordano', 'Paolo Giordano (1982‑) è un romanziere italiano noto per il bestseller “La solitudine dei numeri primi”, che ha esplorato fragilità individuali, relazioni spezzate e trauma emotivo. Fisico e scrittore, unisce rigore scientifico e introspezione psicologica. In “Divorare il cielo”, racconta esperienze giovanili vissute intensamente. La sua scrittura cerca autenticità nelle emozioni e bellezza nella quotidianità. Tra romanzi, saggi e reportage, Giordano affronta questioni sociali, famiglia, ricerca della felicità. Vincitore di premi internazionali, riflette con passione sulle tensioni tra scienza e sentimento nella complessità contemporanea.', 'Italiana', 'Paolo', '/uploads/autori/bf6766f3-5a3c-4dbd-aed9-798fde44011d_Paolo-Giordano.jpg');
INSERT INTO public.autore VALUES ('2014-04-17', '1927-03-06', 5, 'Márquez', 'Gabriel García Márquez (1927–2014), nato in Colombia, è il principale esponente del realismo magico. Il suo capolavoro “Cent’anni di solitudine” ha consegnato ai lettori un universo familiare profondo e visionario, segnato da tempo circolare e meraviglia quotidiana. Con “L’amore ai tempi del colera”, ha raccontato l’ostinata perseveranza dell’amore. Premio Nobel per la Letteratura nel 1982, García Márquez ha fuso storia, mitologia e politica latinoamericana con uno stile ricco, affabulatorio e memorabile. Giornalista di formazione, ha coltivato una visione critica del potere e dell’ingiustizia, testimoniata nei suoi romanzi, saggi e articoli.', 'Colombiana', 'Gabriel García', '/uploads/autori/27323d76-99bc-4d8c-89d0-fd74a68d29c1_Marquez-Gabriel-adv-obit-slide-LP84-superJumbo-v5.jpg');
INSERT INTO public.autore VALUES ('2003-07-14', '1953-04-28', 10, 'Bolaño', 'Roberto Bolaño (1953–2003) è considerato il più influente scrittore latinoamericano post-Borgès. Cileno di nascita, ha vissuto in Messico e Spagna, e ha scritto romanzi sterminati come “2666” e “I detective selvaggi”, in cui intreccia mito, letteratura e violenza. Il suo stile è denso, polifonico, frammentario, capace di raccontare storie erranti, amori e ossessioni. Il realismo visionario di Bolaño esplora il potere politico e la storia del Novecento. Poetica della marginalità e dell’inquietudine, ha lasciato un segno indelebile nella narrativa mondiale contemporanea, con un linguaggio narrativo sperimentale e potente.', 'Cilena', 'Roberto', '/uploads/autori/ecd65082-ab0f-4a1c-bbbe-9d0705345dbc_1rb-640x420.jpg');


--
-- TOC entry 3668 (class 0 OID 64502)
-- Dependencies: 229
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utente VALUES (0, 100, 'User', 'admin@example.com', 'Admin');
INSERT INTO public.utente VALUES (12345678, 1, 'Rossi', 'mario.rossi@gmail.com', 'Mario');
INSERT INTO public.utente VALUES (987585, 2, 'Verdi', 'luca.verdi@gmail.com', 'Luca');
INSERT INTO public.utente VALUES (NULL, 3, 'Adduce', 'no-email@oauth.local', 'Carmine');
INSERT INTO public.utente VALUES (85404830, 4, 'Neri', 'antonio.neri@gmail.com', 'Antonio');


--
-- TOC entry 3663 (class 0 OID 64468)
-- Dependencies: 224
-- Data for Name: credentials; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.credentials VALUES (100, 100, '$2a$10$KTUov316ZwY0tZ7a21Cix.stNhLIRDaN4lbdG2d0PO1M9XjxAYmBy', 'form', 'ADMIN', 'admin');
INSERT INTO public.credentials VALUES (1, 1, '$2a$10$qN/pv9RiBeSAW5gJceCxqOITDSkhQmtTUpHL9oKLmv//cMwAM1m0W', 'form', 'USER', 'mariorossi');
INSERT INTO public.credentials VALUES (2, 2, '$2a$10$iqeH5p.dYcFPRRNEfuoenezUec0vZwbA3fvyebaggJJcs2llZPMDa', 'form', 'USER', 'lucaverdi');
INSERT INTO public.credentials VALUES (3, 3, 'oauth', 'oauth', 'USER', 'adducec03');
INSERT INTO public.credentials VALUES (4, 4, '$2a$10$KO67TDYItJtkv3Zj94Tjle.zZ0THvODXxKSctCTeqGzRuwJM9UNJO', 'form', 'USER', 'antonioneri');


--
-- TOC entry 3665 (class 0 OID 64482)
-- Dependencies: 226
-- Data for Name: libro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.libro VALUES (1987, 4, 'Quando sente una vecchia canzone dei Beatles, Toru Watanabe ricorda la sua giovinezza nella Tokyo degli anni ’60. Al centro della sua memoria, l’amore per Naoko, fragile e tormentata, segnata dalla morte del loro amico Kizuki. Mentre Naoko sprofonda nella malattia mentale, Toru conosce Midori, vivace e diretta, che lo spinge verso la vita. Norwegian Wood è un romanzo sull’amore, la perdita, la depressione e il passaggio all’età adulta. Murakami scrive con tono malinconico e dolce, raccontando un’epoca e una generazione che cerca un equilibrio tra solitudine e speranza.
', 'Romanzo', 'Norwegian Wood');
INSERT INTO public.libro VALUES (2001, 12, 'La famiglia Lambert si prepara all’ultimo Natale con l’anziano padre, Alfred, affetto da demenza senile. La madre Enid organizza tutto, cercando di tenere insieme ricordi e illusioni. I figli Gary, Chip e Denise affrontano crisi professionali, personali e mediche: Gary nella finanza, Chip nel fallimento accademico, Denise nel sentirsi intrappolata nella maternità. Franzen dipinge un ritratto impietoso e tenero di una grande famiglia americana contemporanea, tra aspettative frustrate e tentativi di riconciliazione. Ogni capitolo scava nella psiche dei protagonisti finché la famiglia, con voce nuova, scopre i limiti del cambiamento e la possibilità della verità.
', 'Narrativa', 'Le correzioni');
INSERT INTO public.libro VALUES (1985, 1, 'In una città sul fiume, Florentino Ariza e Fermina Daza vivono un intenso amore giovanile interrotto ma mai dimenticato. Quando Fermina sposa un medico rispettabile, Florentino giura attendere. Cinquant’anni, due mesi e ventisei giorni dopo, il dottor Juvenal Urbino muore, e Florentino coglie l’occasione per dichiararsi di nuovo. Attraverso lettere, barche e un’innata fedeltà romantica, lui sfida il tempo e le convenzioni. Marquez racconta una passione che sopravvive alla vecchiaia, alla morte e alla distanza, disegnando un ritratto struggente di attesa, speranza e amore senza limiti.', 'Narrativa', 'L''amore ai tempi del colera');
INSERT INTO public.libro VALUES (1949, 2, 'Winston Smith vive nella Londra del futuro, dove il Grande Fratello osserva ogni movimento. Impiegato al Ministero della Verità, riscrive la storia per adeguarla alla propaganda governativa. Quando inizia a tenere un diario segreto e a cercare relazioni con Julia, diventa un pericolo per il regime totalitario che controlla la vita di ogni cittadino. La sua ribellione lo porta alla tortura nella famigerata Stanza 101, dove la manipolazione della verità e la paura diventano strumenti di dominio assoluto. Orwell dipinge un mondo cupo in cui l’individuo è annientato dal potere assoluto e dalla sorveglianza.
', 'Distopia', '1984');
INSERT INTO public.libro VALUES (1967, 3, 'Nel villaggio di Macondo, fondato da José Arcadio Buendía, si alternano generazioni della famiglia Buendía immerse in un’epica saga leggendaria. L’amore impossibile, la violenza, la solitudine e la magia quotidiana si intrecciano in una realtà sospesa tra il fantastico e il reale. Tra colombe che piangono sangue, bugie che diventano realtà e passioni travolgenti, ogni membro della famiglia lotta contro il destino e la memoria. Il tempo sembra ripetersi in cicli, fino a un’inquietante profezia. Márquez costruisce un universo simbolico, dove la storia di un popolo diventa mito e la solitudine si trasforma in potere narrativo.
', 'Magico realismo', 'Cent''anni di solitudine');
INSERT INTO public.libro VALUES (1925, 5, 'Londra, un giorno di giugno. Clarissa Dalloway prepara una festa serale con grazia e precisione. Mentre passeggia per la città, i suoi pensieri fluiscono tra ricordi, rimpianti e l’impatto delle scelte passate. Parallelamente, il veterano della Grande Guerra Septimus Warren Smith combatte allucinazioni e trauma, incapace di trovare senso nella vita post-bellica. I loro universi si sfiorano simbolicamente, mostrando dolore, isolamento e la fragilità dell’esistenza. Woolf utilizza il flusso di coscienza, seguendo i pensieri dei personaggi e le loro connessioni emotive. Con sensibilità psicologica, il romanzo rende visibile la complessità interiore dietro atti quotidiani e convenzioni sociali.
', 'Narrativa', 'Mrs Dalloway');
INSERT INTO public.libro VALUES (1957, 6, 'Cosimo Piovasco di Rondò, a dodici anni, decide di salire su un albero e non scendere mai più. Vive fra le fronde, sfidando la società e sperimentando avventure, amori e conflitti familiari. Da quella prospettiva insolita osserva il mondo con occhi nuovi: dal cucchiaio ai rivoluzionari, dagli amori proibiti ai piaceri semplici. Le sue radici diventano simbolo di indipendenza e costruzione identitaria. Calvino racconta la favola filosofica di un ragazzo che sceglie la libertà, non la fuga, riflettendo sul legame fra natura, cultura e responsabilità. Un’allegoria gentile della crescita consapevole.
', 'Fiaba filosofica', 'Il barone rampante');
INSERT INTO public.libro VALUES (1980, 7, 'Nel 1327, nel monastero benedettino di un’abbazia sul lago, l’erudito Guglielmo da Baskerville e il novizio Adso giungono per indagare su misteriosi omicidi. Tra cripte, biblioteche segrete e intrighi religiosi, cercano la verità nascosta nel labirinto delle conoscenze proibite. Eco intreccia giallo, filosofia, storia e teologia in un affresco medievale rigoglioso. Ogni omicidio apre interrogativi filosofici sulla fede, il potere e l’interpretazione dei testi. L’atmosfera è cupa, sospesa tra ragione e superstizione. Alla fine, la biblioteca brucia: metafora del sapere che distrugge se stesso. Un romanzo che indaga il limite tra conoscenza e censura.
', 'Giallo storico', 'Il nome della rosa');
INSERT INTO public.libro VALUES (2003, 8, 'In un mondo post-apocalittico, un uomo e suo figlio camminano su una strada deserta, verso sud, braccati dalla fame e dal freddo. Tra città in rovina e paesaggi grigi, il padre cerca di preservare l’umanità del figlio, insegnandogli a essere “buono” nonostante la follia circostante. Incontrano sopravvissuti minacciosi e piccole speranze di salvezza. La loro relazione si fa simbolo di speranza, cura e resilienza. Bolaño esplora la fragilità e la forza affettiva che unisce padre e figlio di fronte alla devastazione. Con prosa essenziale e cruda, racconta l’amore più puro che può restare quando il mondo crolla.', 'Narrativa', 'Crossroads');
INSERT INTO public.libro VALUES (2013, 10, 'Ifemelu, ragazza nigeriana, emigra negli USA per studiare e vivere l’esperienza americana. Scrive un blog in cui racconta razza, identità, bellezza e appartenenza nel nuovo continente. Nel frattempo, Obinze, rimasto in Nigeria, sogna l’Europa ma finisce per vivere clandestino a Londra. La loro storia d’amore sopravvive a migrazioni, differenze culturali e scelte di vita. Adichie esplora con profondità temi attuali: razzismo, estraneità, nostalgia e potere femminile. Con dialoghi vividi e personaggi a tutto tondo, intreccia storie globali e personali, offrendo una visione acuta sulla dicotomia Africa‑America e sulla ricerca di sé.
', 'Narrativa sociale', 'Americanah');
INSERT INTO public.libro VALUES (1989, 9, 'Il maggiordomo Stevens, fedele servitore a Darlington Hall, riflette sul suo passato tra l’Inghilterra degli anni Trenta e Quaranta. In un viaggio in auto, tra ricordi di un padrone controverso e rifiuti della vita privata, rivela la propria dedizione spietata al dovere, a scapito dei legami umani. Il suo rapporto con Miss Kenton resta intriso di rimpianto e riserve emotive. Ishiguro sottrae lentamente velluti e compassione dal personaggio, mostrando la tragedia di chi sceglie le regole anziché il cuore. Un romanzo di tatto e sottili silenzi, in cui il passato – e ciò che si è rifiutato – detta il presente.
', 'Dramma', 'The Remains of the Day');
INSERT INTO public.libro VALUES (1952, 19, 'Ferito in battaglia da una cannonata, il visconte Medardo di Terralba torna a casa diviso a metà: una parte crudele e spietata, l’altra buona e generosa. Le sue due metà vivono separatamente, creando scompiglio tra la popolazione. Calvino costruisce una favola filosofica allegorica sulla dualità dell’animo umano, riflettendo sul bene, il male e l’identità. Solo quando le due metà si ricongiungono il visconte può tornare completo e umano. Scritto con leggerezza e ironia, il romanzo invita a riflettere sulla complessità morale dell’essere umano, oltre ogni manicheismo.
', 'Fiaba', 'Il visconte dimezzato');
INSERT INTO public.libro VALUES (2002, 18, 'Cuentos completos raccoglie l’intera produzione di racconti di Roberto Bolaño, esplorando i temi ossessivi della sua narrativa: letteratura, giovinezza, esilio, follia e morte. Con uno stile diretto e immaginifico, Bolaño costruisce mondi in cui realtà e invenzione si intrecciano. Personaggi erranti, poeti dimenticati e situazioni surreali danno voce al lato oscuro e affascinante dell’America Latina e dell’Europa. Le storie variano dal noir al realismo magico, dal frammento autobiografico alla critica letteraria. È una summa dell’universo narrativo di Bolaño, dove il racconto breve diventa forma perfetta per contenere l’inquietudine contemporanea.
', 'Racconti', 'Cuentos completos');
INSERT INTO public.libro VALUES (2016, 17, 'Stevens, anziano maggiordomo inglese, intraprende un viaggio che è anche un percorso interiore. Durante il tragitto, riflette sulla sua carriera e sul rapporto con Miss Kenton, l’ex governante del maniero. Devoto al proprio padrone, il signor Darlington, Stevens si rende conto solo troppo tardi di aver sacrificato affetti e giudizio morale per servire fedelmente. Ishiguro, con prosa sobria e introspettiva, indaga il concetto di dignità, rimpianto e repressione emotiva. Il romanzo è un’elegia sulla fine di un’epoca e sulla solitudine di chi ha vissuto una vita intera senza mai concedersi di viverla davvero.
', 'Narrativa storica', 'Quel che resta del giorno');
INSERT INTO public.libro VALUES (1996, 16, 'Ambientato nel Canada ottocentesco, il romanzo segue la storia vera (romanzata) di Grace Marks, domestica accusata di aver partecipato all’omicidio del suo padrone e della governante. Internata in un manicomio, Grace viene interrogata da un giovane medico che cerca di determinarne la colpevolezza. Ma la verità sfugge tra ipnosi, ricordi vaghi e strategie di sopravvivenza. Atwood costruisce un intrigo psicologico tra colpa, innocenza e condizione femminile, mescolando realtà storica e finzione letteraria. Il romanzo esplora i confini tra pazzia e razionalità, tra manipolazione e verità, offrendo un ritratto ambiguo e coinvolgente.
', 'Storico', 'L’altra Grace');
INSERT INTO public.libro VALUES (2007, 15, 'Ispirato a Casa Howard di E.M. Forster, il romanzo racconta le vicende dei Belsey, una famiglia angloamericana alle prese con conflitti razziali, culturali e generazionali. Il padre Howard, professore di estetica bianca e laico, si scontra con il collega Monty Kipps, nero, conservatore e religioso. I figli vivono amori, crisi e ribellioni nel campus universitario. Con ironia brillante e profonda empatia, Zadie Smith riflette su bellezza, ipocrisia accademica, identità e appartenenza. La narrazione si muove tra il comico e il tragico, offrendo uno specchio intelligente delle contraddizioni del multiculturalismo contemporaneo.
', 'Young Adult', 'Della bellezza');
INSERT INTO public.libro VALUES (2020, 14, 'Alice e Mattia sono due “numeri primi gemelli”: vicini ma destinati a non toccarsi mai. Entrambi portano traumi d’infanzia profondi che li rendono incapaci di vivere pienamente le relazioni. Il romanzo segue le loro vite segnate da dolore, isolamento e incomunicabilità, tra infanzia, adolescenza e età adulta. Con stile asciutto e malinconico, Giordano costruisce una storia di solitudini parallele, di ferite invisibili che condizionano ogni scelta. È un racconto toccante sull’incapacità di guarire, ma anche sulla bellezza imperfetta dei legami che, pur senza completarsi, resistono al tempo.
', 'Biografico', 'La solitudine dei numeri primi');
INSERT INTO public.libro VALUES (2014, 13, 'Nel rione povero di Napoli, negli anni ’50, nasce un’amicizia intensa tra Elena Greco e Lila Cerullo. Intelligenti e ambiziose, le due ragazze percorrono strade diverse: Elena prosegue negli studi, Lila resta nel quartiere, tra lavoro e matrimonio precoce. Il romanzo racconta la loro crescita in un contesto violento, patriarcale e stratificato, in cui l’istruzione diventa via di fuga e potere. Con sguardo lucido e appassionato, Ferrante dipinge un rapporto femminile complesso, fatto di ammirazione, rivalità, amore e ombre. L’amica geniale è il primo volume di una saga che ha conquistato il mondo.
', 'Romanzo familiare', 'L''amica geniale');
INSERT INTO public.libro VALUES (2000, 11, 'Partendo da un lutto familiare e da un periodo trascorso in Sri Lanka durante lo tsunami del 2004, Carrère intreccia storie vere di dolore e resilienza. Al centro, la morte di una giovane magistrata francese e il legame umano con i suoi colleghi e amici. Attraverso una narrazione intima e onesta, l’autore riflette sulla perdita, il senso della giustizia, la solidarietà e la capacità di trovare senso nella sofferenza altrui. Vite che non sono la mia è un memoir commosso e lucido che unisce cronaca e confessione, mostrando come la realtà possa superare la finzione.
', 'Biografico', 'Vite che non sono la mia');
INSERT INTO public.libro VALUES (1996, 20, 'Ambientato in un futuro distopico e parodico, Infinite Jest ruota attorno a una misteriosa registrazione video così coinvolgente da rendere gli spettatori incapaci di smettere di guardarla. Intorno a questo fulcro narrativo si intrecciano storie ambientate in un’accademia di tennis e in un centro di recupero per tossicodipendenti. Wallace affronta dipendenza, intrattenimento, disfunzione familiare e alienazione con una prosa densa, digressiva e ironica. Il romanzo è un ritratto caotico ma lucido della società americana e della sua fame di distrazioni. Un’opera monumentale che sfida il lettore con intelligenza e complessità formale.
', 'Postmoderno', 'Infinite Jest');


--
-- TOC entry 3664 (class 0 OID 64479)
-- Dependencies: 225
-- Data for Name: immagini_libro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.immagini_libro VALUES (20, '/uploads/copertine/33f7ed79-c8a1-48af-84cb-a8fe5e3e5f03_9788806232474_0_0_536_0_75.jpg');
INSERT INTO public.immagini_libro VALUES (12, '/uploads/copertine/001b80c9-4c82-4d55-abdd-7f35f37a06bc_714JtZOpChL._UF1000,1000_QL80_.jpg');
INSERT INTO public.immagini_libro VALUES (1, '/uploads/copertine/6554d567-adc6-485b-b47d-b6c0d9642719_9788804668244_0_0_0_0_0.jpg');
INSERT INTO public.immagini_libro VALUES (2, '/uploads/copertine/3f99f07f-f0ba-4e1b-a58a-7e2427d33ec4_Orwell-1984-ed2022-Copertina-A-300x480.jpg');
INSERT INTO public.immagini_libro VALUES (3, '/uploads/copertine/f3d29a38-7cb3-4fb4-add4-c69c8de6a0eb_81xd+qOvR3L._UF1000,1000_QL80_.jpg');
INSERT INTO public.immagini_libro VALUES (5, '/uploads/copertine/ece8f179-32d1-46df-b858-98dbc85336fd_9780143136132.jpg');
INSERT INTO public.immagini_libro VALUES (6, '/uploads/copertine/a245b73b-e644-4725-b746-4d8589d74a25_978880477249HIG-635x905.jpg');
INSERT INTO public.immagini_libro VALUES (7, '/uploads/copertine/1f554885-4d5a-4d2f-9043-dc7d9c80ea9f_61Aa9Yic8AL._UF1000,1000_QL80_.jpg');
INSERT INTO public.immagini_libro VALUES (8, '/uploads/copertine/14a557bc-8772-4975-a72b-c1b31dc8c4da_61yk+kwacxL._UF1000,1000_QL80_.jpg');
INSERT INTO public.immagini_libro VALUES (10, '/uploads/copertine/be1a1b73-0209-4707-ad2f-e02ba0e38777_9788806263249_0_0_536_0_75.jpg');
INSERT INTO public.immagini_libro VALUES (9, '/uploads/copertine/f5460288-a2c0-47ed-977e-2d48325c73e9_71+HFMIHZeL.jpg');
INSERT INTO public.immagini_libro VALUES (19, '/uploads/copertine/a3807140-de81-4ca5-85be-0aae29848dfb_978880477272HIG.webp');
INSERT INTO public.immagini_libro VALUES (18, '/uploads/copertine/1f3dc19f-9732-48ec-94bd-61da9255fd48_71iHE5h6fzL._UF1000,1000_QL80_.jpg');
INSERT INTO public.immagini_libro VALUES (17, '/uploads/copertine/37a201d1-6bda-4c76-b711-a37054e30eb6_9788806229900.1000.jpg');
INSERT INTO public.immagini_libro VALUES (16, '/uploads/copertine/6044cf7c-acff-4f5a-a669-e96d3cac10ba_9788868337377_0_0_0_0_0.jpg');
INSERT INTO public.immagini_libro VALUES (15, '/uploads/copertine/2cbbf905-a19f-46e3-9b7a-20bcfa8b9f62_9788804704072_0_500_0_75.jpg');
INSERT INTO public.immagini_libro VALUES (14, '/uploads/copertine/80973368-bb64-433b-ace9-827cb55c03eb_La_solitudine_dei_numero.webp');
INSERT INTO public.immagini_libro VALUES (13, '/uploads/copertine/51c17d9c-677c-4937-af0f-082f91ed8e3c_cover_9788866320326__id2640_w600_t1531471071__1x.jpg');
INSERT INTO public.immagini_libro VALUES (11, '/uploads/copertine/323ba7bc-bb55-4814-b89b-83d22f71fc53_9788845933950.jpg');
INSERT INTO public.immagini_libro VALUES (4, '/uploads/copertine/884f7f9e-9b3d-4316-9480-c3b697694f2b_81h8IAc5qmL._UF1000,1000_QL80_.jpg');


--
-- TOC entry 3666 (class 0 OID 64490)
-- Dependencies: 227
-- Data for Name: libro_autore; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.libro_autore VALUES (5, 1);
INSERT INTO public.libro_autore VALUES (3, 2);
INSERT INTO public.libro_autore VALUES (5, 3);
INSERT INTO public.libro_autore VALUES (4, 5);
INSERT INTO public.libro_autore VALUES (1, 6);
INSERT INTO public.libro_autore VALUES (18, 7);
INSERT INTO public.libro_autore VALUES (11, 8);
INSERT INTO public.libro_autore VALUES (10, 8);
INSERT INTO public.libro_autore VALUES (7, 10);
INSERT INTO public.libro_autore VALUES (8, 9);
INSERT INTO public.libro_autore VALUES (1, 19);
INSERT INTO public.libro_autore VALUES (12, 18);
INSERT INTO public.libro_autore VALUES (10, 18);
INSERT INTO public.libro_autore VALUES (8, 17);
INSERT INTO public.libro_autore VALUES (20, 17);
INSERT INTO public.libro_autore VALUES (9, 16);
INSERT INTO public.libro_autore VALUES (14, 15);
INSERT INTO public.libro_autore VALUES (17, 15);
INSERT INTO public.libro_autore VALUES (13, 14);
INSERT INTO public.libro_autore VALUES (2, 13);
INSERT INTO public.libro_autore VALUES (20, 11);
INSERT INTO public.libro_autore VALUES (6, 4);
INSERT INTO public.libro_autore VALUES (15, 20);
INSERT INTO public.libro_autore VALUES (11, 12);


--
-- TOC entry 3667 (class 0 OID 64493)
-- Dependencies: 228
-- Data for Name: recensione; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.recensione VALUES (3, '2025-07-05 01:09:45.791207', 1, 14, 1, 'La storia è interessante ma alcuni elementi sembrano messi lì apposta per commuovere. Avrei preferito più naturalezza.', 'Un po’ forzato');
INSERT INTO public.recensione VALUES (3, '2025-07-05 01:11:11.82651', 3, 7, 1, 'L’ambientazione è perfetta, ma le lunghe digressioni rendono difficile seguirlo. Più saggio che romanzo.', 'Bello ma troppo denso');
INSERT INTO public.recensione VALUES (2, '2025-07-05 01:13:10.605352', 4, 4, 1, 'Lento, introspettivo fino allo sfinimento. Alcuni passaggi molto belli, ma nel complesso non mi ha coinvolto.', 'Troppo introspettivo');
INSERT INTO public.recensione VALUES (3, '2025-07-05 01:13:35.137594', 5, 13, 1, 'La scrittura è buona ma la storia mi ha preso solo a tratti. Mi aspettavo più eventi, più azione. Probabilmente non è il mio genere.', 'Sovrastimato');
INSERT INTO public.recensione VALUES (4, '2025-07-05 01:16:16.59432', 6, 16, 2, 'In certi tratti la narrazione è lenta, ma l’introspezione dei personaggi è magistrale. Ti lascia la sensazione di aver davvero conosciuto due persone vere, cresciute accanto a te.', 'Inizio promettente');
INSERT INTO public.recensione VALUES (4, '2025-07-05 01:16:41.935427', 7, 13, 2, 'Va molto oltre la classica storia d’amore. Parla di depressione, perdita, identità. Mi ha sorpreso e mi ha fatto riflettere.', 'Non solo amore');
INSERT INTO public.recensione VALUES (4, '2025-07-05 01:17:15.162243', 8, 7, 2, 'A tratti difficile da seguire, ma ogni pagina offre spunti incredibili. Un romanzo unico nel suo genere.', 'Un viaggio nella conoscenza');
INSERT INTO public.recensione VALUES (4, '2025-07-05 01:17:45.713032', 9, 14, 2, 'L’idea dei numeri primi gemelli è un’immagine perfetta per descrivere la solitudine. Un romanzo moderno e intelligente.', 'Metafora efficace');
INSERT INTO public.recensione VALUES (4, '2025-07-05 01:18:06.851536', 10, 10, 2, 'Si legge in poche ore ma ti resta dentro. La metafora della divisione dell’animo umano è bellissima.', 'Breve ma intenso');
INSERT INTO public.recensione VALUES (5, '2025-07-05 01:19:00.464019', 11, 13, 3, 'La relazione tra Elena e Lila è raccontata con una verità quasi dolorosa. Ho amato come la Ferrante descrive l’ambiente del rione, le sue regole, i limiti e le ambizioni. Un romanzo sincero e potente.', ' Un’amicizia che segna');
INSERT INTO public.recensione VALUES (5, '2025-07-05 01:19:20.615144', 12, 4, 3, 'Un libro delicato, con un’atmosfera ovattata e triste. L’ho trovato profondamente umano, e la figura di Naoko mi è rimasta impressa. Bellissimo.', 'Poesia malinconicaUn libro delicato, con un’atmosfera ovattata e triste. L’ho trovato profondamente umano, e la figura di Naoko mi è rimasta impressa. Bellissimo.');
INSERT INTO public.recensione VALUES (5, '2025-07-05 01:19:44.579826', 13, 7, 3, 'Una lettura impegnativa ma gratificante. La trama gialla si mescola alla filosofia e alla storia medievale. Consigliato a chi cerca qualcosa di profondo.', 'Affascinante e complesso');
INSERT INTO public.recensione VALUES (5, '2025-07-05 01:20:07.604019', 14, 14, 3, 'Una storia toccante che parla di silenzi e distanze. I protagonisti sono scritti con grande sensibilità. Mi ha commosso.', 'Dolorosamente bello');
INSERT INTO public.recensione VALUES (5, '2025-07-05 01:20:45.969698', 15, 19, 3, 'Calvino riesce a dire cose profondissime con parole semplici. Questa storia apparentemente infantile è in realtà molto profonda.', 'Favola filosofica');
INSERT INTO public.recensione VALUES (1, '2025-07-05 01:23:24.324579', 16, 14, 4, 'Avevo alte aspettative ma mi sono annoiato. Il ritmo è lento e la trama sembra quasi ferma. Forse semplicemente non fa per me', 'Non mi ha preso');
INSERT INTO public.recensione VALUES (1, '2025-07-05 01:23:57.60462', 17, 8, 4, 'Speravo in un racconto brillante, ma non ho trovato nessun legame emotivo. Tutto troppo distante e metaforico.', 'Non mi ha detto nulla');
INSERT INTO public.recensione VALUES (1, '2025-07-05 01:24:37.649204', 18, 7, 4, 'Ho dovuto rileggere molte frasi per capirle. La parte filosofica mi ha allontanato dalla trama. Non sono riuscito a finirlo.', 'Troppo complicato');
INSERT INTO public.recensione VALUES (2, '2025-07-05 01:25:16.029063', 19, 6, 4, 'L’idea è intelligente, ma la storia è troppo semplice. Sembrava più una lettura scolastica che un romanzo coinvolgente.', 'Allegoria noiosa');


--
-- TOC entry 3669 (class 0 OID 64511)
-- Dependencies: 230
-- Data for Name: utente_libri_salvati; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utente_libri_salvati VALUES (7, 1);
INSERT INTO public.utente_libri_salvati VALUES (13, 1);
INSERT INTO public.utente_libri_salvati VALUES (11, 1);
INSERT INTO public.utente_libri_salvati VALUES (2, 1);
INSERT INTO public.utente_libri_salvati VALUES (7, 2);
INSERT INTO public.utente_libri_salvati VALUES (9, 2);
INSERT INTO public.utente_libri_salvati VALUES (17, 2);
INSERT INTO public.utente_libri_salvati VALUES (16, 2);
INSERT INTO public.utente_libri_salvati VALUES (11, 2);
INSERT INTO public.utente_libri_salvati VALUES (5, 3);
INSERT INTO public.utente_libri_salvati VALUES (7, 3);
INSERT INTO public.utente_libri_salvati VALUES (8, 3);
INSERT INTO public.utente_libri_salvati VALUES (19, 3);
INSERT INTO public.utente_libri_salvati VALUES (18, 3);
INSERT INTO public.utente_libri_salvati VALUES (16, 3);


--
-- TOC entry 3677 (class 0 OID 0)
-- Dependencies: 218
-- Name: autore_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.autore_seq', 1, true);


--
-- TOC entry 3678 (class 0 OID 0)
-- Dependencies: 219
-- Name: credentials_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.credentials_seq', 51, true);


--
-- TOC entry 3679 (class 0 OID 0)
-- Dependencies: 220
-- Name: libro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.libro_seq', 1, true);


--
-- TOC entry 3680 (class 0 OID 0)
-- Dependencies: 221
-- Name: recensione_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recensione_seq', 201, true);


--
-- TOC entry 3681 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_seq', 51, true);


--
-- TOC entry 3682 (class 0 OID 0)
-- Dependencies: 222
-- Name: utente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.utente_seq', 51, true);


--
-- TOC entry 3671 (class 0 OID 0)
-- Dependencies: 3670 3672
-- Data for Name: 60974; Type: BLOBS; Schema: -; Owner: postgres
--

BEGIN;

SELECT pg_catalog.lo_open('60974', 131072);
SELECT pg_catalog.lowrite(0, '\x62656c6c6973696d6f');
SELECT pg_catalog.lo_close(0);

COMMIT;

-- Completed on 2025-07-05 21:20:26 CEST

--
-- PostgreSQL database dump complete
--

