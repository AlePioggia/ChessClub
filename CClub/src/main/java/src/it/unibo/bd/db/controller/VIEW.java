package src.it.unibo.bd.db.controller;

public enum VIEW {
	
	HOME("/HomePage.fxml"),
	ATTIVITA_HOME("/AttivitaHome.fxml"),
	DATI_TORNEO("/InserimentoDatiTorneo.fxml"),
	DATI_COMPETIZIONE("/InserimentoDatiCompetizione.fxml"),
	PRENOTAZIONE("/Prenotazione.fxml"),
	SCACCHISTA("/Scacchista.fxml"),
	TEAM("/Team.fxml"),
	TEAMS_COMPOSITION("/TeamsComposition.fxml"),
	TORNEO("/Torneo.fxml"),
	LEZIONE("/Lezione.fxml"),
	SEMINARIO("/Seminario.fxml"),
	COMPETIZIONE("/Competizione.fxml"),
	SHOW_PARTECIPANTI_TORNEO("/ShowPartecipantiTorneo.fxml"),
	SHOW_TORNEI("/ShowTornei.fxml"),
	SHOW_COMPETIZIONI("/ShowCompetitions.fxml"),
	SHOW_TEAMS_FROM_TEAM("/ShowTeamsFromTeam.fxml"),
	SHOW_TEAMS_FROM_TEAM_COMP("/ShowTeamsFromTeamComp.fxml"),
	SHOW_TEAMS_FROM_COMPETITION("/ShowTeamsFromCompetition.fxml"),
	SHOW_TEAMS_FROM_INS_COMPETITION("/ShowTeamsFromInsCompetizioni.fxml"),
	SHOW_SCACCHISTI_INS_SQUADRA("/ShowScacchistiFromInsInSquadra.fxml"),
	SHOW_SCACCHISTI_FROM_PRENOTAZIONE("/ShowScacchistiFromPrenotazione.fxml"),
	SHOW_PRENOTAZIONI("/ShowPrenotazioniDisponibili.fxml"),
	SHOW_DATES_FROM_COMPETIZIONE("/ShowDatesFromCompetizione.fxml"),
	SHOW_DATES_FROM_LEZIONI("/ShowDatesFromLezioni.fxml"),
	SHOW_DATES_FROM_SEMINARI("/ShowDatesFromSeminari.fxml"),
	SHOW_DATES_FROM_TORNEI("/ShowDatesFromTornei.fxml"),
	SHOW_CLASSIFICA("/ShowClassifica.fxml"),
	SHOW_PRENOTAZIONI_FROM_DATI_TORNEO("/ShowPrenotazioniFromDati.fxml"),
	SHOW_PRENOTATIONS("/ShowPrenotations.fxml"),
	SHOW_QUERY1("/ShowQuery1.fxml"),
	SHOW_QUERY2("/ShowQuery2.fxml"),
	SHOW_QUERY3("/ShowMostPrenotations.fxml"),
	SHOW_QUERY4("/ShowBestPlayer.fxml"),
	QUERIES("/StatsQueries.fxml");
	
	private final String fileName;
	
	
	 /**
     * @param text
     */
    VIEW(final String fileName) {
        this.fileName = fileName;
    }

    /* 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return fileName;
    }
	
}
