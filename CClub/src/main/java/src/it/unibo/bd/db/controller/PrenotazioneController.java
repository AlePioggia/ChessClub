package src.it.unibo.bd.db.controller;

import java.io.IOException;
import java.sql.SQLException;

import src.it.unibo.bd.db.DBmethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PrenotazioneController extends ControllerMethods{

	private DBmethods db = new DBmethods();
	private ObservableList<String> functionalitiesList = FXCollections.observableArrayList("torneo", 
			"competizione","lezione", "seminario" );
    @FXML
    private Button valuesButton;
    @FXML
    private Button HomePageButton;
    @FXML
    private Button elencoScacchisti;
    @FXML
    private CheckBox insertCheckBox;
    @FXML
    private CheckBox deleteCheckBox;
    @FXML
    private TextField codicePagamento;
    @FXML
    private TextField importo;
    @FXML
    private DatePicker dataPagamento;
    @FXML
    private TextField codiceRichiesta;
    @FXML
    private Button activities;
    @FXML
    private TextField cf;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private DatePicker dataAttivita;
    @FXML
    private Button orariButton;
    
    @FXML
    void comboBoxOnMouseClicked(MouseEvent event) {
    	this.comboBox.setItems(functionalitiesList);
    }

    @FXML
    void deleteOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void elencoScacchistiOnMouseClicked(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.SHOW_SCACCHISTI_FROM_PRENOTAZIONE, event);
    }

    @FXML
    void goBackToActivities(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.ATTIVITA_HOME, event);
    }

    @FXML
    void goToHomepage(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.HOME, event);
    }

    public String increaseNumPartecipazioni() {
    	return "UPDATE scacchisti "
        		+ "SET Numero_di_partecipazioni = Numero_di_partecipazioni + 1 "
        		+ "WHERE CF = '" + cf.getText() + "';";
    }
    
    public String decreaseNumPartecipazioni() {
    	return "UPDATE scacchisti "
        		+ "SET Numero_di_partecipazioni = Numero_di_partecipazioni - 1 "
        		+ "WHERE CF = '" + cf.getText() + "';";
    }
    
    private boolean checkDate() throws SQLException {
    	DBmethods db = new DBmethods();
    	return !db.dbCommunication().executeQuery("select * from prenotazioni where exists( "
    			+ "select * from prenotazioni where DataTorneo = '" + this.dataAttivita + "' " +
    			"OR DataLezione = '" + this.dataAttivita + "' OR "
    			+ "DataSeminario = '" + this.dataAttivita + "' OR "
    			+ "DataCompetizione = '" + this.dataAttivita + "')").next();
    }
    
    private void startTorneiInsertionQueries() throws SQLException {
    	String insertTorneo = "INSERT INTO prenotazioni " + "VALUES('" + cf.getText() + "', '"
    															+ dataAttivita.getValue().toString() +"' , ' ', ' ', ' ');";
    	db.dbCommunication().executeUpdate(insertTorneo);
    	db.dbCommunication().executeUpdate(this.increaseNumPartecipazioni());
    }
    
    private void startCompetizioniInsertionQueries() throws SQLException {
    	String insertCompetizioni = "INSERT INTO prenotazioni " + "VALUES('" + cf.getText() + "', ' ', ' ', ' ', '" 
    															+ dataAttivita.getValue().toString() + "');";
    	db.dbCommunication().executeUpdate(insertCompetizioni);
    	db.dbCommunication().executeUpdate(this.increaseNumPartecipazioni());
    }
    
    private void startSeminariInsertionQueries() throws SQLException {
    	String insertSeminari = "INSERT INTO prenotazioni " + "VALUES('" + cf.getText() + "', ' ', ' ', '"
    															+dataAttivita.getValue().toString() + "', ' ');";
    	String insertPagamento = "INSERT INTO pagamenti VALUES ('"
    														+ codicePagamento.getText() + "', '"
    														+ importo.getText() + "', '"
    														+ dataPagamento.getValue().toString() + "', '"
    														+ cf.getText() + "');";
    	String insertRichiesta = "INSERT INTO richieste VALUES(' " + codiceRichiesta.getText() + "',' ',"
    			+ " '" + dataAttivita.getValue().toString() + "');";
    	db.dbCommunication().executeUpdate(insertSeminari);
    	db.dbCommunication().executeUpdate(insertPagamento);
    	db.dbCommunication().executeUpdate(this.increaseNumPartecipazioni());
    	db.dbCommunication().executeUpdate(insertRichiesta);
    }
    
    private void startLezioniInsertionQueries() throws SQLException {
    	String insertLezioni = "INSERT INTO prenotazioni " + "VALUES('" + cf.getText() + "', ' ', '"
															+ dataAttivita.getValue().toString() + "', ' ', ' ');"; 
    	String insertPagamento = "INSERT INTO pagamenti VALUES ('"
    			+ codicePagamento.getText() + "', '"
    			+ importo.getText() + "', '"
    			+ dataPagamento.getValue().toString() + "', '"
    			+ cf.getText() + "');";
    	String insertRichiesta = "INSERT INTO richieste VALUES(' " + codiceRichiesta.getText() + "', '"
    			+ dataAttivita.getValue().toString() + "', ' ');";
		db.dbCommunication().executeUpdate(insertLezioni);
		db.dbCommunication().executeUpdate(insertPagamento);
		db.dbCommunication().executeUpdate(this.increaseNumPartecipazioni());
		db.dbCommunication().executeUpdate(insertRichiesta);
    }
    
    private void startDeleteQueries(final String data) throws SQLException {
    	String deletePrenotazione = "DELETE FROM prenotazioni WHERE " + data +  " = '" + dataAttivita.getValue().toString() + "';";
    	db.dbCommunication().executeUpdate(deletePrenotazione);
    	db.dbCommunication().executeUpdate(this.decreaseNumPartecipazioni());
    }
    
    private boolean checkTeamCode() throws SQLException {
    	return db.dbCommunication()
    			.executeQuery("SELECT * FROM Scacchisti WHERE CF = '" + cf.getText() + "'"
    					+ "AND Codice_squadra != ' ';")
    			.next();
    }
    
    private boolean checkTorneoEloRange() throws SQLException {
    	return db.dbCommunication()
    			.executeQuery("SELECT  * FROM scacchisti "
    					+ "WHERE CF = '" + this.cf.getText() + "' "
    					+ "AND Punteggio_elo <= "
    					+ "(SELECT Elo_Range FROM tornei WHERE Data_attivita = '" + this.dataAttivita.getValue().toString() + "');")
    			.next();
    }
    
    @FXML
    void insertValues(MouseEvent event) throws SQLException {
    	if (this.checkInsertion()) {
    		if ( this.checkTableName("torneo") && this.checkDate() && this.checkTorneoEloRange()) {
    			this.startTorneiInsertionQueries();
    		} else if ( this.checkTableName("competizione") && this.checkTeamCode() && this.checkDate()) {
    			this.startCompetizioniInsertionQueries();
    		} else if ( this.checkTableName("lezione") && this.checkDate()) {
    			this.startLezioniInsertionQueries();
    		} else if (this.checkTableName("seminario") && this.checkDate()) {
    			this.startSeminariInsertionQueries();
    		} else {
    			this.displayErrorOnDialog();
    			System.out.println(this.checkDate());
    			System.out.println(this.checkTorneoEloRange());
    			System.out.println("SELECT * FROM scacchisti "
    					+ "WHERE CF = '" + this.cf.getText() + "' "
    					+ "AND Punteggio_elo <= "
    					+ "(SELECT Elo_Range FROM tornei WHERE Data_attivita = '" + this.dataAttivita.getValue().toString() + "');");
    			System.out.println(this.dataAttivita);
    		}
    	} else if (this.checkDelete()) {
    		if ( this.checkTableName("torneo") && this.checkDate()) {
    			this.startDeleteQueries("DataTorneo");
    		} else if ( this.checkTableName("competizione") && this.checkDate()) {
    			this.startDeleteQueries("DataCompetizione");
    		} else if ( this.checkTableName("lezione") && this.checkDate()) {
    			this.startDeleteQueries("DataLezione");
    		} else if (this.checkTableName("seminario") && this.checkDate()) {
    			this.startDeleteQueries("DataSeminario");
    		}
    	} else {
    		this.displayErrorOnDialog();
    	}
    }
    
    private boolean checkTableName(final String tableName) {
    	 return this.comboBox.getSelectionModel().getSelectedItem().equals(tableName)
    			&& this.checkCFlenght();
    }
    
    public boolean checkCFlenght() {
		return cf.getText().length() == 16;
    }

    private boolean checkInsertion() {
    	return this.insertCheckBox.isSelected() && !this.deleteCheckBox.isSelected();
    }
    
    private boolean checkDelete() {
    	return this.deleteCheckBox.isSelected() && !this.insertCheckBox.isSelected();
    }
    
    @FXML
    void insertValuesOnMouseClicked(MouseEvent event) throws SQLException {
    }

    @FXML
    void showOrariDisponibili(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.SHOW_PRENOTAZIONI, event);
    }
   
}
