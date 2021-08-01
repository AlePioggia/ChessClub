package src.it.unibo.bd.db.controller.show;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.it.unibo.bd.db.DBConnection;
import src.it.unibo.bd.db.controller.ControllerMethods;
import src.it.unibo.bd.db.controller.VIEW;
import src.it.unibo.bd.db.models.TeamModel;
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

public class ShowTeamsControllerFromTeams extends ControllerMethods {

    @FXML
    private TableView<TeamModel> tableView;
    @FXML
    private TableColumn<TeamModel, String> cCodiceSquadra;
    @FXML
    private TableColumn<TeamModel, String> cNomeSquadra;
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
    	this.goToSelectedPage(VIEW.TEAM, event);
    }

    @FXML
    void showOnMouseClicked(MouseEvent event) throws SQLException {
    	cCodiceSquadra.setCellValueFactory(new PropertyValueFactory<TeamModel, String>("teamCode"));
    	cNomeSquadra.setCellValueFactory(new PropertyValueFactory<TeamModel, String>("teamName"));
    	ObservableList<TeamModel> list = FXCollections.observableArrayList();
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement = connection.createStatement();
    	ResultSet rs = statement.executeQuery("SELECT * FROM squadre");
    	while(rs.next()) {
    		list.add(new TeamModel(rs.getObject(1).toString(), rs.getObject(2).toString()));
    	}
    	tableView.setItems(list);
    	this.show.setDisable(true);
    }

}
