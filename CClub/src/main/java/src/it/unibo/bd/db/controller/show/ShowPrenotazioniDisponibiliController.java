package src.it.unibo.bd.db.controller.show;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.it.unibo.bd.db.DBConnection;
import src.it.unibo.bd.db.controller.ControllerMethods;
import src.it.unibo.bd.db.controller.VIEW;
import src.it.unibo.bd.db.models.DataAttivitaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;

public class ShowPrenotazioniDisponibiliController extends ControllerMethods{

    @FXML
    private TableView<DataAttivitaModel> tableView;
    @FXML
    private TableColumn<DataAttivitaModel, String> lezioni;
    @FXML
    private TableColumn<DataAttivitaModel, String> seminari;
    @FXML
    private TableColumn<DataAttivitaModel, String> tornei;
    @FXML
    private TableColumn<DataAttivitaModel, String> competizioni;
    @FXML
    private Button backButton;
    @FXML
    private CheckBox show;
    @FXML
    private Button copy;

    @FXML
    void copyToClipboardOnMouseClicked(MouseEvent event) {
    	final ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(this.tableView.getItems().toString());
        Clipboard.getSystemClipboard().setContent(clipboardContent);
    }

    @FXML
    void goBackOnMouseClicked(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.PRENOTAZIONE, event);
    }

    @FXML
    void showOnMouseClicked(MouseEvent event) throws SQLException {
    	setCells();
    	ObservableList<DataAttivitaModel> list = FXCollections.observableArrayList();
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement = connection.createStatement();
    	ResultSet rsLezioni, rsSeminari, rsTornei, rsCompetizioni;
    	
    	rsLezioni = statement.executeQuery("SELECT Data_attivita FROM lezioni");
    	int i = 1;
    	while(rsLezioni.next()) {
    		list.add(new DataAttivitaModel(rsLezioni.getObject(i).toString(), "", "", ""));
    	}
    	 	
    	rsSeminari =  statement.executeQuery("SELECT Data_attivita FROM seminari");
    	while(rsSeminari.next()) {
    		list.add(new DataAttivitaModel("", rsSeminari.getObject(i).toString(), "", ""));
    	}
    	
    	rsTornei = statement.executeQuery("SELECT Data_attivita FROM tornei");
    	while(rsTornei.next()) {
    		list.add(new DataAttivitaModel("", "", rsTornei.getObject(i).toString(), ""));
    	}
    	
    	rsCompetizioni = statement.executeQuery("SELECT Data_attivita FROM competizioni_fra_circoli");
    	while(rsCompetizioni.next()) {
    		list.add(new DataAttivitaModel("", "", "", rsCompetizioni.getObject(i).toString()));
    	}
    	System.out.println(list.toString());
    	tableView.setItems(list);
    	this.show.setDisable(true);
    }
    
    private void setCells() {
		lezioni.setCellValueFactory(new PropertyValueFactory<DataAttivitaModel, String>("dataLezioni"));
		seminari.setCellValueFactory(new PropertyValueFactory<DataAttivitaModel, String>("dataSeminari"));
		tornei.setCellValueFactory(new PropertyValueFactory<DataAttivitaModel, String>("dataTornei"));
		competizioni.setCellValueFactory(new PropertyValueFactory<DataAttivitaModel, String>("dataCompetizioni"));
	}

}
