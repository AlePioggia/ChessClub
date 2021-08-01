package src.it.unibo.bd.db.controller.show;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.it.unibo.bd.db.DBmethods;
import src.it.unibo.bd.db.controller.ControllerMethods;
import src.it.unibo.bd.db.controller.VIEW;
import src.it.unibo.bd.db.models.Query2Model;
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

public class ShowQuery2Controller extends ControllerMethods{

	private DBmethods db = new DBmethods();
    @FXML
    private Button backButton;
    @FXML
    private CheckBox show;
    @FXML
    private Button copy;
    @FXML
    private TableView<Query2Model> tableView;
    @FXML
    private TableColumn<Query2Model, String> cf;
    @FXML
    private TableColumn<Query2Model, String> name;
    @FXML
    private TableColumn<Query2Model, String> surname;
    @FXML
    private TableColumn<Query2Model, Integer> totaleVersamenti;
    @FXML
    private TableColumn<Query2Model, Integer> totaleSpeso;

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
    	ObservableList<Query2Model> list = FXCollections.observableArrayList();
    	ResultSet rs = db
    			.dbCommunication()
    			.executeQuery("select CF_Scacchista,s.Nome, s.Cognome, COUNT(*) AS TOTALE_VERSAMENTI, SUM(Importo) as Totale_speso\r\n" + 
    					"from PAGAMENTI AS P, SCACCHISTI AS s\r\n" + 
    					"where s.CF = P.CF_Scacchista\r\n" + 
    					"group by CF_Scacchista\r\n" + 
    					"HAVING Totale_speso >= (\r\n" + 
    					"							select AVG(Importo + 10)\r\n" + 
    					"                            from pagamenti\r\n" + 
    					"						)\r\n" + 
    					"AND TOTALE_VERSAMENTI > 1\r\n" + 
    					"order by Totale_speso DESC;");
    	while(rs.next()) {
    		list.add(new Query2Model(rs.getObject(1).toString(), rs.getObject(2).toString(),
    				rs.getObject(3).toString(), rs.getObject(4).toString(),
    				rs.getObject(5).toString()));
    	}
    	tableView.setItems(list);
    	this.show.setDisable(true);
    }

    private void setCells() {
    	this.cf.setCellValueFactory(new PropertyValueFactory<Query2Model, String>("cf"));
       	this.name.setCellValueFactory(new PropertyValueFactory<Query2Model, String>("name"));
       	this.surname.setCellValueFactory(new PropertyValueFactory<Query2Model, String>("surname"));
       	this.totaleVersamenti.setCellValueFactory(new PropertyValueFactory<Query2Model, Integer>("totaleVersamenti"));
       	this.totaleSpeso.setCellValueFactory(new PropertyValueFactory<Query2Model, Integer>("totaleSpeso"));
       }
    
}
