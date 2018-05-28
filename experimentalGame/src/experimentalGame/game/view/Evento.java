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
	private Button btn1;
	@FXML
	private Button btn2;
	@FXML
	private Button btn3;

	String t1;
	String t2;
	String t3;

	int equipo = MainUI.equipo;
	String sector = MainUI.sector;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			System.out.println(MainUI.equipo);
			System.out.println(sector);

			escuadron();
			evento();

		} catch (Exception e) {
			// TODO: handle exception
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
			int idEscuadron = rs.getInt("idEscuadron");
			String nombre = rs.getString("nombreEscuadron");
			// int vitalidad = rs.getInt("vitalidadEscuadron");
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
			String nombreEvento = rs.getString("nombreEvento");
			String narrativa = rs.getString("narrativa");
			String dialogo = rs.getString("dialogo");
			String accion1 = rs.getString("accion1");
			String texto1 = rs.getString("texto1");
			String accion2 = rs.getString("accion2");
			String texto2 = rs.getString("texto2");
			String accion3 = rs.getString("accion3");
			String texto3 = rs.getString("texto3");

			if (nombreEvento.equals(sector)) {
				area1.setText(narrativa);
				area2.setText(dialogo);
				btn1.setText(accion1);
				btn2.setText(accion2);
				btn3.setText(accion3);
				t1 = texto1;
				t2 = texto2;
				t3 = texto3;
			}
			// if(btn1.getText().equals(null)) {
			// btn1.setDisable(true);
			// }
			// if(btn2.getText().isEmpty()) {
			// btn2.setDisable(true);
			// }
			// if(btn3.getText().isEmpty()) {
			// btn3.setDisable(true);
			// }
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
			}
		}
		st.close();
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
			}
		}
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
			}
		}
		st.close();
	}

	public void continuar(ActionEvent event) {
		try {
			
			((Node) event.getSource()).getScene().getWindow().hide();
//			
//			// create a java mysql database connection
//		      String myDriver = "org.gjt.mm.mysql.Driver";
//		      String myUrl = "jdbc:mysql://localhost/rpg";
//		      Class.forName(myDriver);
//		      Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "studium2017");
//		    
//		      // create the java mysql update preparedstatement
//		      String query = "update escuadron set vitalidadEscuadron = ? where nombreEscuadron = ?";//222, 
//		      PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
//		      preparedStmt.setInt   (1, 12);
//		      preparedStmt.setString(2, "Reconocimiento");
//
//		      // execute the java preparedstatement
//		      preparedStmt.executeUpdate();
//		      
//		      conn.close();
//			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
