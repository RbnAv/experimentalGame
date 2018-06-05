package experimentalGame.game.view;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Evento implements Initializable {

	@FXML
	private TextArea area1;
	@FXML
	private TextArea area2;
	@FXML
	private Label lbl1;
	@FXML
	private Label lbl2;
	@FXML
	private Button btn1 = new Button("");
	@FXML
	private Button btn2 = new Button("");
	@FXML
	private Button btn3 = new Button("");
	@FXML
	private Button btnContinuar = new Button("");

	@FXML
	private ImageView imgEvent;

	String t1;
	String t2;
	String t3;

	int equipo = MainUI.equipo;
	String sector = MainUI.sector;
	String nombreEvento = "";
	String narrativa = "";
	String dialogo = "";
	String accion1 = "";
	String texto1 = "";
	String accion2 = "";
	String texto2 = "";
	String accion3 = "";
	String texto3 = "";

	int idEscuadron;
	int vitalidad;
	String v = "";
	String v2 = "";
	String v3 = "";

	static int c = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			// Image image = new Image("/img/fusil.png");
			Image image = new Image(MainUI.img);
			imgEvent.setImage(image);

			btnContinuar.setDisable(true);

			escuadron();
			evento();

			btn1.setDisable(btn1.getText().toString().equals(""));
			/*
			 * if (btn1.getText().isEmpty()) { btn1.setDisable(true);
			 * btn1.setText(String.valueOf(btn3.getText().length())); }
			 */
			if (btn2.getText().toString().equals("")) {
				btn2.setDisable(true);
			}
			if (btn3.getText().toString().equals("")) {
				btn3.setDisable(true);
			}
		} catch (Exception e) {

		}
	}

	private void escuadron() throws ClassNotFoundException, SQLException {
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg";
		Class.forName(myDriver);
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "studium2017");
		String query = "SELECT * FROM escuadron";
		conn.createStatement();
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			idEscuadron = rs.getInt("idEscuadron");
			String nombre = rs.getString("nombreEscuadron");
			// vitalidad = rs.getInt("vitalidadEscuadron");
			// int constitucion = rs.getInt("constitucionEscuadron");
			// int fuerza = rs.getInt("fuerzaEscuadron");
			if (idEscuadron == equipo) {
				lbl1.setText(nombre);
			}
		}
		st.close();
	}

	private void evento() throws ClassNotFoundException, SQLException {
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg";
		Class.forName(myDriver);
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "studium2017");
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
			if (rs.getString("accion1") != null) {
				accion1 = rs.getString("accion1");
			}
			if (rs.getString("accion1") == null) {
				accion1 = "";
			}
			if (rs.getString("texto1") != null) {
				texto1 = rs.getString("texto1");
			}
			if (rs.getString("accion2") != null) {
				accion2 = rs.getString("accion2");
			}
			if (rs.getString("accion2") == null) {
				accion2 = "";
			}
			if (rs.getString("texto2") != null) {
				texto2 = rs.getString("texto2");
			}
			if (rs.getString("accion3") != null) {
				accion3 = rs.getString("accion3");
			}
			if (rs.getString("accion3") == null) {
				accion3 = "";
			}
			if (rs.getString("texto3") != null) {
				texto3 = rs.getString("texto3");
			}

			if (nombreEvento.equals(sector)) {
				System.out.println(accion1);
				System.out.println(accion2);
				System.out.println(accion3);
				area1.setText(narrativa);
				area2.setText(dialogo);
				btn1.setText(accion1);
				btn2.setText(accion2);
				btn3.setText(accion3);
				t1 = texto1;
				t2 = texto2;
				t3 = texto3;
			}
		}
		st.close();
	}

	public void btn1(ActionEvent event) throws ClassNotFoundException, SQLException {
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg";
		Class.forName(myDriver);
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "studium2017");
		String query = "SELECT * FROM evento";
		conn.createStatement();
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			String nombreEvento = rs.getString("nombreEvento");
			// String narrativa = rs.getString("narrativa");
			// String dialogo = rs.getString("dialogo");
			String accion1 = rs.getString("accion1");
			String texto1 = rs.getString("texto1");
			String accion2 = rs.getString("accion2");
			String texto2 = rs.getString("texto2");
			String accion3 = rs.getString("accion3");
			String texto3 = rs.getString("texto3");

			if (nombreEvento.equals(sector)) {
				if (btn1.getText().equals(accion1)) {
					lbl2.setText(texto1);
				}
				if (btn1.getText().equals(accion2)) {
					lbl2.setText(texto2);
				}
				if (btn1.getText().equals(accion3)) {
					lbl2.setText(texto3);
				}

				if (nombreEvento.equals("A5")) {
					muerte();
				}
				if (nombreEvento.equals("B2")) {
					muerte();
				}
				if (nombreEvento.equals("C2")) {
					muerte();
				}
				if (nombreEvento.equals("D3")) {
					muerte();
				}
			}

		}

		btn1.setDisable(true);
		btn2.setDisable(true);
		btn3.setDisable(true);
		btnContinuar.setDisable(false);
		st.close();
	}

	private void muerte() throws ClassNotFoundException, SQLException {

		// create a java mysql database connection
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg";
		Class.forName(myDriver);
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "studium2017");

		// create the java mysql update preparedstatement
		String query = "update escuadron set vitalidadEscuadron = ? where nombreEscuadron = ?";
		PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
		// preparedStmt.setInt (1, 6000);
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, lbl1.getText());

		// execute the java preparedstatement
		preparedStmt.executeUpdate();

		conn.close();
	}

	public void btn2(ActionEvent event) throws ClassNotFoundException, SQLException {
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg";
		Class.forName(myDriver);
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "studium2017");
		String query = "SELECT * FROM evento";
		conn.createStatement();
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			String nombreEvento = rs.getString("nombreEvento");
			// String narrativa = rs.getString("narrativa");
			// String dialogo = rs.getString("dialogo");
			String accion1 = rs.getString("accion1");
			String texto1 = rs.getString("texto1");
			String accion2 = rs.getString("accion2");
			String texto2 = rs.getString("texto2");
			String accion3 = rs.getString("accion3");
			String texto3 = rs.getString("texto3");

			if (nombreEvento.equals(sector)) {
				if (btn2.getText().equals(accion1)) {
					lbl2.setText(texto1);
				}
				if (btn2.getText().equals(accion2)) {
					lbl2.setText(texto2);
				}
				if (btn2.getText().equals(accion3)) {
					lbl2.setText(texto3);
				}

				if (nombreEvento.equals("A2")) {
					muerte();
				}
				if (nombreEvento.equals("B2")) {
					muerte();
				}
				if (nombreEvento.equals("D2")) {
					muerte();
				}
			}

		}

		btn1.setDisable(true);
		btn2.setDisable(true);
		btn3.setDisable(true);
		btnContinuar.setDisable(false);
		st.close();
	}

	public void btn3(ActionEvent event) throws ClassNotFoundException, SQLException {
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg";
		Class.forName(myDriver);
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "studium2017");
		String query = "SELECT * FROM evento";
		conn.createStatement();
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			String nombreEvento = rs.getString("nombreEvento");
			// String narrativa = rs.getString("narrativa");
			// String dialogo = rs.getString("dialogo");
			String accion1 = rs.getString("accion1");
			String texto1 = rs.getString("texto1");
			String accion2 = rs.getString("accion2");
			String texto2 = rs.getString("texto2");
			String accion3 = rs.getString("accion3");
			String texto3 = rs.getString("texto3");

			if (nombreEvento.equals(sector)) {
				if (btn3.getText().equals(accion1)) {
					lbl2.setText(texto1);
				}
				if (btn3.getText().equals(accion2)) {
					lbl2.setText(texto2);
				}
				if (btn3.getText().equals(accion3)) {
					lbl2.setText(texto3);
				}

				if (nombreEvento.equals("C2")) {
					muerte();
				}
			}
		}

		btn1.setDisable(true);
		btn2.setDisable(true);
		btn3.setDisable(true);
		btnContinuar.setDisable(false);
		st.close();
	}

	public void continuar(ActionEvent event) {
		try {
			MainUIController.cont++;
			System.out.println(MainUIController.cont);
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/rpg";
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
					System.out.println(v);
				}
				if (nombre.equals("Escuadrón B")) {
					v2 = String.valueOf(vitalidad);
					System.out.println(v2);
				}
				if (nombre.equals("Escuadrón C")) {
					v3 = String.valueOf(vitalidad);
					System.out.println(v3);
				}
			}
			st.close();

			if (v.equals("0") && v2.equals("0") && v3.equals("0")) {
				System.out.println("raveeeeeeeeeeeee");
				c = 3;
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
					c = 2;
					((Node) event.getSource()).getScene().getWindow().hide();
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("finalScreen.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.setResizable(false);
					stage.show();
				} else if (v.equals("0") || v2.equals("0") || v3.equals("0")) {
					c = 1;
					((Node) event.getSource()).getScene().getWindow().hide();
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("finalScreen.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.setResizable(false);
					stage.show();
				} else if (!v.equals("0") && !v2.equals("0") && !v3.equals("0")) {
					c = 4;
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



