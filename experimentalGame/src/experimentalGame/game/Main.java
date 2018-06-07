package experimentalGame.game;

import java.io.File;
import java.io.IOException;

import experimentalGame.game.model.Squad;
import experimentalGame.game.view.MainUIController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootStage;

	String musicFile = "./src/Sounds/menu.mp3";

	Media sound = new Media(new File(musicFile).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(sound);

	/**
	 * The data as an observable list of values.
	 */
	private ObservableList<Squad> squadData = FXCollections.observableArrayList();

	/**
	 * contructor of squads with their names
	 */
	public Main() {
		squadData.add(new Squad("Asalto"));
		squadData.add(new Squad("Reconocimiento"));
		squadData.add(new Squad("Apoyo"));

	}

	/**
	 * Returns the data as an observable list of Persons.
	 * 
	 * @return
	 */
	public ObservableList<Squad> getSquadData() {
		return squadData;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HORIZON");
		initRootStage();
		showMainScreen();
		mediaPlayer.play();
		// showMainUI();
		// String musicFile = "./src/Sounds/explosion-1.wav";

		// Media sound = new Media(new File(musicFile).toURI().toString());
		// MediaPlayer mediaPlayer = new MediaPlayer(sound);
		// mediaPlayer.play();

	}

	public static void main(String[] args) {
		launch(args);

	}

	/**
	 * Se inicia el stage "rootStage"
	 */

	public void initRootStage() {
		try {
			// se carga el root del archivo fmxl
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/rootStage.fxml"));
			rootStage = (BorderPane) loader.load();

			// Muestra la escena que contiene el dise�o de la ra�z.
			Scene scene = new Scene(rootStage);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Muestra el men� principal dentro de la vista ra�z
	 */

	public void showMainScreen() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainScreen.fxml"));
			AnchorPane mainScreen = (AnchorPane) loader.load();

			// establece el men� principal en el centro del stage (rootStage)

			rootStage.setCenter(mainScreen);

			// musicFile = "./src/Sounds/intro.mp3";
			//
			// sound = new Media(new File(musicFile).toURI().toString());
			// mediaPlayer = new MediaPlayer(sound);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showMainUI() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainUI.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootStage.setCenter(personOverview);

			// Give the controller access to the main app.
			MainUIController controller = loader.getController();
			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve el stage principal.
	 * 
	 * @return
	 */

	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
