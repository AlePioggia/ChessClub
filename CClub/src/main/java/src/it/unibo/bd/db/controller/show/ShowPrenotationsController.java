package src.it.unibo.bd.db.controller.show;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import src.it.unibo.bd.db.DBConnection;
import src.it.unibo.bd.db.controller.ControllerMethods;
import src.it.unibo.bd.db.controller.VIEW;
import src.it.unibo.bd.db.models.PrenotationsModel;

public class ShowPrenotationsController extends ControllerMethods{

    @FXML
    private TableView<PrenotationsModel> tableView;

    @FXML
    private TableColumn<PrenotationsModel, String> cf;

    @FXML
    private TableColumn<PrenotationsModel, String> dataTorneo;

    @FXML
    private TableColumn<PrenotationsModel, String> dataLezione;

    @FXML
    private TableColumn<PrenotationsModel, String> dataSeminario;

    @FXML
    private TableColumn<PrenotationsModel, String> dataCompetizione;

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
    	this.goToSelectedPage(VIEW.ATTIVITA_HOME, event);
    }

    @FXML
    void showOnMouseClicked(MouseEvent event) throws SQLException {
    	this.setCells();
    	ObservableList<PrenotationsModel> list = FXCollections.observableArrayList();
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement =  connection.createStatement();
    	ResultSet rs = statement.executeQuery("SELECT * from prenotazioni");
    	while(rs.next()) {
    		list.add(new PrenotationsModel(rs.getObject(1).toString(), rs.getObject(2).toString(),
    				rs.getObject(3).toString(), rs.getObject(4).toString(), rs.getObject(5).toString()));
    	}
    	tableView.setItems(list);
    	this.show.setDisable(true);
    }

    private void setCells() {
    	cf.setCellValueFactory(new PropertyValueFactory<PrenotationsModel, String>("cf"));
		dataTorneo.setCellValueFactory(new PropertyValueFactory<PrenotationsModel, String>("dataTorneo"));
		dataLezione.setCellValueFactory(new PropertyValueFactory<PrenotationsModel, String>("dataLezione"));
		dataSeminario.setCellValueFactory(new PropertyValueFactory<PrenotationsModel, String>("dataSeminario"));
		dataCompetizione.setCellValueFactory(new PropertyValueFactory<PrenotationsModel, String>("dataCompetizione"));
    }
    
}

