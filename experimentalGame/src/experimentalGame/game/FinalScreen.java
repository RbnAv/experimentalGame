package experimentalGame.game;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
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
			} else if (Evento.c == 2) {
				evento = "F2";
			} else if (Evento.c == 3) {
				evento = "F1";
			} else if (Evento.c == 4) {
				evento = "F4";
			}
			evento();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void evento() throws SQLException, ClassNotFoundException {
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg?useSSL=false";
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
			MainUIController.c = -1;
			MainUIController.cont = 0;
			MainUIController.items.clear();
			MainUIController.items.addAll("- Sector A:", "A1", "A2", "A3", "A4", "A5", "A6", "- Sector B:", "B1", "B2",
					"B3", "- Sector C:", "C1", "C2", "- Sector D:", "D1", "D2", "D3", "D4", "- Sector B-D:", "B-D1");

			for (int i = 0; i < MainUIController.myArray.length; i++) {
				MainUIController.myArray[i] = null;
			}
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

	public void fNuevaPartida(ActionEvent event) {

		try {
			MainUIController.c = -1;
			MainUIController.cont = 0;
			MainUIController.items.clear();
			MainUIController.items.addAll("- Sector A:", "A1", "A2", "A3", "A4", "A6", "- Sector B:", "B1", "B2", "B3",
					"- Sector C:", "C1", "C2", "- Sector D:", "D1", "D2", "D3", "D4", "- Sector B-D:", "B-D1");

			for (int i = 0; i < MainUIController.myArray.length; i++) {
				MainUIController.myArray[i] = null;
			}

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/rpg?useSSL=false";
			Class.forName(myDriver);
			Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "silvestre96");

			String query = "update escuadron set vitalidadEscuadron = ?";
			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
			preparedStmt.setInt(1, 236);
			preparedStmt.executeUpdate();
			conn.close();

			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("narrativeScreen.fxml"));
			Parent root = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.getIcons().add(new Image("/img/icon.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);

			// Mostramos la ventana
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
