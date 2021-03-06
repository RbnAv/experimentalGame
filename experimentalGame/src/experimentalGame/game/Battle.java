package experimentalGame.game;

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
import javafx.scene.image.Image;
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
	private Label lblEsc;
	@FXML
	private Label lblSectorN;
	@FXML
	private Label lblBotiquin;

	@FXML
	private TextArea consola;

	ActionEvent event;
	int random = 0;
	int num = 0;

	boolean turno = false; // si es true comienza el jugador, si no, el enemigo
	private int resultado1 = 0;
	private int resultado2 = 0;
	private int tirada;
	private int vitalidadUnidad = 0;
	private int vitalidadEnemigo = 0;
	// private int restaVitalidad;
	private int defensaUnidad;
	private int da�oEnemigo;
	private int da�oUnidad;
	private int defensaEnemigo;

	static int vit = 0;
	static int vit2 = 0;

	int v = 0;
	int v2 = 0;
	int contadorBot = 2;

	int numIniciativa = 0;

	int equipo = MainUI.equipo;
	String equi;
	String sector = MainUI.sector;

	int numTirada = 0;
	
	String musicFile;
	Media sound;
	MediaPlayer mediaPlayer;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			lblSectorN.setText("Sector " + sector);
			fin.setDisable(true);
			random = (int) (Math.random() * 3 + 1);
			num = random;
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
			// escuadron();
			// enemigo();
			tirada();
			// }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Este m�todo es invocado cuando el jugador pulsa el bot�n del ataque
	 * principal. Cuando se pulsa el bot�n se ejecutan tres m�todos diferentes: - La
	 * tirada que determinar� el da�o final del ataque - El ataque del jugador
	 * (calculado mediante una serie de operaciones matem�ticas) - El ataque del
	 * enemigo (calculado mediante una serie de operaciones matem�ticas)
	 * 
	 * @param event
	 */
	public void btnAtaque1(ActionEvent event) {

		// est�tico
		// Sounds.explosionPlay();
		// no estatico
		// Sounds sonido = new Sounds();
		// sonido.ataque1Play();

		// M�sica
		musicFile = "Sounds/laser.wav";

		sound = new Media(Main.documentBase + musicFile);
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();

		if (vitalityLabel.getText().toString() != String.valueOf(0)
				|| vitalityLabel2.getText().toString() != String.valueOf(0)) {
			tirada();
			atacar1();
			tirada();
			atacar2();

		} else {
			consola.setText("ERROR");
		}
	}

	/**
	 * Este m�todo es invocado cuando el jugador pulsa el bot�n del ataque
	 * secundario. Cuando se pulsa el bot�n se ejecutan tres m�todos diferentes: -
	 * La tirada que determinar� el da�o final del ataque - El ataque del jugador
	 * (calculado mediante una serie de operaciones matem�ticas) - El ataque del
	 * enemigo (calculado mediante una serie de operaciones matem�ticas)
	 * 
	 * @param event
	 */
	public void btnAtaque2(ActionEvent event) {

		musicFile = "Sounds/machinegun.wav";

		sound = new Media(Main.documentBase + musicFile);
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();

		if (vitalityLabel.getText().toString() != String.valueOf(0)
				|| vitalityLabel2.getText().toString() != String.valueOf(0)) {
			tirada();
			atacar3();
			tirada();
			atacar4();

		} else {
			consola.setText("ERROR");
		}
	}

	/**
	 * Cuando se pulsa el bot�n del botiqu�n dos m�todos son invocados - El m�todo
	 * tirada - Y el m�todo de curaci�n encargado de sumar vitalidad al escuadr�n
	 * 
	 * @param event
	 */
	public void btnBotiquin(ActionEvent event) {

		musicFile = "Sounds/medkit.wav";

		sound = new Media(Main.documentBase + musicFile);
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();

		if (contadorBot == 0) {
			botiquin.setDisable(true);
		} else {
			contadorBot -= 1;
			lblBotiquin.setText(contadorBot + " unidades");
			curacion();
		}
	}

	/**
	 * Cuando se pulsa el bot�n de la granada dos m�todos son invocados - granadamon
	 * - ultragranadamon
	 * 
	 * @param event
	 */
	public void btnGranada(ActionEvent event) {

		musicFile = "Sounds/granade.mp3";

		sound = new Media(Main.documentBase + musicFile);
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		tirada();
		granadamon();
		tirada();
		ultragranadamon();

	}

	/**
	 * Este m�todo produce un n�mero aleatorio entre 0 y 23. Guarda el valor
	 * aleatorio en la variable "tirada"
	 * 
	 * @return tirada
	 */
	private int tirada() {
		numTirada = (int) (Math.random() * 22 + 1);
		tirada = numTirada;
		return tirada;
	}

	/**
	 * M�todo encargado de calcular el ataque principal del jugador. Obtiene los
	 * valores de defensa y vitalidad del enemigo. Obtiene los valores de da�o y
	 * vitalidad del jugador. Se calcula mediante una sencilla f�rmula un resultado
	 * que se guarda en la variable "resultado1" Este m�todo se encarga adem�s de
	 * mostrar en pantalla lo que est� sucediendo durante el ataque en ese turno.
	 * Los botones de habilidades tambi�n se desabilitan en caso de no estar
	 * disponibles.
	 * 
	 * @return resultado1
	 */
	private int atacar1() {
		da�oUnidad = Integer.parseInt(fearLabel.getText());
		defensaEnemigo = Integer.parseInt(strengthLabel2.getText());
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado1 = vitalidadEnemigo - ((da�oUnidad - defensaEnemigo) + tirada);
		if (resultado1 <= 0) {
			resultado1 = 0;
		}
		consola.appendText(
				String.valueOf("�Disparad disparad! �Pam pam pam!\n- El escuadr�n us� el fusil de silicona, ha hecho "
						+ ((da�oUnidad - defensaEnemigo) + tirada) + " puntos de da�o.\n"
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
		v = Integer.parseInt(vitalityLabel.getText());
		v2 = Integer.parseInt(vitalityLabel2.getText());
		vit = v;
		vit2 = v2;

		if (equipo == 1) {
			equi = "Escuadr�n A";
		}
		if (equipo == 2) {
			equi = "Escuadr�n B";
		}
		if (equipo == 3) {
			equi = "Escuadr�n C";
		}

		if (v <= 0) {
			v = 0;
			vit = 0;
		}

		// create a java mysql database connection
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg?useSSL=false";
		Class.forName(myDriver);
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "silvestre96");

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
		stage.getIcons().add(new Image("/img/icon.png"));
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.show();
	}

	/**
	 * M�todo encargado de calcular el ataque principal del enemigo. Obtiene los
	 * valores de defensa y vitalidad del jugador. Obtiene los valores de da�o y
	 * vitalidad del enemigo. Se calcula mediante una sencilla f�rmula un resultado
	 * que se guarda en la variable "resultado2" Este m�todo se encarga adem�s de
	 * mostrar en pantalla lo que est� sucediendo durante el ataque en ese turno.
	 * Los botones de habilidades tambi�n se desabilitan en caso de no estar
	 * disponibles.
	 * 
	 * @return resultado2
	 */
	private int atacar2() {
		da�oEnemigo = Integer.parseInt(fearLabel2.getText());
		defensaUnidad = Integer.parseInt(strengthLabel.getText());
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado2 = vitalidadUnidad - ((da�oEnemigo - defensaUnidad) + tirada);
		if (resultado2 <= 0) {
			resultado2 = 0;
		}
		consola.appendText(String.valueOf("�Grrrrraaaaahhhh!\n- El enemigo lanz� �cido por la boca y ha hecho "
				+ ((da�oEnemigo - defensaUnidad) + tirada) + " puntos de da�o.\n" + "   Ha dejado al escuadr�n con "
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
	 * M�todo encargado de calcular el ataque secundario del jugador. Obtiene los
	 * valores de defensa y vitalidad del enemigo. Obtiene los valores de da�o y
	 * vitalidad del jugador. Se calcula mediante una sencilla f�rmula un resultado
	 * que se guarda en la variable "resultado1" Este m�todo se encarga adem�s de
	 * mostrar en pantalla lo que est� sucediendo durante el ataque en ese turno.
	 * Los botones de habilidades tambi�n se desabilitan en caso de no estar
	 * disponibles.
	 * 
	 * @return resultado1
	 */
	private int atacar3() {
		da�oUnidad = Integer.parseInt(fearLabel.getText());
		defensaEnemigo = Integer.parseInt(strengthLabel2.getText());
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado1 = vitalidadEnemigo - ((da�oUnidad - defensaEnemigo) + tirada + 10);
		if (resultado1 <= 0) {
			resultado1 = 0;
		}
		vitalityLabel2.setText(String.valueOf(resultado1));
		consola.appendText(String.valueOf(
				"�Utilizad el fusil de asalto! �Bam bam bam!\n- El escadr�n dispar� contra el enemigo y ha hecho "
						+ ((da�oUnidad - defensaEnemigo) + tirada + 10) + " puntos de da�o.\n"
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
	 * M�todo encargado de calcular el ataque secundario del enemigo. Obtiene los
	 * valores de defensa y vitalidad del jugador. Obtiene los valores de da�o y
	 * vitalidad del enemigo. Se calcula mediante una sencilla f�rmula un resultado
	 * que se guarda en la variable "resultado2" Este m�todo se encarga adem�s de
	 * mostrar en pantalla lo que est� sucediendo durante el ataque en ese turno.
	 * Los botones de habilidades tambi�n se desabilitan en caso de no estar
	 * disponibles.
	 * 
	 * @return resultado2
	 */
	private int atacar4() {
		da�oEnemigo = Integer.parseInt(fearLabel2.getText());
		defensaUnidad = Integer.parseInt(strengthLabel.getText());
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado2 = vitalidadUnidad - ((da�oEnemigo - defensaUnidad) + tirada + 5);
		if (resultado2 <= 0) {
			resultado2 = 0;
		}
		consola.appendText(String.valueOf("�Grrrrraaaaahhhh!\n- El enemigo lanz� �cido por la boca y ha hecho "
				+ ((da�oEnemigo - defensaUnidad) + tirada + 5) + " puntos de da�o.\n" + "   Ha dejado al escuadr�n con "
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
	 * M�todo encargado de calcular la cantidad de vitalidad que un escuadr�n puede
	 * recuperar.
	 * 
	 * @return
	 */
	private int curacion() {

		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado1 = (vitalidadUnidad + 30);
		vitalityLabel.setText(String.valueOf(resultado1));
		consola.appendText(String.valueOf("�Necesitamos curaci�n!\n- El escadr�n us� un botiqu�n y ha sanado " + 30
				+ " puntos de salud.\n" + "Ahora el escuadr�n tiene " + resultado1 + " puntos de vida."
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
		da�oUnidad = Integer.parseInt(fearLabel.getText());
		defensaEnemigo = Integer.parseInt(strengthLabel2.getText());
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado1 = vitalidadEnemigo - ((da�oUnidad - defensaEnemigo) + tirada + 30);
		if (resultado1 <= 0) {
			resultado1 = 0;
		}
		vitalityLabel2.setText(String.valueOf(resultado1));
		consola.appendText(String.valueOf("�Granada va! �Buuummm!\n- El escuadr�n lanz� una granada y ha hecho "
				+ ((da�oUnidad - defensaEnemigo) + tirada + 30) + " puntos de da�o.\n" + "   Ha dejado al enemigo con "
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
		da�oEnemigo = Integer.parseInt(fearLabel2.getText());
		defensaUnidad = Integer.parseInt(strengthLabel.getText());
		vitalidadEnemigo = Integer.parseInt(vitalityLabel2.getText());
		vitalidadUnidad = Integer.parseInt(vitalityLabel.getText());
		resultado2 = vitalidadUnidad - ((da�oEnemigo - defensaUnidad) + tirada + 25);
		if (resultado2 <= 0) {
			resultado2 = 0;
		}
		vitalityLabel.setText(String.valueOf(resultado2));
		consola.appendText(String.valueOf("- La explosi�n es tan fuerte que hace da�o al escuadr�n.\n" + "   Ha hecho "
				+ ((da�oEnemigo - defensaUnidad) + tirada + 25) + " puntos de da�o.\n"
				+ "   El escuadr�n se ha quedado con " + resultado2 + " puntos de vida."
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
		if (numIniciativa <= 5) {
			turno = false;
		} else {
			turno = true;
		}
		return turno;
	}

	private void enemigo() throws ClassNotFoundException, SQLException {

		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg?useSSL=false";
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
			if (idEnemigo == num) {
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
		String myUrl = "jdbc:mysql://localhost/rpg?useSSL=false";
		Class.forName(myDriver);
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "silvestre96");
		String query = "SELECT * FROM escuadron";
		conn.createStatement();
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			int idEscuadron = rs.getInt("idEscuadron");
			String nombre = rs.getString("nombreEscuadron");
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
				lblEsc.setText(nombre);
			}
		}
		st.close();
	}

}
