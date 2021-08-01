package src.it.unibo.bd.db.controller.show;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.it.unibo.bd.db.DBmethods;
import src.it.unibo.bd.db.controller.ControllerMethods;
import src.it.unibo.bd.db.controller.VIEW;
import src.it.unibo.bd.db.models.MostPrenotationsModel;
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

public class ShowMostPrenotationsController extends ControllerMethods{

	private final DBmethods db = new DBmethods();
    @FXML
    private Button backButton;
    @FXML
    private CheckBox show;
    @FXML
    private Button copy;
    @FXML
    private TableView<MostPrenotationsModel> tableView;
    @FXML
    private TableColumn<MostPrenotationsModel, String> cf;
    @FXML
    private TableColumn<MostPrenotationsModel, String> name;
    @FXML
    private TableColumn<MostPrenotationsModel, String> surname;
    @FXML
    private TableColumn<MostPrenotationsModel, String> nPart;

    @FXML
    void copyToClipboardOnMouseClicked(MouseEvent event) {
    	final ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(this.tableView.getItems().toString());
        Clipboard.getSystemClipboard().setContent(clipboardContent);
    }

    @FXML
    void goBackOnMouseClicked(MouseEvent event) throws IOException {
    	this.goToSelectedPage(VIEW.QUERIES, event);
    }

    @FXML
    void showOnMouseClicked(MouseEvent event) throws SQLException {
    	this.setCells();
    	ObservableList<MostPrenotationsModel> list = FXCollections.observableArrayList();
    	ResultSet rs = db
    			.dbCommunication()
    			.executeQuery("SELECT s.CF, s.Nome, s.Cognome, s.Numero_di_partecipazioni "
    					+ "FROM SCACCHISTI as s WHERE s.Numero_di_Partecipazioni IN "
    					+ "( SELECT MAX(s1.Numero_di_partecipazioni) from scacchisti as s1)");
    	while(rs.next()) {
    		list.add(new MostPrenotationsModel(rs.getObject(1).toString(), rs.getObject(2).toString(),
    				rs.getObject(3).toString(), rs.getObject(4).toString()));
    	}
    	tableView.setItems(list);
    	this.show.setDisable(true);
    }

    private void setCells() {
    	this.cf.setCellValueFactory(new PropertyValueFactory<MostPrenotationsModel, String>("cf"));
       	this.name.setCellValueFactory(new PropertyValueFactory<MostPrenotationsModel, String>("name"));
       	this.surname.setCellValueFactory(new PropertyValueFactory<MostPrenotationsModel, String>("surname"));
       	this.nPart.setCellValueFactory(new PropertyValueFactory<MostPrenotationsModel, String>("prenotations"));
    }
    
}
