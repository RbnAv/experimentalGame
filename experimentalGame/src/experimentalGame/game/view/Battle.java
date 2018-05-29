package experimentalGame.game.view;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import experimentalGame.game.model.Posibilidades;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Battle implements Initializable {

	@FXML
	private Label vitalityLabel;
	@FXML
	private Label energyLabel;
	@FXML
	private Label fearLabel;
	@FXML
	private Label strengthLabel;
	@FXML
	private Label vitalityLabel1;
	@FXML
	private Label energyLabel1;
	@FXML
	private Label fearLabel1;
	@FXML
	private Label strengthLabel1;
	@FXML
	private Label vitalityLabel2;
	@FXML
	private Label energyLabel2;
	@FXML
	private Label fearLabel2;
	@FXML
	private Label strengthLabel2;

	@FXML
	private Label lbl;
	@FXML
	private TextArea consola;
	
	private boolean turno; // si es true comienza el jugador, si no, el enemigo
	private int resultado;
	private Posibilidades resultadoAtaque;
	private int tirada;
	private int vitalidadUnidad;
	private int vitalidadEnemigo;
	private int restaVitalidad;

	int v;
	int v2;

	int equipo = MainUI.equipo;
	String sector = MainUI.sector;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			escuadron();
			enemigo();
			tirada();
			batalla();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private int tirada() {
		int numTirada = (int) (Math.random() * 22 + 1);
		tirada = numTirada;
		return tirada;
	}

	private void batalla() {
		System.out.println(turno);
		iniciativa();
		atacar1();
		if (turno = false) {
			atacar1();
			//System.out.println(resultado);
			//lbl.setText(String.valueOf(resultado));
		}

	}

	// private void atacar1() {
	// this.turno = turno;
	//
	// if (turno = true) {
	//
	// int dañoUnidad = unidad1.getFuerza();
	// int defensaEnemigo = enemigo1.getFortaleza();
	// // int vitalidadEnemigo = enemigo1.getVitalidad();
	// resultado = (dañoUnidad - defensaEnemigo) + tirada;
	//
	// if (resultado <= 10) {
	// resultadoAtaque = Posibilidades.FALLO;
	// } else if (resultado <= 20) {
	// resultadoAtaque = Posibilidades.ROCE;
	// } else if (resultado <= 40) {
	// resultadoAtaque = Posibilidades.ACIERTO;
	// } else if (resultado <= 50) {
	// resultadoAtaque = Posibilidades.CRITICO;
	// }
	//
	// } else {
	//
	// int dañoEnemigo = enemigo1.getFuerza();
	// int defensaUnidad = unidad1.getConstitucion();
	// // int vitalidadUnidad = unidad1.getVitalidad();
	// resultado = (dañoEnemigo - defensaUnidad) + tirada;
	//
	// if (resultado <= 10) {
	// resultadoAtaque = Posibilidades.FALLO;
	// } else if (resultado <= 20) {
	// resultadoAtaque = Posibilidades.ROCE;
	// } else if (resultado <= 40) {
	// resultadoAtaque = Posibilidades.ACIERTO;
	// } else if (resultado <= 50) {
	// resultadoAtaque = Posibilidades.CRITICO;
	// }
	// }
	// return resultadoAtaque;
	// }
	private int atacar1() {
			int dañoUnidad = Integer.parseInt(fearLabel.getText());
			int defensaEnemigo = Integer.parseInt(strengthLabel.getText());
			int vitalidadEnemigo = Integer.parseInt(vitalityLabel.getText());
			resultado = vitalidadEnemigo - ((dañoUnidad - defensaEnemigo) + tirada);
			System.out.println("vitalidadEnemigo: "+vitalidadEnemigo);
			System.out.println("dañoUnidad: "+dañoUnidad);
			System.out.println("defensaEnemigo: "+defensaEnemigo);
			System.out.println("tirada: "+tirada);
			System.out.println("resultado: "+resultado);
			lbl.setText(String.valueOf(resultado));
			consola.setText(String.valueOf(resultado));
		return resultado;
	}

	private boolean iniciativa() {
		int numIniciativa = (int) (Math.random() * 10 + 1);

		if (numIniciativa <= 5) {
			turno = false;
		} else {
			turno = true;
		}
		return turno;
	}

	private void enemigo() throws ClassNotFoundException, SQLException {
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg";
		Class.forName(myDriver);
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "studium2017");
		String query = "SELECT * FROM enemigo";
		conn.createStatement();
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			int idEnemigo = rs.getInt("idEnemigo");
			// String nombre = rs.getString("nombreEnemigo");
			int constitucion = rs.getInt("constitucionEnemigo");
			int vitalidad = rs.getInt("vitalidadEnemigo");
			int fuerza = rs.getInt("fuerzaEnemigo");
			if (idEnemigo == 1) {
				vitalityLabel2.setText(String.valueOf(vitalidad));
				fearLabel2.setText(String.valueOf(fuerza));
				strengthLabel2.setText(String.valueOf(constitucion));
				v2 = Integer.parseInt(vitalityLabel2.getText());
			}
		}
		st.close();
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
			// String nombre = rs.getString("nombreEscuadron");
			int constitucion = rs.getInt("constitucionEscuadron");
			int vitalidad = rs.getInt("vitalidadEscuadron");
			int fuerza = rs.getInt("fuerzaEscuadron");
			int agilidad = rs.getInt("agilidadEscuadron");
			if (idEscuadron == equipo) {
				vitalityLabel.setText(String.valueOf(vitalidad));
				energyLabel.setText(String.valueOf(agilidad));
				fearLabel.setText(String.valueOf(fuerza));
				strengthLabel.setText(String.valueOf(constitucion));
				v = Integer.parseInt(vitalityLabel.getText());
			}
		}
		st.close();
	}

}
