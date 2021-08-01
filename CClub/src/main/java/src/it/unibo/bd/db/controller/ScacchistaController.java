package src.it.unibo.bd.db.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import src.it.unibo.bd.db.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ScacchistaController extends ControllerMethods{

    @FXML
    private Button values;
    @FXML
    private Button homepage;
    @FXML
    private CheckBox InsertCheckBox;
    @FXML
    private CheckBox DeleteCheckBox;
    @FXML
    private TextField cfInputText;
    @FXML
    private TextField nameInputText;
    @FXML
    private TextField surnameInputText;
    @FXML
    private TextField birthPlaceInputText;
    @FXML
    private TextField eloInputText;
    @FXML
    private TextField codAbbonamentoInputText;
    @FXML
    private DatePicker birthDateInputText;
    @FXML
    private DatePicker validityDateInputText;

    @FXML
    void deleteOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void goToHomepage(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.HOME, event);
    }

    @FXML
    void insertOnMouseClicked(MouseEvent event) {

    }

    private void startInsertionQueries() throws SQLException {
    	String insertScacchista = "INSERT INTO scacchisti " + "VALUES('" + cfInputText.getText() + "', '"
				+ nameInputText.getText() + "', '"
				+ surnameInputText.getText() + "', '"
				+ birthDateInputText.getValue().toString() + "', '"
				+ birthPlaceInputText.getText() + "', "
				+ eloInputText.getText() + ", "
				+ "0, "
				+ "' ', "
				+ codAbbonamentoInputText.getText() + ")";
    	String insertAbbonamento = "INSERT INTO abbonamenti " + "VALUES('" 
				+ codAbbonamentoInputText.getText() + "', '"
				+ validityDateInputText.getValue().toString() + "', '"
				+ cfInputText.getText() + "')";
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement = connection.createStatement();
    	statement.executeUpdate(insertScacchista);
    	statement.executeUpdate(insertAbbonamento);
    }
    
    private void startDeleteQueries() throws SQLException {
    	String deleteScacchista = "DELETE FROM scacchisti WHERE CF = '" + cfInputText.getText() + "';";
    	String deleteAbbonamento = "DELETE FROM abbonamenti WHERE CF_Scacchista = '" + cfInputText.getText() + "';";
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement = connection.createStatement();
    	statement.executeUpdate(deleteScacchista);
    	statement.executeUpdate(deleteAbbonamento);
    }
    
    public boolean checkCFlenght() {
		return cfInputText.getText().length() == 16;
    }
    
    @FXML
    void insertValues(MouseEvent event) throws SQLException {
    	if (InsertCheckBox.isSelected() && !this.DeleteCheckBox.isSelected() && checkCFlenght()) {
    		startInsertionQueries();
    	} else if (!InsertCheckBox.isSelected() && this.DeleteCheckBox.isSelected() && checkCFlenght()) {
    		startDeleteQueries();
    	} else {
    		this.displayErrorOnDialog();
    	}
    }
    

}
