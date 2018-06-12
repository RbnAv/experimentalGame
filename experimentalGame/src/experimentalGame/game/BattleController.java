package experimentalGame.game;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class BattleController {

	@FXML
	private Button ataque1 = new Button();
	@FXML
	private Button ataque2 = new Button();
	@FXML
	private Button botiquin = new Button();
	@FXML
	private Button granada = new Button();
	@FXML
	private Button habilidad1 = new Button();
	@FXML
	private Button habilidad2 = new Button();
	@FXML
	private Button habilidad3 = new Button();
	@FXML
	private TextArea consola = new TextArea();

	public BattleController() {

	}

	public void iniciarCombate() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("battle.fxml"));
		Parent root;
		try {
			root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}






