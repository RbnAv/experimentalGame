package experimentalGame.game.view;

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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MainUI implements Initializable {

	@FXML
	private TextArea comando;
	@FXML
	private Label vitalityLabel;
	@FXML
	private Label energyLabel;
	@FXML
	private Label fearLabel;
	@FXML
	private Label vitalityLabel1;
	@FXML
	private Label energyLabel1;
	@FXML
	private Label fearLabel1;
	@FXML
	private Label vitalityLabel2;
	@FXML
	private Label energyLabel2;
	@FXML
	private Label fearLabel2;

	String txt = "";
	static int equipo = 0;
	static String sector = "";

	CharSequence e1 = "Escuadrón 1";
	CharSequence e2 = "Escuadrón 2";
	CharSequence e3 = "Escuadrón 3";

	CharSequence s1 = "A1";
	CharSequence s2 = "A2";
	CharSequence s3 = "A3";
	CharSequence s4 = "A4";
	CharSequence s5 = "A5";
	CharSequence s6 = "A6";

	CharSequence s7 = "B1";
	CharSequence s8 = "B2";
	CharSequence s9 = "B3";

	CharSequence s10 = "C1";
	CharSequence s11 = "C2";

	CharSequence s12 = "D1";
	CharSequence s13 = "D2";
	CharSequence s14 = "D3";
	CharSequence s15 = "D4";

	CharSequence s16 = "B-D1";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/rpg";
			Class.forName(myDriver);
			Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "silvestre96");
			String query = "SELECT * FROM escuadron";
			conn.createStatement();
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int idEscuadron = rs.getInt("idEscuadron");
				int vitalidad = rs.getInt("vitalidadEscuadron");
				int fuerza = rs.getInt("fuerzaEscuadron");
				int agilidad = rs.getInt("agilidadEscuadron");
				if (idEscuadron == 1) {
					vitalityLabel.setText(String.valueOf(vitalidad));
					energyLabel.setText(String.valueOf(agilidad));
					fearLabel.setText(String.valueOf(fuerza));
				}
				if (idEscuadron == 2) {
					vitalityLabel1.setText(String.valueOf(vitalidad));
					energyLabel1.setText(String.valueOf(agilidad));
					fearLabel1.setText(String.valueOf(fuerza));
				}
				if (idEscuadron == 3) {
					vitalityLabel2.setText(String.valueOf(vitalidad));
					energyLabel2.setText(String.valueOf(agilidad));
					fearLabel2.setText(String.valueOf(fuerza));
				}
			}
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void evento(ActionEvent event) {
		try {
			txt = comando.getText();
			// Equipo
			if (txt.contains(e1)) {
				equipo = 1;
			}
			if (txt.contains(e2)) {
				equipo = 2;
			}
			if (txt.contains(e3)) {
				equipo = 3;
			}
			// Sector
			if (txt.contains(s1)) {
				sector = s1.toString();
			}
			if (txt.contains(s2)) {
				sector = s2.toString();
			}
			if (txt.contains(s3)) {
				sector = s3.toString();
			}
			if (txt.contains(s4)) {
				sector = s4.toString();
			}
			if (txt.contains(s5)) {
				sector = s5.toString();
			}
			if (txt.contains(s6)) {
				sector = s6.toString();
			}
			if (txt.contains(s7)) {
				sector = s7.toString();
			}
			if (txt.contains(s8)) {
				sector = s8.toString();
			}
			if (txt.contains(s9)) {
				sector = s9.toString();
			}
			if (txt.contains(s10)) {
				sector = s10.toString();
			}
			if (txt.contains(s11)) {
				sector = s11.toString();
			}
			if (txt.contains(s12)) {
				sector = s12.toString();
			}
			if (txt.contains(s13)) {
				sector = s13.toString();
			}
			if (txt.contains(s14)) {
				sector = s14.toString();
			}
			if (txt.contains(s15)) {
				sector = s15.toString();
			}
			if (txt.contains(s16)) {
				sector = s16.toString();
			}

			if (sector.equals("C1") || (sector.equals("A3")
					|| (sector.equals("B1") || (sector.equals("D1") || (sector.equals("A1")))))) {
				((Node) event.getSource()).getScene().getWindow().hide();

				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Battle.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
			} else {

				((Node) event.getSource()).getScene().getWindow().hide();

				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eventScreen.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
