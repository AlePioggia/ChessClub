package src.it.unibo.bd.db.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class HomePageController extends ControllerMethods{

	ObservableList<String> functionalitiesList = FXCollections.observableArrayList("inserisci scacchista", "attività", "inserisci squadra",
			"inserisci membri nella squadra", "sondaggi statistici");
	
    @FXML
    private ComboBox<String> choices = new ComboBox<String>(functionalitiesList);

    @FXML
    void choicesOnMouseClicked(MouseEvent event) {
    	choices.setItems(functionalitiesList);
    }

    @FXML
    void homeConfirmOnMouseClicked(MouseEvent event) throws IOException {
    	this.selectPage(this.choices, event, "inserisci scacchista", VIEW.SCACCHISTA);
   	 	this.selectPage(this.choices, event, "attività", VIEW.ATTIVITA_HOME);
   	 	this.selectPage(this.choices, event, "inserisci membri nella squadra", VIEW.TEAMS_COMPOSITION);
   	 	this.selectPage(this.choices, event, "inserisci squadra", VIEW.TEAM);
   	 	this.selectPage(this.choices, event, "sondaggi statistici", VIEW.QUERIES);
    }

}
