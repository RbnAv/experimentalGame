package experimentalGame.game.view;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class FinalScreen implements Initializable {

	@FXML
	private TextArea area1;
	@FXML
	private TextArea area2;

	String nombreEvento = "";
	String narrativa = "";
	String dialogo = "";

	String evento = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			if (Evento.c == 1) {
				evento = "F2";
			}
			else if (Evento.c == 2) {
				evento = "F3";
			}
			else if (Evento.c == 3) {
				evento = "F1";
			}
			else if (Evento.c == 4) {
				evento = "F4";
			}
			evento();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void evento() throws SQLException, ClassNotFoundException {
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/rpg";
		Class.forName(myDriver);
		Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "silvestre96");
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

			if (nombreEvento.equals(evento)) {
				area1.setText(narrativa);
				area2.setText(dialogo);
			}
		}

		st.close();
	}

}
