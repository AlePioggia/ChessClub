package src.it.unibo.bd.db.controller.show;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.it.unibo.bd.db.DBConnection;
import src.it.unibo.bd.db.controller.ControllerMethods;
import src.it.unibo.bd.db.controller.VIEW;
import src.it.unibo.bd.db.models.TorneiModel;
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

public class ShowTorneiController extends ControllerMethods{

    @FXML
    private TableView<TorneiModel> tableView;
    @FXML
    private TableColumn<TorneiModel, String> dataAttivita;
    @FXML
    private TableColumn<TorneiModel, String> orarioInizio;
    @FXML
    private TableColumn<TorneiModel, String> orarioFine;
    @FXML
    private TableColumn<TorneiModel, String> eloRange;
    @FXML
    private TableColumn<TorneiModel, String> tempo;
    @FXML
    private TableColumn<TorneiModel, String> incremento;
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
    	this.goToSelectedPage(VIEW.DATI_TORNEO, event);
    }

    @FXML
    void showOnMouseClicked(MouseEvent event) throws SQLException {
    	this.setCells();
    	ObservableList<TorneiModel> list = FXCollections.observableArrayList();
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement = connection.createStatement();
    	ResultSet rs = statement.executeQuery("SELECT * FROM tornei");
    	while(rs.next()) {
    		list.add(new TorneiModel(rs.getObject(1).toString(), rs.getObject(2).toString(),
    									rs.getObject(3).toString(), rs.getObject(4).toString(),
    									rs.getObject(5).toString(), rs.getObject(6).toString()));
    	}
    	tableView.setItems(list);
    	this.show.setDisable(true);
    }
    
    private void setCells() {
		this.dataAttivita.setCellValueFactory(new PropertyValueFactory<TorneiModel, String>("dataAttivita"));
    	this.orarioInizio.setCellValueFactory(new PropertyValueFactory<TorneiModel, String>("orarioInizio"));
    	this.orarioFine.setCellValueFactory(new PropertyValueFactory<TorneiModel, String>("orarioFine"));
    	this.eloRange.setCellValueFactory(new PropertyValueFactory<TorneiModel, String>("eloRange"));
    	this.tempo.setCellValueFactory(new PropertyValueFactory<TorneiModel, String>("tempo"));
    	this.incremento.setCellValueFactory(new PropertyValueFactory<TorneiModel, String>("incremento"));
	}

}


