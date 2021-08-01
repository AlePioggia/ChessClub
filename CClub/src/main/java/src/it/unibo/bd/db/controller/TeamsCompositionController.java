package src.it.unibo.bd.db.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import src.it.unibo.bd.db.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

public class TeamsCompositionController extends ControllerMethods{

    @FXML
    private Button values;
    @FXML
    private Button Homepage;
    @FXML
    private Button scacchistiList;
    @FXML
    private Button teamsList;
    @FXML
    private CheckBox insertionCheckBox;
    @FXML
    private CheckBox deleteCheckBox;
    @FXML
    private TextField cf;
    @FXML
    private TextField codSquadra;
    
    @FXML
    void deleteCheckBoxOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void goToHomepage(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.HOME, event);
    }

    public boolean checkCFlenght() {
		return cf.getText().length() == 16;
    }

    private void startInsertionQueries() throws SQLException {
    	String updateScacchista = "UPDATE scacchisti SET Codice_squadra = '" + codSquadra.getText() + "' + '00000000'" +
    								" WHERE CF = '" + cf.getText() + "';";
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement = connection.createStatement();
    	statement.executeUpdate(updateScacchista);
    	statement.close();
    }
    
    private void startDeleteQueries() throws SQLException {
    	String updateScacchista = "UPDATE scacchisti as s SET Codice_squadra = ' ' "  +
				"WHERE CF = '" + cf.getText() + "';";
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement = connection.createStatement();
    	statement.executeUpdate(updateScacchista);
    	statement.close();
    }
    
    @FXML
    void insertCheckBoxOnMouseClicked(MouseEvent event) {
    	
    }

    @FXML
    void insertValues(MouseEvent event) throws SQLException {
    	if (insertionCheckBox.isSelected() && !this.deleteCheckBox.isSelected() && checkCFlenght()) {
    		startInsertionQueries();
    	} else if (!insertionCheckBox.isSelected() && this.deleteCheckBox.isSelected()) {
    		startDeleteQueries();
    	} else {
    		this.displayErrorOnDialog();
    	}
    }

    @FXML
    void showScacchistiOnMouseClick(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.SHOW_SCACCHISTI_INS_SQUADRA, event);
    }

    @FXML
    void showTeamsOnMouseClick(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.SHOW_TEAMS_FROM_TEAM_COMP, event);
    }

}

