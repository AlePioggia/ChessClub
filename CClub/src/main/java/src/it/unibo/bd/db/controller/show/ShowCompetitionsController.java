package src.it.unibo.bd.db.controller.show;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.it.unibo.bd.db.DBConnection;
import src.it.unibo.bd.db.controller.ControllerMethods;
import src.it.unibo.bd.db.controller.VIEW;
import src.it.unibo.bd.db.models.CompetizioneModel;
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

public class ShowCompetitionsController extends ControllerMethods{

    @FXML
    private TableView<CompetizioneModel> tableView;
    @FXML
    private TableColumn<CompetizioneModel, String> dataAttivita;
    @FXML
    private TableColumn<CompetizioneModel, String> orarioInizio;
    @FXML
    private TableColumn<CompetizioneModel, String> orarioFine;
    @FXML
    private TableColumn<CompetizioneModel, String> codicePartecipazione;
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
    	this.goToSelectedPage(VIEW.DATI_COMPETIZIONE, event);
    }

    @FXML
    void showOnMouseClicked(MouseEvent event) throws SQLException {
    	this.setCells();
    	ObservableList<CompetizioneModel> list = FXCollections.observableArrayList();
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement = connection.createStatement();
    	ResultSet rs = statement.executeQuery("SELECT * FROM competizioni_fra_circoli");
    	while(rs.next()) {
    		list.addAll(new CompetizioneModel(rs.getObject(1).toString(), rs.getObject(2).toString(), 
    											rs.getObject(3).toString(), rs.getObject(4).toString()));
    	}
    	tableView.setItems(list);
    	this.show.setDisable(true);
    }
    
    private void setCells() {
    	this.dataAttivita.setCellValueFactory(new PropertyValueFactory<CompetizioneModel, String>("dataAttivita"));
    	this.orarioInizio.setCellValueFactory(new PropertyValueFactory<CompetizioneModel, String>("orarioInizio"));
    	this.orarioFine.setCellValueFactory(new PropertyValueFactory<CompetizioneModel, String>("orarioFine"));
    	this.codicePartecipazione.setCellValueFactory(new PropertyValueFactory<CompetizioneModel, String>("codicePartecipazione"));
    }

}
