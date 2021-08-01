package src.it.unibo.bd.db.controller;
import src.it.unibo.bd.db.DBmethods;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CompetizioneController extends ControllerMethods{

	private DBmethods db = new DBmethods();
    @FXML
    private Button insertButton;
    @FXML
    private Button HomePage;
    @FXML
    private Button showDate;
    @FXML
    private CheckBox insertValue;
    @FXML
    private TextField orarioInizio;
    @FXML
    private TextField orarioFine;
    @FXML
    private CheckBox deleteValue;
    @FXML
    private Button activitiesHome;
    @FXML
    private DatePicker dataAttivita;
    @FXML
    private TextField codPartecipazione;

    @FXML
    void deleteValueOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void goBackToActivitiesHome(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.ATTIVITA_HOME, event);
    }

    @FXML
    void goToHomePage(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.HOME, event);
    }

    private void startInsertionQueries() throws SQLException {
    	String insertCompetizioni = "INSERT INTO competizioni_fra_circoli " + "VALUES('" + dataAttivita.getValue().toString() + "', "
				+ orarioInizio.getText() + ", "
				+ orarioFine.getText() + ", '"
				+ codPartecipazione.getText() + "');";
    	db.dbCommunication().executeUpdate(insertCompetizioni);
    }
    
    private void startDeleteQueries() throws SQLException {
    	String deleteCompetizioni = "DELETE FROM competizioni_fra_circoli WHERE Data_Attivita = '" + dataAttivita.getValue().toString() + "';";
    	db.dbCommunication().executeUpdate(deleteCompetizioni);
    }
    
    private boolean checkOrari() {
    	return Integer.parseInt(orarioInizio.getText()) < Integer.parseInt(orarioFine.getText());
    }
    
    @FXML
    void insertButtonOnMouseClicked(MouseEvent event) throws SQLException {
    	if (	insertValue.isSelected() 
    			&& !this.deleteValue.isSelected() && db.checkDates(dataAttivita.getValue().toString())
    			&& checkOrari()) {
    		startInsertionQueries();
    	} else if (!insertValue.isSelected() && this.deleteValue.isSelected()) {
    		startDeleteQueries();
    	} else {
    		this.displayErrorOnDialog();
    	}
    }

    @FXML
    void insertValueOnMouseClicked(MouseEvent event) {

    }

    //errore di copiatura, mostra le DATE non le squadre
    @FXML
    void showTeamsOnMouseClicked(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.SHOW_DATES_FROM_COMPETIZIONE, event);
    }

}
