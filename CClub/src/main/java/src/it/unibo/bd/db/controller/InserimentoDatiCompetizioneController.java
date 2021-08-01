package src.it.unibo.bd.db.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.it.unibo.bd.db.DBmethods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class InserimentoDatiCompetizioneController extends ControllerMethods{

	private DBmethods db = new DBmethods();
    @FXML
    private Button insertValues;
    @FXML
    private Button homePage;
    @FXML
    private Button activities;
    @FXML
    private TextField esito;
    @FXML
    private TextField codiceSquadra;
    @FXML
    private TextField codicePartecipazione;
    @FXML
    private CheckBox insertCheckBox;
    @FXML
    private CheckBox deleteCheckBox;
    @FXML
    private Button showTeams;
    @FXML
    private Button showCompetizioni;
    
    @FXML
    void goBackToActivitiesMenu(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.ATTIVITA_HOME, event);
    }

    @FXML
    void goBackToHomepage(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.HOME, event);
    }

    private void startInsertionQueries() throws SQLException {
    	String insertCompetizioni = "INSERT INTO partecipazioni " + "VALUES('" + codicePartecipazione.getText() + "', '"
				+ esito.getText() + "', '"
				+ codiceSquadra.getText() + "');";
    	db.dbCommunication().executeUpdate(insertCompetizioni);
    }
    
    private void startDeleteQueries() throws SQLException {
    	String deleteCompetizioni = "DELETE FROM prenotazioni WHERE Codice_partecipazione = '" 
    			+ codicePartecipazione.getText() + "';";
    	db.dbCommunication().executeUpdate(deleteCompetizioni);
    }
    
    //Controlla 
    private boolean checkTotalTeamMembers() throws SQLException {
    	String check = "SELECT COUNT(*) "
    			+ "FROM scacchisti as s, prenotazioni as p "
    			+ "WHERE s.CF = p.CF_SCACCHISTA "
    			+ "AND s.Codice_squadra = '" + codiceSquadra.getText() + "' "
				+ "AND p.DataCompetizione = ( SELECT Data_attivita FROM competizioni_fra_circoli "
												+ " WHERE codicePartecipazione = '" + codicePartecipazione.getText() + "') "
				+ "GROUP BY p.DataCompetizione;"; 
    	ResultSet rs = db.dbCommunication().executeQuery(check);
    	if(rs.next()) {
    		return rs.getInt(1) >= 4
    				&& rs.getInt(1) <= 8;
    	}
    	return false;
    }
    
    @FXML
    void insertValuesOnMouseClicked(MouseEvent event) throws SQLException {
    	if ( this.insertCheckBox.isSelected() && !this.deleteCheckBox.isSelected()
    			&& this.checkTotalTeamMembers()) {
    		this.startInsertionQueries();
    	} else if (!this.insertCheckBox.isSelected() && this.deleteCheckBox.isSelected()) {
    		this.startDeleteQueries();
    	} else {
    		this.displayErrorOnDialog();
    	}
    }

    @FXML
    void showCompetizioniOnMouseClicked(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.SHOW_COMPETIZIONI, event);
    }

    @FXML
    void showTeamsOnMouseClicked(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.SHOW_TEAMS_FROM_INS_COMPETITION, event);
    }

}

