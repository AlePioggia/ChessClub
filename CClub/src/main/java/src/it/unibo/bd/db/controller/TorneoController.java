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

public class TorneoController extends ControllerMethods{

	
	private DBmethods db = new DBmethods();
    @FXML
    private Button insertValuesButton;
    @FXML
    private Button homepage;
    @FXML
    private Button showTornei;
    @FXML
    private CheckBox insertCheckBox;
    @FXML
    private CheckBox deleteCheckBox;
    @FXML
    private Button activitiesHomePage;
    @FXML
    private TextField orarioInizio;
    @FXML
    private TextField orarioFine;
    @FXML
    private TextField eloRange;
    @FXML
    private TextField time;
    @FXML
    private TextField incremento;
    @FXML
    private DatePicker dataAttivita;
    @FXML
    void dataAttivitaOnMouseClicked(MouseEvent event) {
    	
    }

    @FXML
    void deleteCBOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void goBackToActivitiesHome(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.ATTIVITA_HOME, event);
    }

    @FXML
    void goBackToHomepage(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.HOME, event);
    }

    @FXML
    void insertCBOnMouseClicked(MouseEvent event) {

    }

    private void startInsertionQueries() throws SQLException {
    	String insertTorneo = "INSERT INTO tornei " + "VALUES('" + dataAttivita.getValue().toString() + "', "
				+ orarioInizio.getText() + ", "
				+ orarioFine.getText() + ", "
				+ eloRange.getText() + ", "
				+ time.getText() + ", "
				+ incremento.getText() +  ");";
    	db.dbCommunication().executeUpdate(insertTorneo);
    }
    
    private void startDeleteQueries() throws SQLException {
    	String deleteTorneo = "DELETE FROM tornei WHERE Data_attivita = '" + dataAttivita.getValue().toString() + "';";
    	db.dbCommunication().executeUpdate(deleteTorneo);
    }
    
    private boolean checkOrari() {
    	return Integer.parseInt(orarioInizio.getText()) < Integer.parseInt(orarioFine.getText());
    }
    
    @FXML
    void insertValuesOnMouseClicked(MouseEvent event) throws SQLException {
    	if (insertCheckBox.isSelected() && !this.deleteCheckBox.isSelected() && db.checkDates(dataAttivita.toString())
    			&& checkOrari()) {
    		startInsertionQueries();
    	} else if (!insertCheckBox.isSelected() && this.deleteCheckBox.isSelected()) {
    		startDeleteQueries();
    	} else {
    		this.displayErrorOnDialog();
    	}
    }
    
    @FXML
    void showTorneiOnMouseClicked(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.SHOW_DATES_FROM_TORNEI, event);
    }

}
