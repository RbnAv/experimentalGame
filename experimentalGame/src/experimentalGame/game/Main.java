package experimentalGame.game;

import java.io.IOException;

import experimentalGame.game.model.Squad;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootStage;

	String musicFile = "Sounds/menu.mp3";
	Media sound;
	MediaPlayer mediaPlayer;
	
	static String documentBase;

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
		// initRootStage();
		// showMainScreen();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.getIcons().add(new Image("/img/titulo.jpg"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();

			documentBase = getHostServices().getDocumentBase();
			sound = new Media(documentBase + musicFile);
			mediaPlayer = new MediaPlayer(sound);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// showMainUI();
		mediaPlayer.play();
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
			loader.setLocation(Main.class.getResource("rootStage.fxml"));
			rootStage = (BorderPane) loader.load();

			// Muestra la escena que contiene el diseño de la raíz.
			Scene scene = new Scene(rootStage);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error initRootStage");
		}
	}

	/**
	 * Muestra el menú principal dentro de la vista raíz
	 */

	public void showMainScreen() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("MainScreen.fxml"));
			AnchorPane mainScreen = (AnchorPane) loader.load();

			// establece el menú principal en el centro del stage (rootStage)

			rootStage.setCenter(mainScreen);

			String documentBase = getHostServices().getDocumentBase();
			sound = new Media(documentBase + musicFile);
			mediaPlayer = new MediaPlayer(sound);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error showMainScreen");
		}
	}

	public void showMainUI() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("MainUI.fxml"));
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
