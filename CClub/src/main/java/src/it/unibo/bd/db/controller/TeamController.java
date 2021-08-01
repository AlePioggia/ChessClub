package src.it.unibo.bd.db.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import src.it.unibo.bd.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TeamController extends ControllerMethods {

    @FXML
    private TextField birthPlace;
    @FXML
    private Button insertTeam;
    @FXML
    private Button homePage;
    @FXML
    private Button showTeams;
    @FXML
    private CheckBox insertCheckBox;
    @FXML
    private CheckBox deleteButton;
    @FXML
    private DatePicker birthDate;
    @FXML
    private TextField CodiceSquadra;
    @FXML
    private TextField nomeSquadra;
    @FXML
    private TextField cf;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField mail;
    
    @FXML
    void deleteCheckBox(ActionEvent event) {
    	
    }

    @FXML
    void deleteOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void goToHomepage(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.HOME, event);
    }

    @FXML
    void insertMouseClick(MouseEvent event) {

    }
    
    public boolean checkCFlenght() {
		return cf.getText().length() == 16;
    }

    private void startInsertionQueries() throws SQLException {
    	String insertTeam = "INSERT INTO squadre " + "VALUES('" + this.CodiceSquadra.getText() + "', '"
				+ this.nomeSquadra.getText() + "');";
    	String insertReclutatore = "INSERT INTO reclutatori " + "VALUES('" 
				+ this.cf.getText() + "', '"
				+ this.CodiceSquadra.getText() + "', '"
				+ this.name.getText() + "', '"
				+ this.surname.getText() + "', '"
				+ this.mail.getText() + "', '"
				+ this.birthDate.getValue().toString() + "', '"
				+ this.birthPlace.getText() + "')";
				
    	System.out.println(insertTeam);
    	System.out.println(insertReclutatore);
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement = connection.createStatement();
    	statement.executeUpdate(insertTeam);
    	statement.executeUpdate(insertReclutatore);
    }
    
    private void startDeleteQueries() throws SQLException {
    	String deleteReclutatore = "DELETE FROM reclutatori WHERE Codice_squadra = '" + CodiceSquadra.getText() + "';";
    	String deleteSquadra = "DELETE FROM squadre WHERE Codice_squadra = '" + CodiceSquadra.getText() + "';";
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement = connection.createStatement();
    	statement.executeUpdate(deleteReclutatore);
    	statement.executeUpdate(deleteSquadra);
    }
    
    @FXML
    void insertTeamsOnMouseClicked(MouseEvent event) throws SQLException {
    	if (insertCheckBox.isSelected() && !this.deleteButton.isSelected() && checkCFlenght()) {
    		startInsertionQueries();
    	} else if (!insertCheckBox.isSelected() && this.deleteButton.isSelected()) {
    		startDeleteQueries();
    	} else {
    		this.displayErrorOnDialog();
    	}
    }

    @FXML
    void showTeamsListOnClick(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.SHOW_TEAMS_FROM_TEAM, event);
    }

}

