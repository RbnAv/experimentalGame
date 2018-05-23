package experimentalGame.game.view;

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
	static String c = "";
	
	Thread t;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		c=MainUIController.s;
		label.setText(c);
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
		String s = "\n12 de Agosto del año 3515\n\nNombre de misión: BARAJ\n\nDescripción: El crucero espacial LUDUS dedicado a investigaciones\nbiotecnológicas nos ha informado hace una semana de una fuga sospechosa.\nTodos los intentos de contactar con la nave han sido inútiles.\n\nObjetivo: Su misión consiste en dirigir a su escuadrón (LEB) compuesto por 10\npersonas (sin contarla a usted) al interior del crucero, que visualicen la situación\ny retrocedan en caso de peligro, para poder enviar a un equipo más especializado.\n\nMisión Aceptada.";
		char c;
		String b;
		int tiempo = 20;

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
		String s = "Oxi: Aquí Oxi comunicándose con la nave ¿Estás ahí?\n\nAr: Si da señal es que le llegan los mensajes.\n\nAxios: Deberíamos informarle de… esto.\n\nOctano: Está bien, que se encargue el novato. Algo huele a mierda desde que has llegado, novato.\n\nUrón: Lo siento, creo que he sido yo. Llevo dos semanas tomando probióticos.\n\nAstro: ¿Estás hablando en serio? Qué asco, joder, volvamos a la misión ¡Novato! ¡Informa!\n\nCal: Aquí el soldado… soldado…\n\nAxios: ¡Dilo!\n\nCal: ¡Cal! ¡El soldado Cal informando! Tenemos frente a nosotros el hangar vacío, hemos aterrizado la cápsula sin\nproblemas. Todo lo que se ve es la puerta principal cerrada, una puerta que se abre y se cierra y…\n\nAxios: ¿Qué ha sido eso?\n\nAr: Creo que han sido gritos.\n\nBorok: Transmitiendo frecuencia de audio captada… (se reproduce un grito de hombre en la lejanía).\n\nOxi: Efectivamente, es un grito.\n\nAxios: Me parece que venía de por aquí.\n\nAstro: ¿Veis esto? Parece una grieta, si nos agachamos un poco, podemos pasar.\n\nAr: Deberíamos dividirnos en escuadrones para encargarnos de las distintas partes.\n\nBorok: Bibobibobi.\n\nOctano: Este robot imbécil cree que entendemos el idioma de los tansformers o algo.\n\nBorok: Perdón, estaba calculando las mejores variantes para dividirnos. Creo que Axios y Astro podrían ir juntos por la\nbrecha.\n\nAxios: ¿Estás insinuando que porque seamos gemelos y mellizos vamos a todos lados juntos? Eso es discriminatorio.\n\nOxi: Yo también lo creo, con dos personas mirando en la brecha es más que suficiente, tal vez no llegue a ningún lado.\nMientras, Arson, Ar y Borok pueden ir a la puerta estropeada que lleva al otro hangar y ver si hay otro camino o alguna\nnovedad. Yo buscaré los conductos de aire, a ver si llego a la sala de mando.\n\nDécimo: ¿Y nosotros, señor?\n\nOxi: Coged la taladradora e intentad echar la puerta de entrada abajo. Sala de mando, te iré diciendo lo que veo por los\nconductos, tal vez necesite que me guíes, este sitio es como el Taj Mahal de grande.\n\n----- 2 Horas después -----\n\nEscuadrón A: Aquí el líder del primer escuadrón, hemos solucionado el problema, simplemente era el tono de llamada de un\nmóvil. Seguiremos hacia delante.\n\nEscuadrón B: Líder del segundo escuadrón comunicando, estamos dentro, no sabemos que le ha podido pasar a la puerta\npero no nos preocupa, hemos entrado uno a uno y seguimos el camino.\n\nEscuadrón C: Líder del tercer escuadrón informa, al parecer el taladro se sobrecalentón y no llegó a abrir la puerta,\nafortunadamente a Urón se le ocurrió la idea de llevar sus explosivos en la maleta y hemos podido volar la puerta sin\nproblemas. Seguimos en contacto.";
		char c;
		String b;
		int tiempo = 35;

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
			stage.show();
			t.stop();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
