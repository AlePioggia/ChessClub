package src.it.unibo.bd.db.controller.show;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.it.unibo.bd.db.DBmethods;
import src.it.unibo.bd.db.controller.ControllerMethods;
import src.it.unibo.bd.db.controller.VIEW;
import src.it.unibo.bd.db.models.BestPlayerModel;
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

public class ShowBestPlayerController extends ControllerMethods{

	private final DBmethods db = new DBmethods();
    @FXML
    private Button backButton;
    @FXML
    private CheckBox show;
    @FXML
    private Button copy;
    @FXML
    private TableView<BestPlayerModel> tableView;
    @FXML
    private TableColumn<BestPlayerModel, String> cf;
    @FXML
    private TableColumn<BestPlayerModel, String> name;
    @FXML
    private TableColumn<BestPlayerModel, String> surname;
    @FXML
    private TableColumn<BestPlayerModel, String> elo;

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
    	ObservableList<BestPlayerModel> list = FXCollections.observableArrayList();
    	ResultSet rs = db
    			.dbCommunication()
    			.executeQuery("SELECT s.CF, s.Nome, s.Cognome, s.Punteggio_elo "
    					+ "FROM SCACCHISTI as s WHERE s.Punteggio_elo IN ( select MAX(s1.Punteggio_elo) from SCACCHISTI as s1 )"
    					+ " ORDER BY s.Cognome ASC");
    	while(rs.next()) {
    		list.add(new BestPlayerModel(rs.getObject(1).toString(), rs.getObject(2).toString(),
    				rs.getObject(3).toString(), rs.getObject(4).toString()));
    	}
    	tableView.setItems(list);
    	this.show.setDisable(true);
    }

    private void setCells() {
    	this.cf.setCellValueFactory(new PropertyValueFactory<BestPlayerModel, String>("cf"));
       	this.name.setCellValueFactory(new PropertyValueFactory<BestPlayerModel, String>("name"));
       	this.surname.setCellValueFactory(new PropertyValueFactory<BestPlayerModel, String>("surname"));
       	this.elo.setCellValueFactory(new PropertyValueFactory<BestPlayerModel, String>("elo"));
       }
}
