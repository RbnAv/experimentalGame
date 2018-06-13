package experimentalGame.game;

import java.net.URL;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import experimentalGame.game.model.Squad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.image.Image;
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

	static int cont = 0;

	static String[] myArray = new String[100];
	static List<String> palabra = Arrays.asList(myArray);
	static int c = -1;

	static ObservableList<String> items = FXCollections.observableArrayList("- Sector A:", "A1", "A2", "A3", "A4", "A5",
			"A6", "- Sector B:", "B1", "B2", "B3", "- Sector C:", "C1", "C2", "- Sector D:", "D1", "D2", "D3", "D4",
			"- Sector B-D:", "B-D1");

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
			// Creamos la conexión
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/rpg?useSSL=false";
			Class.forName(myDriver);
			Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "silvestre96");

			// Ponemos lo que va a hacer en la base de datos (un update)
			String query = "update escuadron set vitalidadEscuadron = ?";
			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
			preparedStmt.setInt(1, 236);
			preparedStmt.executeUpdate();
			conn.close();

			// Cerramos esta ventana
			((Node) event.getSource()).getScene().getWindow().hide();

			// Añadimos el fxml y lo cargamos
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("narrativeScreen.fxml"));
			Parent root = (Parent) fxmlLoader.load();

			// Creamos un nuevo Stage (una nueva ventana vacía)
			Stage stage = new Stage();
			stage.getIcons().add(new Image("/img/icon.png"));

			// Dentro del Stage añadimos la escena que anteriormente hemos metido en root
			stage.setScene(new Scene(root));

			stage.setResizable(false);

			// Mostramos la ventana
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void principal(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.getIcons().add(new Image("/img/icon.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();
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
			stage.getIcons().add(new Image("/img/icon.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
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
			stage.getIcons().add(new Image("/img/icon.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void mAyuda(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("helpPanel2.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.getIcons().add(new Image("/img/icon.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
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
			stage.getIcons().add(new Image("/img/icon.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void mCreditos(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("creditsPanel2.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.getIcons().add(new Image("/img/icon.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
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
			stage.getIcons().add(new Image("/img/icon.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void mControles(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("controlsPanel2.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.getIcons().add(new Image("/img/icon.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
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
			stage.getIcons().add(new Image("/img/icon.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void mAtras(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("optionPanel2.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.getIcons().add(new Image("/img/icon.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
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
			stage.getIcons().add(new Image("/img/icon.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void salir(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMain(Main main) {
		this.main = main;

		// Add observable list data to the table
		// squadTable.setItems(main.getSquadData());
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
