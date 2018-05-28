package experimentalGame.game.view;

import java.net.URL;
import java.util.ResourceBundle;

import experimentalGame.game.Main;
import experimentalGame.game.model.Squad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * this class is the controller for the fxml MainUI
 */

public class MainUIController implements Initializable {

	@FXML
	private TableView<Squad> squadTable;

	@FXML
	private TableColumn<Squad, String> nameColumn;

	@FXML
	private Label vitalityLabel;
	@FXML
	private Label energyLabel;
	@FXML
	private Label fearLabel;

	Main main;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public MainUIController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

		showSquadDetails(null);

		squadTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showSquadDetails(newValue));
	}

	public void nuevaPartida(ActionEvent event) {

		try {
			// Cerramos esta ventana
			((Node) event.getSource()).getScene().getWindow().hide();

			// A�adimos el fxml y lo cargamos
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("narrativeScreen.fxml"));
			Parent root = (Parent) fxmlLoader.load();

			// Creamos un nuevo Stage (una nueva ventana vac�a)
			Stage stage = new Stage();

			// Dentro del Stage a�adimos la escena que anteriormente hemos le�do y metido en
			// root
			stage.setScene(new Scene(root));

			// Mostramos la ventana
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void principal(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			// se carga el root del archivo fmxl
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/rootStage.fxml"));
			Stage primaryStage = new Stage();
			BorderPane rootStage = (BorderPane) loader.load();
			// Muestra la escena que contiene el dise�o de la ra�z.
			Scene scene = new Scene(rootStage);
			primaryStage.setScene(scene);
			primaryStage.show();

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("view/MainScreen.fxml"));
			AnchorPane mainScreen = (AnchorPane) fxmlLoader.load();
			// establece el men� principal en el centro del stage (rootStage)
			rootStage.setCenter(mainScreen);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void opciones(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("optionPanel.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void ayuda(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("helpPanel.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void creditos(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("creditsPanel.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void controles(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("controlsPanel.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void atras(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("optionPanel.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void option2(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void salir(ActionEvent event) {
		System.exit(0);
	}

	/*
	 * public void escogerPersonaje(ActionEvent event) { try { // create our mysql
	 * database connection String myDriver = "org.gjt.mm.mysql.Driver"; String myUrl
	 * = "jdbc:mysql://localhost/mydb"; Class.forName(myDriver); Connection conn =
	 * (Connection) DriverManager.getConnection(myUrl, "root", "studium2017");
	 * 
	 * // our SQL SELECT query. // if you only need a few columns, specify them by
	 * name instead of using "*" String query = "SELECT * FROM personaje";
	 * 
	 * // create the java statement Statement st = (Statement)
	 * conn.createStatement();
	 * 
	 * // execute the query, and get a java resultset ResultSet rs =
	 * st.executeQuery(query);
	 * 
	 * // iterate through the java resultset while (rs.next()) { String
	 * nombrePersonaje = rs.getString("nombrePersonaje"); int vidaPersonaje =
	 * rs.getInt("vidaPersonaje");
	 * 
	 * // Apoyo if (nombrePersonaje.equals("Apoyo")) { int v1 = vidaPersonaje;
	 * lbl.setText("Vida :" + v1); } } st.close(); } catch (Exception e) {
	 * System.err.println("Got an exception! "); System.err.println(e.getMessage());
	 * } }
	 */

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMain(Main main) {
		this.main = main;

		// Add observable list data to the table
		squadTable.setItems(main.getSquadData());
	}

	/**
	 * Fills all text fields to show details about the person. If the specified
	 * person is null, all text fields are cleared.
	 * 
	 * @param person
	 *            the person or null
	 */
	private void showSquadDetails(Squad squad) {
		if (squad != null) {
			// Fill the labels with info from the person object.
			vitalityLabel.setText(Integer.toString(squad.getVitalidad()));
			energyLabel.setText(Integer.toString(squad.getEnergia()));
			fearLabel.setText(Integer.toString(squad.getMiedo()));
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
