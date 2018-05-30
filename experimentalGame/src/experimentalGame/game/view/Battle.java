package experimentalGame.game.view;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import experimentalGame.game.model.Posibilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	private Button ataque1;
	@FXML
	private Button ataque2;
	@FXML
	private Button botiquin;
	@FXML
	private Button granada;

	@FXML
	private TextArea consola;
	
	Thread t;

	boolean turno = false; // si es true comienza el jugador, si no, el enemigo
	private int resultado1 = 0;
	private int resultado2 = 0;
	private Posibilidades resultadoAtaque;
	private int tirada;
	private int vitalidadUnidad;
	private int vitalidadEnemigo;
	private int restaVitalidad;

	int v;
	int v2;

	int numIniciativa = 0;

	int equipo = MainUI.equipo;
	String sector = MainUI.sector;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			escuadron();
			enemigo();
			// while (vitalityLabel.getText().toString() != String.valueOf(0)
			// && vitalityLabel2.getText().toString() != String.valueOf(0)) {
			iniciativa();
			// if (turno == true) {
			//
			// }
			// if (turno == false) {
			// if (vitalityLabel.getText().toString() != String.valueOf(0)) {
			// atacar1();
			// }
			// }
			// iniciativa();
			System.out.println(turno);
			// escuadron();
			// enemigo();
			tirada();
			// }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void btnAtaque1(ActionEvent event) {
		if (vitalityLabel.getText().toString() != String.valueOf(0)
				|| vitalityLabel2.getText().toString() != String.valueOf(0)) {
			// if ((Integer.parseInt(vitalityLabel.getText()) <= 0) ||
			// (Integer.parseInt(vitalityLabel2.getText()) <= 0)) {
			// iniciativa();
			tirada();
			atacar1();
			t = new Thread() {
				public void run() {
					super.run();
					try {
						Thread.sleep (2000);
						atacar2();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
			
		} else {
			consola.setText("ERROR");
		}
	}

	public void btnAtaque2(ActionEvent event) {
		if (vitalityLabel.getText().toString() != String.valueOf(0)
				|| vitalityLabel2.getText().toString() != String.valueOf(0)) {
			tirada();
			atacar3();
			t = new Thread() {
				public void run() {
					super.run();
					try {
						Thread.sleep (2000);
						atacar4();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
			
		} else {
			consola.setText("ERROR");
		}
	}

	public void btnBotiquin(ActionEvent event) {
		tirada();
		curacion();
	}

	public void btnGranada(ActionEvent event) {
		granadamon();
		t = new Thread() {
			public void run() {
				super.run();
				try {
					Thread.sleep (2000);
					ultragranadamon();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
		
	}

	private int tirada() {
		int numTirada = (int) (Math.random() * 22 + 1);
		System.out.println(tirada);
		tirada = numTirada;
		return tirada;
	}

	// private void atacar1() {
	// this.turno = turno;
	//
	// if (turno = true) {
	//
	// int da�oUnidad = unidad1.getFuerza();
	// int defensaEnemigo = enemigo1.getFortaleza();
	// // int vitalidadEnemigo = enemigo1.getVitalidad();
	// resultado = (da�oUnidad - defensaEnemigo) + tirada;
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
	// int da�oEnemigo = enemigo1.getFuerza();
	// int defensaUnidad = unidad1.getConstitucion();
	// // int vitalidadUnidad = unidad1.getVitalidad();
	// resultado = (da�oEnemigo - defensaUnidad) + tirada;
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
		int da�oUnidad = Integer.parseInt(fearLabel.getText());
		int defensaEnemigo = Integer.parseInt(strengthLabel2.getText());
		int vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		resultado1 = vitalidadEnemigo - ((da�oUnidad - defensaEnemigo) + tirada);
		System.out.println("vitalidadEnemigo: " + vitalidadEnemigo);
		System.out.println("da�oUnidad: " + da�oUnidad);
		System.out.println("defensaEnemigo: " + defensaEnemigo);
		System.out.println("tirada: " + tirada);
		System.out.println("resultado: " + resultado1);
		consola.appendText(String.valueOf("�Disparad disparad! �Pam pam pam!\n- El escuadr�n us� el fusil de silicona, ha hecho "+((da�oUnidad - defensaEnemigo) + tirada)+" puntos de da�o.\n"
				+ "   Ha dejado al enemigo con "+resultado1+" puntos de vida.\n\n"));
		vitalityLabel2.setText(String.valueOf(resultado1));
		return resultado1;
	}
	private int atacar2() {
		int da�oEnemigo = Integer.parseInt(fearLabel2.getText());
		int defensaUnidad = Integer.parseInt(strengthLabel.getText());
		int vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado2 = vitalidadUnidad - ((da�oEnemigo - defensaUnidad) + tirada);
		System.out.println("vitalidadUnidad: " + vitalidadUnidad);
		consola.appendText(String.valueOf("�Grrrrraaaaahhhh!\n- El enemigo lanz� �cido por la boca y ha hecho "+((da�oEnemigo - defensaUnidad) + tirada)+" puntos de da�o.\n"
				+ "   Ha dejado al escuadr�n con "+resultado2+" puntos de vida."
				+ "\n\n--------------------------------------------------------------------------------------\n\n"));
		vitalityLabel.setText(String.valueOf(resultado2));
		t.stop();
		return resultado2;
	}

	private int atacar3() {
		int da�oUnidad = Integer.parseInt(fearLabel.getText());
		int defensaEnemigo = Integer.parseInt(strengthLabel2.getText());
		int vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		resultado1 = vitalidadEnemigo - ((da�oUnidad - defensaEnemigo) + tirada + 10);
		vitalityLabel2.setText(String.valueOf(resultado1));
		consola.appendText(String.valueOf("�Utilizad el fusil de asalto! �Bam bam bam!\n- El escadr�n dispar� contra el enemigo y ha hecho "+((da�oUnidad - defensaEnemigo) + tirada + 10)+" puntos de da�o.\n"
				+ "   Ha dejado al enemigo con "+resultado1+" puntos de vida.\n\n"));
		return resultado1;
	}
	
	private int atacar4() {
		int da�oEnemigo = Integer.parseInt(fearLabel2.getText());
		int defensaUnidad = Integer.parseInt(strengthLabel.getText());
		int vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado2 = vitalidadUnidad - ((da�oEnemigo - defensaUnidad) + tirada);
		System.out.println("vitalidadUnidad: " + vitalidadUnidad);
		consola.appendText(String.valueOf("�Grrrrraaaaahhhh!\n- El enemigo lanz� �cido por la boca y ha hecho "+((da�oEnemigo - defensaUnidad) + tirada)+" puntos de da�o.\n"
				+ "   Ha dejado al escuadr�n con "+resultado2+" puntos de vida."
				+ "\n\n--------------------------------------------------------------------------------------\n\n"));
		vitalityLabel.setText(String.valueOf(resultado2));
		t.stop();
		return resultado2;
	}

	private int curacion() {
		int vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado1 = (vitalidadUnidad + 30 + tirada);
		vitalityLabel.setText(String.valueOf(resultado1));
		consola.appendText(String.valueOf("�Necesitamos curaci�n!\n- El escadr�n us� un botiqu�n y ha sanado "+30+" puntos de salud.\n"
				+ "Ahora el escuadr�n tiene "+resultado1+" puntos de vida."
				+ "\n\n--------------------------------------------------------------------------------------\n\n"));
		return resultado1;
	}
	
	private int granadamon() {
		int da�oUnidad = Integer.parseInt(fearLabel.getText());
		int defensaEnemigo = Integer.parseInt(strengthLabel2.getText());
		int vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		resultado1 = vitalidadEnemigo - ((da�oUnidad - defensaEnemigo) + tirada + 40);
		System.out.println("vitalidadEnemigo: " + vitalidadEnemigo);
		System.out.println("da�oUnidad: " + da�oUnidad);
		System.out.println("defensaEnemigo: " + defensaEnemigo);
		System.out.println("tirada: " + tirada);
		System.out.println("resultado: " + resultado1);
		vitalityLabel2.setText(String.valueOf(resultado1));
		consola.appendText(String.valueOf("�Granada va! �Buuummm!\n- El escuadr�n lanz� una granada y ha hecho "+((da�oUnidad - defensaEnemigo) + tirada+ 40)+" puntos de da�o.\n"
				+ "   Ha dejado al enemigo con "+resultado1+" puntos de vida.\n\n"));
		return resultado1;
	}
	
	private int ultragranadamon() {
		int da�oEnemigo = Integer.parseInt(fearLabel2.getText());
		int defensaUnidad = Integer.parseInt(strengthLabel.getText());
		int vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado2 = vitalidadUnidad - ((da�oEnemigo - defensaUnidad) + tirada + 20);
		System.out.println("vitalidadUnidad: " + vitalidadUnidad);
		vitalityLabel.setText(String.valueOf(resultado2));
		consola.appendText(String.valueOf("- La explosi�n es tan fuerte que hace da�o al escuadr�n.\n"
				+ "   Ha hecho "+((da�oEnemigo - defensaUnidad) + tirada+ 20)+" puntos de da�o.\n"
				+ "   El escuadr�n se ha quedado con "+resultado2+" puntos de vida."
				+ "\n\n--------------------------------------------------------------------------------------\n\n"));
		t.stop();
		return resultado2;
	}

	private boolean iniciativa() {
		numIniciativa = (int) (Math.random() * 10 + 1);
		System.out.println(numIniciativa);
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
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "silvestre96");
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
			int agilidad = rs.getInt("agilidadEnemigo");
			if (idEnemigo == 1) {
				vitalityLabel2.setText(String.valueOf(vitalidad));
				energyLabel2.setText(String.valueOf(agilidad));
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
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "silvestre96");
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
