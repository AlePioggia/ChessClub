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

public class LezioneController extends ControllerMethods{

	private DBmethods db = new DBmethods();
    @FXML
    private Button insertValues;
    @FXML
    private Button homePage;
    @FXML
    private Button showLessons;
    @FXML
    private CheckBox insertCheckBox;
    @FXML
    private CheckBox deleteCheckBox;
    @FXML
    private TextField orarioInizio;
    @FXML
    private TextField orarioFine;
    @FXML
    private TextField argomentoLezione;
    @FXML
    private TextField cf;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField codiceProva;
    @FXML
    private TextField difficulty;
    @FXML
    private TextField numeroQuesiti;
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
    	String insertLezioni = "INSERT INTO lezioni " + "VALUES('" + dataAttivita.getValue().toString() + "', '"
    			+ cf.getText() + "', "
				+ orarioInizio.getText() + ", "
				+ orarioFine.getText() + ", '"
				+ argomentoLezione.getText() + "');";
    	String insertDocenti = "INSERT INTO docenti " + "VALUES('" + cf.getText() + "', '"
    			+ name.getText() + "', '"
    			+ surname.getText() + "', '"
    			+ birthDate.getValue().toString() + "');";
    	String insertProve = "INSERT INTO prove_di_autovalutazione VALUES('" + codiceProva.getText() + "', '"
    			+ cf.getText() + "', '"
    			+ difficulty.getText() + "', '"
    			+ numeroQuesiti.getText() + "');";
    	db.dbCommunication().executeUpdate(insertLezioni);
    	db.dbCommunication().executeUpdate(insertDocenti);
    	if(!difficulty.getText().isEmpty() && !numeroQuesiti.getText().isEmpty()) {
    		db.dbCommunication().executeUpdate(insertProve);
    	}
    }
    
    private void insertDocente() throws SQLException {
    	db.dbCommunication().executeUpdate("INSERT INTO docenti " + "VALUES('" + cf.getText() + "', '"
    			+ name.getText() + "', '"
    			+ surname.getText() + "', '"
    			+ birthDate.getValue().toString() + "');");
    }
    
    private boolean isDocenteInsertion() {
    	return dataAttivita.getValue() == null ;
    }
    
    private void startInsertionQueriesWithNoDocente() throws SQLException {
    	String insertLezioni = "INSERT INTO lezioni " + "VALUES('" + dataAttivita.getValue().toString() + "', '"
    			+ cf.getText() + "', "
				+ orarioInizio.getText() + ", "
				+ orarioFine.getText() + ", '"
				+ argomentoLezione.getText() + "');";
    	String insertProve = "INSERT INTO prove_di_autovalutazione VALUES('" + codiceProva.getText() + "', '"
    			+ cf.getText() + "', '"
    			+ difficulty.getText() + "', '"
    			+ numeroQuesiti.getText() + "');";
    	db.dbCommunication().executeUpdate(insertLezioni);
    	if(!difficulty.getText().isEmpty() && !numeroQuesiti.getText().isEmpty()) {
    		db.dbCommunication().executeUpdate(insertProve);
    	}
    }
    
    private void startDeleteQueries() throws SQLException {
    	String deleteLezioni = "DELETE FROM lezioni WHERE Data_Attivita = '" + dataAttivita.getValue().toString() + "';";
    	String deleteDocenti = "DELETE FROM docenti WHERE CF = '" + cf.getText() + "';";
    	String deleteProve = "DELETE FROM prove WHERE CF_Docente = '" + cf.getText() + "';";
    	db.dbCommunication().executeUpdate(deleteLezioni);
    	db.dbCommunication().executeUpdate(deleteDocenti);
    	if (!difficulty.getText().isEmpty() && !numeroQuesiti.getText().isEmpty()) {
    		db.dbCommunication().executeUpdate(deleteProve);
    	}
    }
    
    public boolean checkCFlenght() {
		return cf.getText().length() == 16;
    }
    
    private boolean checkIfDocenteGiaPresente() throws SQLException {
    	return db.dbCommunication().executeQuery("SELECT * FROM docenti WHERE CF = '" + cf.getText() + "'").next();
    }
    
    @FXML
    void insertValuesOnMouseClicked(MouseEvent event) throws SQLException {
    	if (	insertCheckBox.isSelected() && !this.deleteCheckBox.isSelected() && checkCFlenght() ) { 
    		if(this.isDocenteInsertion()) {
    			this.insertDocente();
    		} else if (!checkIfDocenteGiaPresente() && db.checkDates(dataAttivita.getValue().toString())) {
    			this.startInsertionQueries();
    		} else if (checkIfDocenteGiaPresente() && db.checkDates(dataAttivita.getValue().toString())) {
    			this.startInsertionQueriesWithNoDocente();
    		}
    			
    	} else if (!insertCheckBox.isSelected() && this.deleteCheckBox.isSelected() && checkCFlenght()) {
    		startDeleteQueries();
    	} else {
    		this.displayErrorOnDialog();
    	}
    }

    @FXML
    void showLessonsOnMouseClicked(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.SHOW_DATES_FROM_LEZIONI, event);	
    }

}
