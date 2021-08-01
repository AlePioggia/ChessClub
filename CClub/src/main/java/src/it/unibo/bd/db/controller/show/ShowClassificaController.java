package src.it.unibo.bd.db.controller.show;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.it.unibo.bd.db.DBConnection;
import src.it.unibo.bd.db.controller.ControllerMethods;
import src.it.unibo.bd.db.controller.VIEW;
import src.it.unibo.bd.db.models.ClassificaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;

public class ShowClassificaController extends ControllerMethods{

    @FXML
    private TableView<ClassificaModel> tableView;
    @FXML
    private TableColumn<ClassificaModel, String> cf;
    @FXML
    private TableColumn<ClassificaModel, String> nome;
    @FXML
    private TableColumn<ClassificaModel, String> cognome;
    @FXML
    private TableColumn<ClassificaModel, String> elo;
    @FXML
    private TableColumn<ClassificaModel, Integer> punteggio;
    @FXML
    private Button backButton;
    @FXML
    private CheckBox show;
    @FXML
    private Button copy;
    @FXML
    private DatePicker dataTorneo;

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
    	ObservableList<ClassificaModel> list = FXCollections.observableArrayList();
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement =  connection.createStatement();
    	ResultSet rs = statement.executeQuery("SELECT c.CF_Scacchista,s.Nome, s.Cognome,s.Punteggio_elo, SUM(Punteggio_acquisito) AS Punti_totali\r\n" + 
    			"FROM \r\n" + 
    			"(\r\n" + 
    			"	SELECT * FROM sfide_al_proprio_tavolo union\r\n" + 
    			"    SELECT * FROM sfide_al_tavolo_avversario\r\n" + 
    			") as c, scacchisti as s, sfide as sf\r\n" + 
    			"WHERE s.CF = c.CF_Scacchista\r\n" +
    			"AND sf.Codice_sfida = c.Codice_sfida\r\n" + 
    			"AND sf.DataTorneo = '" + dataTorneo.getValue().toString() + "'\r\n" + 
    			"GROUP BY c.CF_Scacchista\r\n" + 
    			"ORDER BY Punti_totali DESC;");
    	while(rs.next()) {
    		list.add(new ClassificaModel(rs.getObject(1).toString(), rs.getObject(2).toString(),
    				rs.getObject(3).toString(), rs.getObject(4).toString(),
    				Integer.parseInt(rs.getObject(5).toString())));
    	}
    	tableView.setItems(list);
    	this.show.setDisable(true);
    }
    
    private void setCells() {
    	cf.setCellValueFactory(new PropertyValueFactory<ClassificaModel, String>("cf"));
    	this.nome.setCellValueFactory(new PropertyValueFactory<ClassificaModel, String>("name"));
    	this.cognome.setCellValueFactory(new PropertyValueFactory<ClassificaModel, String>("surname"));
    	this.elo.setCellValueFactory(new PropertyValueFactory<ClassificaModel, String>("elo"));
    	this.punteggio.setCellValueFactory(new PropertyValueFactory<ClassificaModel, Integer>("punteggio"));
    }

}

