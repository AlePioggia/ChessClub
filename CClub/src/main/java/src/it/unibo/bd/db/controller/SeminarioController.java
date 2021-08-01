package src.it.unibo.bd.db.controller;

import java.io.IOException;
import java.sql.SQLException;

import src.it.unibo.bd.db.DBmethods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SeminarioController extends ControllerMethods{

	private DBmethods db = new DBmethods();
    @FXML
    private Button insertValues;
    @FXML
    private Button homePage;
    @FXML
    private Button showSeminari;
    @FXML
    private CheckBox insertCheckBox;
    @FXML
    private CheckBox deleteCheckBox;
    @FXML
    private TextField orarioInizio;
    @FXML
    private TextField orarioFine;
    @FXML
    private TextField argomentoSeminario;
    @FXML
    private TextField cf;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private Button activitiesHome;
    @FXML
    private DatePicker birthDate;
    @FXML
    private DatePicker dataAttivita;

    @FXML
    void goBackToActivitiesHomePage(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.ATTIVITA_HOME, event);
    }

    @FXML
    void goBackToHomePage(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.HOME, event);
    }

    private void startInsertionQueries() throws SQLException {
    	String insertSeminari = "INSERT INTO seminari " + "VALUES('" + dataAttivita.getValue().toString() + "', '"
    			+ cf.getText() + "', "
				+ orarioInizio.getText() + ", "
				+ orarioFine.getText() + ", '"
				+ argomentoSeminario.getText() + "');";
    	String insertEsperti = "INSERT INTO esperti " + "VALUES('" + cf.getText() + "', '"
    			+ name.getText() + "', '"
    			+ surname.getText() + "', '"
    			+ birthDate.getValue().toString() + "');";
    	db.dbCommunication().executeUpdate(insertSeminari);
    	db.dbCommunication().executeUpdate(insertEsperti);
    }
    
    private void startInsertionQueriesWithNoExpert() throws SQLException {
    	String insertSeminari = "INSERT INTO seminari " + "VALUES('" + dataAttivita.getValue().toString() + "', '"
    			+ cf.getText() + "', "
				+ orarioInizio.getText() + ", "
				+ orarioFine.getText() + ", '"
				+ argomentoSeminario.getText() + "');";
    	db.dbCommunication().executeUpdate(insertSeminari);
    }
    
    private void startDeleteQueries() throws SQLException {
    	String deleteLezioni = "DELETE FROM seminari WHERE Data_Attivita = '" + dataAttivita.getValue().toString() + "';";
    	String deleteEsperti = "DELETE FROM esperti WHERE CF = '" + cf.getText() + "';";
    	db.dbCommunication().executeUpdate(deleteLezioni);
    	db.dbCommunication().executeUpdate(deleteEsperti);
    }
    
    public boolean checkCFlenght() {
		return cf.getText().length() == 16;
    }
    
    private boolean checkIfEspertoGiaPresente() throws SQLException {
    	return db.dbCommunication().executeQuery("SELECT * FROM esperti WHERE CF = '" + cf.getText() + "'").next();
    }

    private boolean isEspertoInsertion() {
    	return dataAttivita.getValue() == null;
    }
    
    private void insertEsperto() throws SQLException {
    	db.dbCommunication().executeUpdate("INSERT INTO esperti " + "VALUES('" + cf.getText() + "', '"
    			+ name.getText() + "', '"
    			+ surname.getText() + "', '"
    			+ birthDate.getValue().toString() + "');");
    }
    
    @FXML
    void insertValuesOnMouseClicked(MouseEvent event) throws SQLException {
    	if (	insertCheckBox.isSelected() 
    			&& !this.deleteCheckBox.isSelected()) {
    		if (isEspertoInsertion()) {
    			this.insertEsperto();
    		} else if (!checkIfEspertoGiaPresente() && db.checkDates(dataAttivita.getValue().toString())) {
    			this.startInsertionQueries();
    		} else if(checkIfEspertoGiaPresente() && db.checkDates(dataAttivita.getValue().toString())) {
    			this.startInsertionQueriesWithNoExpert();
    		}
    	} else if (!insertCheckBox.isSelected() && this.deleteCheckBox.isSelected() && checkCFlenght()) {
    		startDeleteQueries();
    	} else {
    		this.displayErrorOnDialog();
    	}
    }

    @FXML
    void showSeminariOnMouseClicked(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.SHOW_DATES_FROM_SEMINARI, event);
    }

}

