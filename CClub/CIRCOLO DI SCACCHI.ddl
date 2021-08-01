-- *********************************************
-- * Standard SQL generation                   *
-- *-------------------------------------------*
-- * Generator date:  Jul 12 2010              *
-- * Generation date: Tue Jun 15 09:22:24 2021 *
-- *********************************************


-- Database Section
-- ________________

-- DBSpace Section
-- _______________


-- Tables Section
-- _____________

create DATABASE circolo_scacchi;

create table ABBONAMENTI (
     Codice_abbonamento char(8) PRIMARY KEY,
     Data_inizio_validita date not null,
     CF_Scacchista references SCACCHISTA(CF) not null);

create table RICHIESTE (
     CodiceRichiesta char(8) PRIMARY KEY,
     DataLezione date references LEZIONE(Data_attivita),
     DataSeminario date references SEMINARIO(Data_attivita));

create table COMPETIZIONI_FRA_CIRCOLI (
     Data_attivita date PRIMARY KEY,
     OrarioInizio int(5) not null,
     OrarioFine int(5) not null,
     CodicePartecipazione char(8) references PARTECIPAZIONE(Codice_partecipazione));

create table COMPILAZIONI (
     Codice_prova char(8) PRIMARY KEY,
     CF char(16) references SCACCHISTA(CF) );

create table DOCENTI (
     CF char(16) PRIMARY KEY,
     Nome char(25) not null,
     Cognome char(25) not null,
     Data_di_nascita date not null);

create table ESPERTI (
     CF char(16) PRIMARY KEY,
     Nome char(25) not null,
     Cognome char(25) not null,
     Data_di_nascita date not null);

create table LEZIONI (
     Data_attivita date PRIMARY KEY,
     CF_docente char(16) references DOCENTE(CF),
     OrarioInizio char(10) not null,
     OrarioFine char(10) not null,
     Argomento_lezione varchar(30) not null);

create table SFIDE (
     Codice_sfida char(8) PRIMARY KEY,
     DataTorneo date references TORNEO(Data_attivita));

create table PAGAMENTI (
     Codice_pagamento char(8) PRIMARY KEY,
     Importo int(4) not null,
     Data_pagamento date not null,
     CF_Scacchista char(16) references SCACCHISTA(CF));

create table PARTECIPAZIONI (
     Codice_partecipazione char(8) PRIMARY KEY,
     Esito char(10) not null,
     Codice_squadra char(8) references SQUADRA(Codice_squadra));

   create table PRENOTAZIONI (
        CF_Scacchista char(16) references SCACCHISTA(CF),
        DataTorneo date references TORNEO(Data_attivita),
        DataLezione date references LEZIONE(Data_attivita),
        DataSeminario date references SEMINARIO(Data_attivita),
        DataCompetizione date references COMPETIZIONE_FRA_CIRCOLI(Data_attivita));

create table PROVE_DI_AUTOVALUTAZIONE (
     Codice_prova char(8) PRIMARY KEY,
     CF_Docente char(16) references DOCENTE(Data_attivita),
     Difficolta char(1) not null,
     Numero_quesiti char(3) not null);

create table RECLUTATORI (
     CF char(16) PRIMARY KEY,
     Codice_squadra char(8) references SQUADRA(Codice_squadra),
     Nome char(25) not null,
     Cognome char(25) not null,
     E_Mail char(40) not null,
     Data_di_nascita char(10) not null,
     provenienza char(25) not null);

create table SCACCHISTI (
     CF char(16) PRIMARY KEY,
     Nome char(25) not null,
     Cognome char(25) not null,
     Data_di_nascita date not null,
     Provenienza char(25) not null,
     Punteggio_elo char(4) not null,
     Numero_di_partecipazioni varchar(4) not null,
     Codice_squadra char(8) references SQUADRA(Codice_squadra),
     Codice_Abbonamento char(8) references SQUADRA(Codice_abbonamento));

create table SEMINARI (
     Data_attivita date PRIMARY KEY,
     CF_Esperto char(16) not null references ESPERTO(CF),
     OrarioInizio int(5) not null,
     OrarioFine int(5) not null,
     Argomento_seminario char(40) not null);

create table Sfide_al_proprio_tavolo (
     Codice_sfida char(8) references SFIDA(Codice_sfida),
     Punteggio_acquisito int(1) not null,
     CF_Scacchista char(16) not null references SCACCHISTA(CF));

create table Sfide_al_tavolo_avversario (
     Codice_sfida char(8) references SFIDA(Codice_sfida),
     Punteggio_acquisito int(1) not null,
     CF_Scacchista char(16) not null references SCACCHISTA(CF));

create table SQUADRE (
     Codice_squadra char(8) PRIMARY KEY,
     Nome_squadra char(40) not null );

create table TORNEI (
     Data_attivita daet PRIMARY KEY,
     OrarioInizio int(5) not null,
     OrarioFine int(5) not null,
     Elo_range int(4) not null,
     Tempo_a_disposizione int(5) not null,
     incremento_secondi int(5) not null);
