package src.it.unibo.bd.db.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class AttivitaHomeController extends ControllerMethods{

	ObservableList<String> functionalitiesList = FXCollections.observableArrayList("prenotazione", "torneo", 
			"inserimento risultati torneo",
			"competizione","lezione", "seminario",
			"inserimento risultati competizione",
			"classifica torneo",
			"mostra prenotazioni");
	
    @FXML
    private Button choiceConfirmation;

    @FXML
    private Button activitiesHomePage;

    @FXML
    private ComboBox<String> comboBoxChoices = new ComboBox<String>(functionalitiesList);

    @FXML
    void choiceConfOnMouseClicked(MouseEvent event) throws IOException {
    	this.selectPage(comboBoxChoices, event, "prenotazione", VIEW.PRENOTAZIONE);
    	this.selectPage(comboBoxChoices, event, "torneo", VIEW.TORNEO);
    	this.selectPage(comboBoxChoices, event, "inserimento risultati torneo", VIEW.DATI_TORNEO);
    	this.selectPage(comboBoxChoices, event, "competizione", VIEW.COMPETIZIONE);
    	this.selectPage(comboBoxChoices, event, "lezione", VIEW.LEZIONE);
    	this.selectPage(comboBoxChoices, event, "seminario", VIEW.SEMINARIO);
    	this.selectPage(comboBoxChoices, event, "inserimento risultati competizione", VIEW.DATI_COMPETIZIONE);
    	this.selectPage(comboBoxChoices, event, "classifica torneo", VIEW.SHOW_CLASSIFICA);
    	this.selectPage(comboBoxChoices, event, "mostra prenotazioni", VIEW.SHOW_PRENOTATIONS);
    	
    }

    @FXML
    void comboBoxOnMouseClicked(MouseEvent event)  {
    	comboBoxChoices.setItems(this.functionalitiesList);
    }

    @FXML
    void goToHomepage(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.HOME, event);
    }

}
