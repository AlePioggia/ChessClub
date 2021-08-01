package src.it.unibo.bd.db.controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public abstract class ControllerMethods {
	
	public void goToSelectedPage(final VIEW fxmlFileName, final MouseEvent event) throws IOException {
		
		Platform.runLater(() -> {
			Parent page = null;
			try {
				page = FXMLLoader.load(getClass().getResource(fxmlFileName.toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	Scene scene = new Scene(page);
	    	//This line gets the stage information
	    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
	    	window.setScene(scene);
	    	window.show();
	    	
		});
		
    }
	
	public void selectPage(ComboBox<String> comboBox, MouseEvent event,
			String selectedItemName, VIEW fxmlFileName) throws IOException {
		if (comboBox.getSelectionModel().getSelectedItem().equals(selectedItemName)) {
			this.goToSelectedPage(fxmlFileName, event);
		}
	}
	
	
		public void displayErrorOnDialog() throws SQLException {
	    	Alert alert = new Alert(AlertType.ERROR, "inserimento non andato a buon fine, controllare i paramentri", ButtonType.CLOSE);
			alert.show();
	    }
	

	
	
}
