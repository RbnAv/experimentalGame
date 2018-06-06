package experimentalGame.game.view;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import experimentalGame.game.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FinalScreen implements Initializable {

	@FXML
	private TextArea area1;
	@FXML
	private TextArea area2;

	String nombreEvento = "";
	String narrativa = "";
	String dialogo = "";

	String evento = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			if (Evento.c == 1) {
				evento = "F3";
			}
			else if (Evento.c == 2) {
				evento = "F2";
			}
			else if (Evento.c == 3) {
				evento = "F1";
			}
			else if (Evento.c == 4) {
				evento = "F4";
			}
			evento();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void evento() throws SQLException, ClassNotFoundException {
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg";
		Class.forName(myDriver);
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "silvestre96");
		String query = "SELECT * FROM evento";
		conn.createStatement();
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);

		while (rs.next()) {
			if (rs.getString("nombreEvento") != null) {
				nombreEvento = rs.getString("nombreEvento");
			}
			if (rs.getString("narrativa") != null) {
				narrativa = rs.getString("narrativa");
			}
			if (rs.getString("dialogo") != null) {
				dialogo = rs.getString("dialogo");
			}

			if (nombreEvento.equals(evento)) {
				area1.setText(narrativa);
				area2.setText(dialogo);
			}
		}

		st.close();
	}
	
	public void fSalir(ActionEvent event) {
		System.exit(0);
	}
	
	public void fPrincipal(ActionEvent event) {
		try {
			MainUIController.c=-1;
			MainUIController.cont=0;
			for (int i = 0; i < MainUIController.myArray.length; i++) {
				MainUIController.myArray[i]=null;
			}
			((Node) event.getSource()).getScene().getWindow().hide();
			// se carga el root del archivo fmxl
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/rootStage.fxml"));
			Stage primaryStage = new Stage();
			BorderPane rootStage = (BorderPane) loader.load();
			// Muestra la escena que contiene el diseño de la raíz.
			Scene scene = new Scene(rootStage);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("view/MainScreen.fxml"));
			AnchorPane mainScreen = (AnchorPane) fxmlLoader.load();
			// establece el menú principal en el centro del stage (rootStage)
			rootStage.setCenter(mainScreen);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void fNuevaPartida(ActionEvent event) {

		try {
			MainUIController.c=-1;
			MainUIController.cont=0;
			for (int i = 0; i < MainUIController.myArray.length; i++) {
				MainUIController.myArray[i]=null;
			}
			// create a java mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/rpg";
			Class.forName(myDriver);
			Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "silvestre96");

			// create the java mysql update preparedstatement
			String query = "update escuadron set vitalidadEscuadron = ?";
			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
			preparedStmt.setInt(1, 236);

			// execute the java preparedstatement
			preparedStmt.executeUpdate();
			conn.close();

			// Cerramos esta ventana
			((Node) event.getSource()).getScene().getWindow().hide();

			// Añadimos el fxml y lo cargamos
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("narrativeScreen.fxml"));
			Parent root = (Parent) fxmlLoader.load();

			// Creamos un nuevo Stage (una nueva ventana vacía)
			Stage stage = new Stage();

			// Dentro del Stage añadimos la escena que anteriormente hemos leído y metido en
			// root
			stage.setScene(new Scene(root));

			stage.setResizable(false);

			// Mostramos la ventana
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
