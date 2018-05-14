package experimentalGame.game.view;

import java.net.URL;
import java.util.ResourceBundle;

import experimentalGame.game.Main;
import experimentalGame.game.model.Squad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * this class is the controller for the fxml MainUI
 * 
 * @author Ruvek
 *
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

	// Reference to the main application.
	//private Main main;
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
		
		squadTable.getSelectionModel().selectedItemProperty().addListener((observable,
				oldValue, newValue) -> showSquadDetails(newValue));
	}
	
	public void showGreeting(ActionEvent event) {
		//System.out.println("Hola!");
		try{
			 
			//Te lo comento para que  lo comprendamos tú y yo
			//Léeme el source del archivo que te digo fxml y te pongo el path
			 
			FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("narrativeScreen.fxml"));
			Parent root1= (Parent)fxmlLoader.load();
			 
			//Creame un nuevo Stage o para que nos entendamos una nueva ventana windows vacía
			Stage stage= new Stage();
			 
			//Y ahora dentro del Stage me metes la escena que anteriormente hemos leído y metido en root1
			stage.setScene(new Scene(root1));
			 
			// Y ahora le digo que me muestres el stage
			stage.show();
			
			// Cerrar esta ventana
			Stage ventana1= new Stage();
			ventana1.getScene().getWindow();
			ventana1.close();
			}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

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
