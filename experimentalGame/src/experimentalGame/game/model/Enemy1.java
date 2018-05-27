package experimentalGame.game.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Enemy1 {

	private final StringProperty name;
	private final IntegerProperty fortaleza;
	private final IntegerProperty fuerza;
	private final IntegerProperty agilidad;
	private final IntegerProperty percepcion;
	private final IntegerProperty voluntad;
	private final IntegerProperty vitalidad;

	public Enemy1() {
		this(null);
	}

	public Enemy1(String name) {
		this.name = new SimpleStringProperty(name);

		/**
		 * dummy data
		 * 
		 */
		this.fortaleza = new SimpleIntegerProperty(1);
		this.fuerza = new SimpleIntegerProperty(1);
		this.agilidad = new SimpleIntegerProperty(1);
		this.percepcion = new SimpleIntegerProperty(1);
		this.voluntad = new SimpleIntegerProperty(1);
		this.vitalidad = new SimpleIntegerProperty(350);
	}

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

	public final IntegerProperty fuerzaProperty() {
		return this.fuerza;
	}

	public final int getFuerza() {
		return this.fuerzaProperty().get();
	}

	public final void setFuerza(final int fuerza) {
		this.fuerzaProperty().set(fuerza);
	}

	public final IntegerProperty agilidadProperty() {
		return this.agilidad;
	}

	public final int getAgilidad() {
		return this.agilidadProperty().get();
	}

	public final void setAgilidad(final int agilidad) {
		this.agilidadProperty().set(agilidad);
	}

	public final IntegerProperty percepcionProperty() {
		return this.percepcion;
	}

	public final int getPercepcion() {
		return this.percepcionProperty().get();
	}

	public final void setPercepcion(final int percepcion) {
		this.percepcionProperty().set(percepcion);
	}

	public final IntegerProperty voluntadProperty() {
		return this.voluntad;
	}

	public final int getVoluntad() {
		return this.voluntadProperty().get();
	}

	public final void setVoluntad(final int voluntad) {
		this.voluntadProperty().set(voluntad);
	}

	public final IntegerProperty fortalezaProperty() {
		return this.fortaleza;
	}

	public final int getFortaleza() {
		return this.fortalezaProperty().get();
	}

	public final void setFortaleza(final int fortaleza) {
		this.fortalezaProperty().set(fortaleza);
	}

	public final IntegerProperty vitalidadProperty() {
		return this.vitalidad;
	}

	public final int getVitalidad() {
		return this.vitalidadProperty().get();
	}

	public final void setVitalidad(final int vitalidad) {
		this.vitalidadProperty().set(vitalidad);
	}

}
