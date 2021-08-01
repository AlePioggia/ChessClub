package src.it.unibo.bd.db.controller;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.it.unibo.bd.db.DBmethods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class InserimentoDatiTorneoController extends ControllerMethods{

	private DBmethods db = new DBmethods();
    @FXML
    private Button insertValues;
    @FXML
    private Button homePage;
    @FXML
    private DatePicker dataTorneo;
    @FXML
    private Button activities;
    @FXML
    private TextField cfProprioTavolo;
    @FXML
    private TextField cfTavoloAvversario;
    @FXML
    private TextField puntiProprioTavolo;
    @FXML
    private TextField puntiTavoloAvversario;
    @FXML
    private TextField codiceSfida;
    @FXML
    private CheckBox insertCheckBox;
    @FXML
    private CheckBox deleteCheckBox;
    @FXML
    private Button showPrenotazioni;

    @FXML
    void goBackToActivitiesMenu(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.ATTIVITA_HOME, event);
    }

    @FXML
    void goBackToHomepage(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.HOME, event);
    }

    public boolean checkCFlenght(String codiceFiscale) {
		return codiceFiscale.length() == 16;
    }
    
    private void startInsertionQueries() throws SQLException {
    	String insertSfidaInCasa = "INSERT into sfide_al_proprio_tavolo VALUES(' " 
    			+ this.codiceSfida.getText() + "', '"
    			+ this.puntiProprioTavolo.getText() + "', '"
    			+ this.cfProprioTavolo.getText() + "');";
    	String insertSfidaInTrasferta = "INSERT into sfide_al_tavolo_avversario VALUES(' " 
    			+ this.codiceSfida.getText() + "', '"
    			+ this.puntiTavoloAvversario.getText() + "', '"
    			+ this.cfTavoloAvversario.getText() + "');";
    	String insertSfida = "INSERT into sfide VALUES(' "
    			+ this.codiceSfida.getText() + "', '"
    			+ this.dataTorneo.getValue().toString() + "');";
    	db.dbCommunication().executeUpdate(insertSfidaInCasa);
    	db.dbCommunication().executeUpdate(insertSfidaInTrasferta);
    	db.dbCommunication().executeUpdate(insertSfida);
    }
    
    private String deleteTuples(final String table) {
    	return "DELETE from " + table + " WHERE codice_sfida = '" + this.codiceSfida;
    }
    
    private void startDeleteQueries() throws SQLException {
    	db.dbCommunication().executeUpdate(this.deleteTuples("sfide"));
    	db.dbCommunication().executeUpdate(this.deleteTuples("sfide_al_proprio_tavolo"));
    	db.dbCommunication().executeUpdate(this.deleteTuples("sfide_al_tavolo_avversario"));
    }
    
    private boolean checkSubscription() throws SQLException {
    	final String check = "SELECT * FROM scacchisti as s, prenotazioni as p "
    			+ "WHERE s.CF = p.CF_SCACCHISTA AND p.DataTorneo = '" + this.dataTorneo.getValue().toString() + "' ;";
    	ResultSet rs = db.dbCommunication().executeQuery(check);
    	return rs.next();
    }
    
    @FXML
    void insertValuesOnMouseClicked(MouseEvent event) throws SQLException {
    	if (	insertCheckBox.isSelected() 
    			&& !this.deleteCheckBox.isSelected() 
    			&& this.checkCFlenght(this.cfProprioTavolo.getText())
    			&& this.checkSubscription()) {
    		startInsertionQueries();
    	} else if (!insertCheckBox.isSelected() 
    			&& this.deleteCheckBox.isSelected() 
    			&& checkCFlenght(this.cfTavoloAvversario.getText())) {
    		startDeleteQueries();
    	} else {
    		this.displayErrorOnDialog();
    	}
    }

    @FXML
    void showDateTorneo(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.SHOW_PRENOTAZIONI_FROM_DATI_TORNEO, event);
    }

}
