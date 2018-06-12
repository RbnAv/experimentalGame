package experimentalGame.game;

import java.net.URL;
import java.util.ResourceBundle;

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

public class Narrative implements Initializable {

	@FXML
	private TextArea area1;
	@FXML
	private TextArea area2;
	@FXML
	private Label label;

	Thread t;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		t = new Thread() {
			public void run() {
				super.run();
				texto();
				texto2();
			}
		};
		t.start();

	}

	public void texto() {
		String s = "\n12 de Agosto del a�o 3515\n\nNombre de misi�n: BARAJ\n\nDescripci�n: El crucero espacial LUDUS dedicado a investigaciones\nbiotecnol�gicas nos ha informado hace una semana de una fuga sospechosa.\nTodos los intentos de contactar con la nave han sido in�tiles.\n\nObjetivo: Su misi�n consiste en dirigir a su escuadr�n (LEB) compuesto por 10\npersonas (sin contarla a usted) al interior del crucero, que visualicen la situaci�n\ny retrocedan en caso de peligro, para poder enviar a un equipo m�s especializado.\n\nMisi�n Aceptada.";
		char c;
		String b;
		int tiempo = 40;

		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			b = String.valueOf(c);
			try {
				Thread.sleep(tiempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			area1.appendText(b);
		}
	}

	@SuppressWarnings("deprecation")
	public void texto2() {
		String s = "Oxi: Aqu� Oxi comunic�ndose con la nave �Est�s ah�?\n\nAr: Si da se�al es que le llegan los mensajes.\n\nAxios: Deber�amos informarle de� esto.\n\nOctano: Est� bien, que se encargue el novato. Algo huele a mierda desde que has llegado, novato.\n\nUr�n: Lo siento, creo que he sido yo. Llevo dos semanas tomando probi�ticos.\n\nAstro: �Est�s hablando en serio? Qu� asco, joder, volvamos a la misi�n �Novato! �Informa!\n\nCal: Aqu� el soldado� soldado�\n\nAxios: �Dilo!\n\nCal: �Cal! �El soldado Cal informando! Tenemos frente a nosotros el hangar vac�o, hemos aterrizado la c�psula sin\nproblemas. Todo lo que se ve es la puerta principal cerrada, una puerta que se abre y se cierra y�\n\nAxios: �Qu� ha sido eso?\n\nAr: Creo que han sido gritos.\n\nBorok: Transmitiendo frecuencia de audio captada� (se reproduce un grito de hombre en la lejan�a).\n\nOxi: Efectivamente, es un grito.\n\nAxios: Me parece que ven�a de por aqu�.\n\nAstro: �Veis esto? Parece una grieta, si nos agachamos un poco, podemos pasar.\n\nAr: Deber�amos dividirnos en escuadrones para encargarnos de las distintas partes.\n\nBorok: Bibobibobi.\n\nOctano: Este robot imb�cil cree que entendemos el idioma de los tansformers o algo.\n\nBorok: Perd�n, estaba calculando las mejores variantes para dividirnos. Creo que Axios y Astro podr�an ir juntos por la\nbrecha.\n\nAxios: �Est�s insinuando que porque seamos gemelos y mellizos vamos a todos lados juntos? Eso es discriminatorio.\n\nOxi: Yo tambi�n lo creo, con dos personas mirando en la brecha es m�s que suficiente, tal vez no llegue a ning�n lado.\nMientras, Arson, Ar y Borok pueden ir a la puerta estropeada que lleva al otro hangar y ver si hay otro camino o alguna\nnovedad. Yo buscar� los conductos de aire, a ver si llego a la sala de mando.\n\nD�cimo: �Y nosotros, se�or?\n\nOxi: Coged la taladradora e intentad echar la puerta de entrada abajo. Sala de mando, te ir� diciendo lo que veo por los\nconductos, tal vez necesite que me gu�es, este sitio es como el Taj Mahal de grande.\n\n----- 2 Horas despu�s -----\n\nEscuadr�n A: Aqu� el l�der del primer escuadr�n, hemos solucionado el problema, simplemente era el tono de llamada de un\nm�vil. Seguiremos hacia delante.\n\nEscuadr�n B: L�der del segundo escuadr�n comunicando, estamos dentro, no sabemos que le ha podido pasar a la puerta\npero no nos preocupa, hemos entrado uno a uno y seguimos el camino.\n\nEscuadr�n C: L�der del tercer escuadr�n informa, al parecer el taladro se sobrecalent�n y no lleg� a abrir la puerta,\nafortunadamente a Ur�n se le ocurri� la idea de llevar sus explosivos en la maleta y hemos podido volar la puerta sin\nproblemas. Seguimos en contacto.";
		char c;
		String b;
		int tiempo = 40;

		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			b = String.valueOf(c);
			try {
				Thread.sleep(tiempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			area2.appendText(b);
		}
		t.stop();
	}

	@SuppressWarnings("deprecation")
	public void continuar(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();
			t.stop();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
