package src.it.unibo.bd.db.controller.show;

import java.io.IOException;

import src.it.unibo.bd.db.DBmethods;
import src.it.unibo.bd.db.controller.ControllerMethods;
import src.it.unibo.bd.db.controller.VIEW;
import src.it.unibo.bd.db.models.Query1Model;
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
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowQuery1Controller extends ControllerMethods{

	private final DBmethods db = new DBmethods();
    @FXML
    private Button backButton;
    @FXML
    private CheckBox show;
    @FXML
    private Button copy;
    @FXML
    private TableView<Query1Model> tableView;
    @FXML
    private TableColumn<Query1Model, String> cf;
    @FXML
    private TableColumn<Query1Model, String> name;
    @FXML
    private TableColumn<Query1Model, String> surname;
    @FXML
    private TableColumn<Query1Model, String> birthDate;

    @FXML
    void copyToClipboardOnMouseClicked(MouseEvent event) {
    	final ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(this.tableView.getItems().toString());
        Clipboard.getSystemClipboard().setContent(clipboardContent);
    }

    @FXML
    void goBackOnMouseClicked(MouseEvent event) throws IOException{
    	this.goToSelectedPage(VIEW.QUERIES, event);
    }

    @FXML
    void showOnMouseClicked(MouseEvent event) throws SQLException {
    	this.setCells();
    	ObservableList<Query1Model> list = FXCollections.observableArrayList();
    	ResultSet rs = db
    			.dbCommunication()
    			.executeQuery("SELECT SO.CF, SO.Nome, SO.Cognome, Data_di_nascita\r\n" + 
    					"FROM SCACCHISTI as SO\r\n" + 
    					"WHERE SO.CF IN (\r\n" + 
    					"					SELECT SI.CF\r\n" + 
    					"					FROM SCACCHISTI AS SI, PRENOTAZIONI AS P, TORNEI AS T\r\n" + 
    					"					WHERE SI.CF = P.CF_Scacchista\r\n" + 
    					"					AND T.Data_attivita = P.DataTorneo\r\n" + 
    					"					AND DataTorneo BETWEEN '2021-09-01' AND '2021-12-20'\r\n" + 
    					"                    AND YEAR(Data_di_nascita) > '2000'\r\n" + 
    					"				)\r\n" + 
    					"AND (SO.Nome LIKE \"%A\"  \r\n" + 
    					"OR SO.Nome LIKE \"L%\"\r\n" + 
    					"OR SO.Nome LIKE \"F%\")\r\n" + 
    					"ORDER BY SO.Nome, SO.Cognome ASC;");
    	while(rs.next()) {
    		list.add(new Query1Model(rs.getObject(1).toString(), rs.getObject(2).toString(),
    				rs.getObject(3).toString(), rs.getObject(4).toString()));
    	}
    	tableView.setItems(list);
    	this.show.setDisable(true);
    }
    
   private void setCells() {
	this.cf.setCellValueFactory(new PropertyValueFactory<Query1Model, String>("cf"));
   	this.name.setCellValueFactory(new PropertyValueFactory<Query1Model, String>("name"));
   	this.surname.setCellValueFactory(new PropertyValueFactory<Query1Model, String>("surname"));
   	this.birthDate.setCellValueFactory(new PropertyValueFactory<Query1Model, String>("birthDate"));
   }

}
