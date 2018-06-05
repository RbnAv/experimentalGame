package experimentalGame.game.view;

import java.io.File;
import java.io.IOException;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

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
	private Button fin;

	@FXML
	private Label lblBotiquin;

	@FXML
	private TextArea consola;

	ActionEvent event;

	boolean turno = false; // si es true comienza el jugador, si no, el enemigo
	private int resultado1 = 0;
	private int resultado2 = 0;
	private int tirada;
	private int vitalidadUnidad = 0;
	private int vitalidadEnemigo = 0;
	// private int restaVitalidad;
	private int defensaUnidad;
	private int dañoEnemigo;
	private int dañoUnidad;
	private int defensaEnemigo;

	static int vit = 0;
	static int vit2 = 0;

	int v = 0;
	int v2 = 0;
	int contadorBot = 3;

	int numIniciativa = 0;

	int equipo = MainUI.equipo;
	String equi;
	String sector = MainUI.sector;

	private String sonido1;
	private String sonido2;
	private String sonido3;
	private String sonido4;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			fin.setDisable(true);
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

	/**
	 * Este método es invocado cuando el jugador pulsa el botón del ataque
	 * principal. Cuando se pulsa el botón se ejecutan tres métodos diferentes: - La
	 * tirada que determinará el daño final del ataque - El ataque del jugador
	 * (calculado mediante una serie de operaciones matemáticas) - El ataque del
	 * enemigo (calculado mediante una serie de operaciones matemáticas)
	 * 
	 * @param event
	 */
	public void btnAtaque1(ActionEvent event) {

		// estático
		// Sounds.explosionPlay();
		// no estatico
		// Sounds sonido = new Sounds();
		// sonido.ataque1Play();

		// Música 
		String musicFile = "./src/Sounds/explosion-1.wav"; 

		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();

		if (vitalityLabel.getText().toString() != String.valueOf(0)
				|| vitalityLabel2.getText().toString() != String.valueOf(0)) {
			tirada();
			atacar1();
			atacar2();

		} else {
			consola.setText("ERROR");
		}
	}

	/**
	 * Este método es invocado cuando el jugador pulsa el botón del ataque
	 * secundario. Cuando se pulsa el botón se ejecutan tres métodos diferentes: -
	 * La tirada que determinará el daño final del ataque - El ataque del jugador
	 * (calculado mediante una serie de operaciones matemáticas) - El ataque del
	 * enemigo (calculado mediante una serie de operaciones matemáticas)
	 * 
	 * @param event
	 */
	public void btnAtaque2(ActionEvent event) {
		if (vitalityLabel.getText().toString() != String.valueOf(0)
				|| vitalityLabel2.getText().toString() != String.valueOf(0)) {
			tirada();
			atacar3();
			atacar4();

		} else {
			consola.setText("ERROR");
		}
	}

	/**
	 * Cuando se pulsa el botón del botiquín dos métodos son invocados - El método
	 * tirada - Y el método de curación encargado de sumar vitalidad al escuadrón
	 * 
	 * @param event
	 */
	public void btnBotiquin(ActionEvent event) {
		if (contadorBot == 0) {
			botiquin.setDisable(true);
		} else {
			contadorBot -= 1;
			lblBotiquin.setText(contadorBot + " unidades");
			tirada();
			curacion();
		}
	}

	/**
	 * Cuando se pulsa el botón de la granada dos métodos son invocados - granadamon
	 * - ultragranadamon
	 * 
	 * @param event
	 */
	public void btnGranada(ActionEvent event) {
		granadamon();
		ultragranadamon();

	}

	/**
	 * Este método produce un número aleatorio entre 0 y 23. Guarda el valor
	 * aleatorio en la variable "tirada"
	 * 
	 * @return tirada
	 */
	private int tirada() {
		int numTirada = (int) (Math.random() * 22 + 1);
		System.out.println(tirada);
		tirada = numTirada;
		return tirada;
	}

	/**
	 * Método encargado de calcular el ataque principal del jugador. Obtiene los
	 * valores de defensa y vitalidad del enemigo. Obtiene los valores de daño y
	 * vitalidad del jugador. Se calcula mediante una sencilla fórmula un resultado
	 * que se guarda en la variable "resultado1" Este método se encarga además de
	 * mostrar en pantalla lo que está sucediendo durante el ataque en ese turno.
	 * Los botones de habilidades también se desabilitan en caso de no estar
	 * disponibles.
	 * 
	 * @return resultado1
	 */
	private int atacar1() {
		dañoUnidad = Integer.parseInt(fearLabel.getText());
		defensaEnemigo = Integer.parseInt(strengthLabel2.getText());
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado1 = vitalidadEnemigo - ((dañoUnidad - defensaEnemigo) + tirada);
		System.out.println("vitalidadEnemigo: " + vitalidadEnemigo);
		System.out.println("dañoUnidad: " + dañoUnidad);
		System.out.println("defensaEnemigo: " + defensaEnemigo);
		System.out.println("tirada: " + tirada);
		System.out.println("resultado: " + resultado1);
		consola.appendText(
				String.valueOf("¡Disparad disparad! ¡Pam pam pam!\n- El escuadrón usó el fusil de silicona, ha hecho "
						+ ((dañoUnidad - defensaEnemigo) + tirada) + " puntos de daño.\n"
						+ "   Ha dejado al enemigo con " + resultado1 + " puntos de vida.\n\n"));
		vitalityLabel2.setText(String.valueOf(resultado1));
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		if (vitalidadUnidad <= 0 || vitalidadEnemigo <= 0) {
			ataque1.setDisable(true);
			ataque2.setDisable(true);
			botiquin.setDisable(true);
			granada.setDisable(true);
			fin.setDisable(false);
		} else {
			fin.setDisable(true);
		}
		return resultado1;
	}

	public void muerte(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		MainUIController.cont++;
		System.out.println("Contador: " + MainUIController.cont);
		v = Integer.parseInt(vitalityLabel.getText());
		v2 = Integer.parseInt(vitalityLabel2.getText());
		vit = v;
		vit2 = v2;

		if (equipo == 1) {
			equi = "Escuadrón A";
		}
		if (equipo == 2) {
			equi = "Escuadrón B";
		}
		if (equipo == 3) {
			equi = "Escuadrón C";
		}

		if (v <= 0) {
			v = 0;
			vit = 0;
		}

		// create a java mysql database connection
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg";
		Class.forName(myDriver);
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "studium2017");

		// create the java mysql update preparedstatement
		String query = "update escuadron set vitalidadEscuadron = ? where nombreEscuadron = ?";
		PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
		// preparedStmt.setInt (1, 6000);
		preparedStmt.setInt(1, v);
		preparedStmt.setString(2, equi);

		// execute the java preparedstatement
		preparedStmt.executeUpdate();

		conn.close();

		((Node) event.getSource()).getScene().getWindow().hide();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("alertPanel.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.show();
	}

	/**
	 * Método encargado de calcular el ataque principal del enemigo. Obtiene los
	 * valores de defensa y vitalidad del jugador. Obtiene los valores de daño y
	 * vitalidad del enemigo. Se calcula mediante una sencilla fórmula un resultado
	 * que se guarda en la variable "resultado2" Este método se encarga además de
	 * mostrar en pantalla lo que está sucediendo durante el ataque en ese turno.
	 * Los botones de habilidades también se desabilitan en caso de no estar
	 * disponibles.
	 * 
	 * @return resultado2
	 */
	private int atacar2() {
		dañoEnemigo = Integer.parseInt(fearLabel2.getText());
		defensaUnidad = Integer.parseInt(strengthLabel.getText());
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado2 = vitalidadUnidad - ((dañoEnemigo - defensaUnidad) + tirada);
		System.out.println("vitalidadUnidad: " + vitalidadUnidad);
		consola.appendText(String.valueOf("¡Grrrrraaaaahhhh!\n- El enemigo lanzó ácido por la boca y ha hecho "
				+ ((dañoEnemigo - defensaUnidad) + tirada) + " puntos de daño.\n" + "   Ha dejado al escuadrón con "
				+ resultado2 + " puntos de vida."
				+ "\n\n--------------------------------------------------------------------------------------\n\n"));
		vitalityLabel.setText(String.valueOf(resultado2));
		// t.stop();
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		if (vitalidadUnidad <= 0 || vitalidadEnemigo <= 0) {
			ataque1.setDisable(true);
			ataque2.setDisable(true);
			botiquin.setDisable(true);
			granada.setDisable(true);
			fin.setDisable(false);
		} else {
			fin.setDisable(true);
		}
		return resultado2;
	}

	/**
	 * Método encargado de calcular el ataque secundario del jugador. Obtiene los
	 * valores de defensa y vitalidad del enemigo. Obtiene los valores de daño y
	 * vitalidad del jugador. Se calcula mediante una sencilla fórmula un resultado
	 * que se guarda en la variable "resultado1" Este método se encarga además de
	 * mostrar en pantalla lo que está sucediendo durante el ataque en ese turno.
	 * Los botones de habilidades también se desabilitan en caso de no estar
	 * disponibles.
	 * 
	 * @return resultado1
	 */
	private int atacar3() {
		dañoUnidad = Integer.parseInt(fearLabel.getText());
		defensaEnemigo = Integer.parseInt(strengthLabel2.getText());
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado1 = vitalidadEnemigo - ((dañoUnidad - defensaEnemigo) + tirada + 10);
		vitalityLabel2.setText(String.valueOf(resultado1));
		consola.appendText(String.valueOf(
				"¡Utilizad el fusil de asalto! ¡Bam bam bam!\n- El escadrón disparó contra el enemigo y ha hecho "
						+ ((dañoUnidad - defensaEnemigo) + tirada + 10) + " puntos de daño.\n"
						+ "   Ha dejado al enemigo con " + resultado1 + " puntos de vida.\n\n"));
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		if (vitalidadUnidad <= 0 || vitalidadEnemigo <= 0) {
			ataque1.setDisable(true);
			ataque2.setDisable(true);
			botiquin.setDisable(true);
			granada.setDisable(true);
			fin.setDisable(false);
		} else {
			fin.setDisable(true);
		}
		return resultado1;
	}

	/**
	 * Método encargado de calcular el ataque secundario del enemigo. Obtiene los
	 * valores de defensa y vitalidad del jugador. Obtiene los valores de daño y
	 * vitalidad del enemigo. Se calcula mediante una sencilla fórmula un resultado
	 * que se guarda en la variable "resultado2" Este método se encarga además de
	 * mostrar en pantalla lo que está sucediendo durante el ataque en ese turno.
	 * Los botones de habilidades también se desabilitan en caso de no estar
	 * disponibles.
	 * 
	 * @return resultado2
	 */
	private int atacar4() {
		dañoEnemigo = Integer.parseInt(fearLabel2.getText());
		defensaUnidad = Integer.parseInt(strengthLabel.getText());
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado2 = vitalidadUnidad - ((dañoEnemigo - defensaUnidad) + tirada + 5);
		System.out.println("vitalidadUnidad: " + vitalidadUnidad);
		consola.appendText(String.valueOf("¡Grrrrraaaaahhhh!\n- El enemigo lanzó ácido por la boca y ha hecho "
				+ ((dañoEnemigo - defensaUnidad) + tirada + 5) + " puntos de daño.\n" + "   Ha dejado al escuadrón con "
				+ resultado2 + " puntos de vida."
				+ "\n\n--------------------------------------------------------------------------------------\n\n"));
		vitalityLabel.setText(String.valueOf(resultado2));
		// t.stop();
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		if (vitalidadUnidad <= 0 || vitalidadEnemigo <= 0) {
			ataque1.setDisable(true);
			ataque2.setDisable(true);
			botiquin.setDisable(true);
			granada.setDisable(true);
			fin.setDisable(false);
		} else {
			fin.setDisable(true);
		}
		return resultado2;
	}

	/**
	 * Método encargado de calcular la cantidad de vitalidad que un escuadrón puede
	 * recuperar.
	 * 
	 * @return
	 */
	private int curacion() {
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado1 = (vitalidadUnidad + 30 + tirada);
		vitalityLabel.setText(String.valueOf(resultado1));
		consola.appendText(String.valueOf("¡Necesitamos curación!\n- El escadrón usó un botiquín y ha sanado " + 30
				+ " puntos de salud.\n" + "Ahora el escuadrón tiene " + resultado1 + " puntos de vida."
				+ "\n\n--------------------------------------------------------------------------------------\n\n"));
		if (contadorBot == 0) {
			botiquin.setDisable(true);
		}
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		if (vitalidadUnidad <= 0 || vitalidadEnemigo <= 0) {
			ataque1.setDisable(true);
			ataque2.setDisable(true);
			botiquin.setDisable(true);
			granada.setDisable(true);
			fin.setDisable(false);
		} else {
			fin.setDisable(true);
		}
		return resultado1;

	}

	private int granadamon() {
		dañoUnidad = Integer.parseInt(fearLabel.getText());
		defensaEnemigo = Integer.parseInt(strengthLabel2.getText());
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado1 = vitalidadEnemigo - ((dañoUnidad - defensaEnemigo) + tirada + 40);
		System.out.println("vitalidadEnemigo: " + vitalidadEnemigo);
		System.out.println("dañoUnidad: " + dañoUnidad);
		System.out.println("defensaEnemigo: " + defensaEnemigo);
		System.out.println("tirada: " + tirada);
		System.out.println("resultado: " + resultado1);
		vitalityLabel2.setText(String.valueOf(resultado1));
		consola.appendText(String.valueOf("¡Granada va! ¡Buuummm!\n- El escuadrón lanzó una granada y ha hecho "
				+ ((dañoUnidad - defensaEnemigo) + tirada + 40) + " puntos de daño.\n" + "   Ha dejado al enemigo con "
				+ resultado1 + " puntos de vida.\n\n"));
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		if (vitalidadUnidad <= 0 || vitalidadEnemigo <= 0) {
			ataque1.setDisable(true);
			ataque2.setDisable(true);
			botiquin.setDisable(true);
			granada.setDisable(true);
			fin.setDisable(false);
		} else {
			fin.setDisable(true);
		}
		return resultado1;
	}

	private int ultragranadamon() {
		dañoEnemigo = Integer.parseInt(fearLabel2.getText());
		defensaUnidad = Integer.parseInt(strengthLabel.getText());
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado2 = vitalidadUnidad - ((dañoEnemigo - defensaUnidad) + tirada + 20);
		System.out.println("vitalidadUnidad: " + vitalidadUnidad);
		vitalityLabel.setText(String.valueOf(resultado2));
		consola.appendText(String.valueOf("- La explosión es tan fuerte que hace daño al escuadrón.\n" + "   Ha hecho "
				+ ((dañoEnemigo - defensaUnidad) + tirada + 20) + " puntos de daño.\n"
				+ "   El escuadrón se ha quedado con " + resultado2 + " puntos de vida."
				+ "\n\n--------------------------------------------------------------------------------------\n\n"));
		// t.stop();
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		if (vitalidadUnidad <= 0 || vitalidadEnemigo <= 0) {
			ataque1.setDisable(true);
			ataque2.setDisable(true);
			botiquin.setDisable(true);
			granada.setDisable(true);
			fin.setDisable(false);
		} else {
			fin.setDisable(true);
		}
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
