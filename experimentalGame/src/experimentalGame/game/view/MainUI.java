package experimentalGame.game.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MainUI implements Initializable {

	@FXML
	private TextArea comando;
	
	String txt = "";
	static int equipo = 0;
	static String sector = "";
	
	CharSequence e1 = "equipo 1";
	CharSequence e2 = "equipo 2";
	CharSequence e3 = "equipo 3";
	
	CharSequence s1 = "A1";
	CharSequence s2 = "A2";
	CharSequence s3 = "A3";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void evento(ActionEvent event) {
		try {
			txt = comando.getText();
			if (txt.contains(e1)) {
				equipo = 1;
			}
			if (txt.contains(e2)) {
				equipo = 2;
			}
			if (txt.contains(e3)) {
				equipo = 3;
			}
			//
			if (txt.contains(s1)) {
				sector = s1.toString();
			}
			if (txt.contains(s2)) {
				sector = s2.toString();
			}
			if (txt.contains(s3)) {
				sector = s3.toString();
			}

			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eventScreen.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
