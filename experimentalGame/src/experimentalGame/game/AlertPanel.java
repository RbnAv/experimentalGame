package experimentalGame.game;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertPanel implements Initializable {

	@FXML
	private Label lblGanador;

	int idEscuadron;
	int vitalidad;
	String v = "";
	String v2 = "";
	String v3 = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (Battle.vit <= 0) {
			lblGanador.setText("El enemigo ha ganado la batalla");
		} else {
			lblGanador.setText("El escuadrón ha ganado la batalla");
		}
	}

	public void continuar(ActionEvent event) throws IOException {

		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/rpg?useSSL=false";
			Class.forName(myDriver);
			Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "studium2017");
			String query = "SELECT * FROM escuadron";
			conn.createStatement();
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				idEscuadron = rs.getInt("idEscuadron");
				String nombre = rs.getString("nombreEscuadron");
				vitalidad = rs.getInt("vitalidadEscuadron");
				if (nombre.equals("Escuadrón A")) {
					v = String.valueOf(vitalidad);
				}
				if (nombre.equals("Escuadrón B")) {
					v2 = String.valueOf(vitalidad);
				}
				if (nombre.equals("Escuadrón C")) {
					v3 = String.valueOf(vitalidad);
				}
			}
			st.close();

			if (v.equals("0") && v2.equals("0") && v3.equals("0")) {
				Evento.c = 3;
				((Node) event.getSource()).getScene().getWindow().hide();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("finalScreen.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				stage.show();
			} else if (MainUIController.cont == 12) {
				if ((v.equals("0") && v2.equals("0"))
						|| ((v.equals("0") && v3.equals("0")) || ((v2.equals("0") && v3.equals("0"))))) {
					Evento.c = 2;
					((Node) event.getSource()).getScene().getWindow().hide();
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("finalScreen.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.setResizable(false);
					stage.show();
				} else if (v.equals("0") || v2.equals("0") || v3.equals("0")) {
					Evento.c = 1;
					((Node) event.getSource()).getScene().getWindow().hide();
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("finalScreen.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.setResizable(false);
					stage.show();
				} else if (!v.equals("0") && !v2.equals("0") && !v3.equals("0")) {
					Evento.c = 4;
					((Node) event.getSource()).getScene().getWindow().hide();
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("finalScreen.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.setResizable(false);
					stage.show();
				}
			} else {
				((Node) event.getSource()).getScene().getWindow().hide();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				stage.show();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
