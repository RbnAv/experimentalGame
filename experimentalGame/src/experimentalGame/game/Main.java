package experimentalGame.game;

import java.io.IOException;

import experimentalGame.game.model.Squad;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootStage;

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
		// showMainUI();

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
