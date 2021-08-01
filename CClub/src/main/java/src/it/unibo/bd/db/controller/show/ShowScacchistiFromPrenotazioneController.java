package src.it.unibo.bd.db.controller.show;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.it.unibo.bd.db.DBConnection;
import src.it.unibo.bd.db.controller.ControllerMethods;
import src.it.unibo.bd.db.controller.VIEW;
import src.it.unibo.bd.db.models.ScacchistaModel;
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

public class ShowScacchistiFromPrenotazioneController extends ControllerMethods{

    @FXML
    private Button backButton;
    @FXML
    private CheckBox show;
    @FXML
    private Button copy;
    @FXML
    private TableView<ScacchistaModel> tableView;
    @FXML
    private TableColumn<ScacchistaModel, String> cf;
    @FXML
    private TableColumn<ScacchistaModel, String> name;
    @FXML
    private TableColumn<ScacchistaModel, String> surname;
    @FXML
    private TableColumn<ScacchistaModel, String> birthDate;
    @FXML
    private TableColumn<ScacchistaModel, String> provenienza;
    @FXML
    private TableColumn<ScacchistaModel, String> elo;
    @FXML
    private TableColumn<ScacchistaModel, String> nPart;
    @FXML
    private TableColumn<ScacchistaModel, String> codSquadra;
    @FXML
    private TableColumn<ScacchistaModel, String> codAbbonamento;

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
    	ObservableList<ScacchistaModel> list = FXCollections.observableArrayList();
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement = connection.createStatement();
    	ResultSet rs = statement.executeQuery("SELECT * FROM scacchisti");
    	while(rs.next()) {
    		list.add(new ScacchistaModel(rs.getObject(1).toString(), rs.getObject(2).toString(), rs.getObject(3).toString(),
    										rs.getObject(4).toString(), rs.getObject(5).toString(), rs.getObject(6).toString(), 
    										rs.getObject(7).toString(), rs.getObject(8).toString(), rs.getObject(9).toString()));
    	}
    	tableView.setItems(list);
    	this.show.setDisable(true);
    }

	private void setCells() {
		cf.setCellValueFactory(new PropertyValueFactory<ScacchistaModel, String>("cf"));
    	this.name.setCellValueFactory(new PropertyValueFactory<ScacchistaModel, String>("name"));
    	this.surname.setCellValueFactory(new PropertyValueFactory<ScacchistaModel, String>("surname"));
    	this.birthDate.setCellValueFactory(new PropertyValueFactory<ScacchistaModel, String>("birthDate"));
    	this.provenienza.setCellValueFactory(new PropertyValueFactory<ScacchistaModel, String>("birthPlace"));
    	this.elo.setCellValueFactory(new PropertyValueFactory<ScacchistaModel, String>("elo"));
    	this.nPart.setCellValueFactory(new PropertyValueFactory<ScacchistaModel, String>("nPart"));
    	this.codSquadra.setCellValueFactory(new PropertyValueFactory<ScacchistaModel, String>("codSquadra"));
    	this.codAbbonamento.setCellValueFactory(new PropertyValueFactory<ScacchistaModel, String>("codAbbonamento"));
	}

}
