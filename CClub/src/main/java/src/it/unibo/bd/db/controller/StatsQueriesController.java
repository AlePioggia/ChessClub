package src.it.unibo.bd.db.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.it.unibo.bd.db.DBmethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class StatsQueriesController extends ControllerMethods{

	DBmethods db = new DBmethods();
	ObservableList<String> functionalitiesList = FXCollections.observableArrayList("ragazzini e tornei", 
			"% paganti su totale scacchisti", "resoconto", "partecipazioni", "best player");
    @FXML
    private Button queryExecution;
    @FXML
    private Button HomePage;
    @FXML
    private ComboBox<String> comboBox;
    
    @FXML
    void HomePageOnMouseClicked(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.HOME, event);
    }

    @FXML
    void queryExecutionOnMouseClicked(MouseEvent event) throws IOException, SQLException {
    	this.selectPage(comboBox, event, "ragazzini e tornei", VIEW.SHOW_QUERY1);
    	this.selectPage(comboBox, event, "resoconto", VIEW.SHOW_QUERY2);
    	this.selectPage(comboBox, event, "partecipazioni", VIEW.SHOW_QUERY3);
    	this.selectPage(comboBox, event, "best player", VIEW.SHOW_QUERY4);
    	if (comboBox.getSelectionModel().getSelectedItem().equals("% paganti su totale scacchisti")) {
    		this.createDialog();
    	}
    }

    public void createDialog() throws SQLException {
    	Alert alert = new Alert(AlertType.NONE, "result = " + this.getResult() + "%", ButtonType.CLOSE);
		alert.show();
    }
    
    public Integer getResult() throws SQLException {
    ResultSet rs = db
    			.dbCommunication()
    			.executeQuery("select (select count(distinct CF_scacchista) from pagamenti)*100 / count(*) as percentuale_paganti_su_totale\r\n" + 
    					"from scacchisti; ");
    	if (rs.next()) {
    		return rs.getInt(1);
    	} 
    	return 0;
    }
    
    @FXML
    void choiceBoxOnMouseClicked(MouseEvent event) {
    	this.comboBox.setItems(functionalitiesList);
    }

}
