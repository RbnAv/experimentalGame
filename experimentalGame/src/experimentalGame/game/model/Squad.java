package experimentalGame.game.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** Model class for the squads
 * 
 * @author Ruvek
 *
 */



public class Squad {
	
	private final StringProperty name;
	private final IntegerProperty constitucion;
	private final IntegerProperty fuerza;
	private final IntegerProperty agilidad;
	private final IntegerProperty percepcion;
	private final IntegerProperty voluntad;
	private  IntegerProperty vitalidad;
	private  IntegerProperty energia;
	private  IntegerProperty miedo;

	/** default constructor
	 * 
	 */
	
	public Squad() {
		this(null);
	}
	
	/** constructor with initial data
	 * 
	 * @param name	this name will be given by the user  through interface
	 */
	
	public Squad(String name) {
		this.name = new SimpleStringProperty(name);
	
	/** dummy data
	 * 
	 */
	this.constitucion = new SimpleIntegerProperty(1);
	this.fuerza = new SimpleIntegerProperty(1);
	this.agilidad = new SimpleIntegerProperty(1);
	this.percepcion = new SimpleIntegerProperty(1);
	this.voluntad = new SimpleIntegerProperty(1);
	

}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);;
	}
	
	public StringProperty nameProperty() {
		return name;
	}


	public int getConstitucion() {
		return constitucion.get();
	}

	public void setConstitucion(int constitucion) {
		this.constitucion.set(constitucion);
	}
	
	public IntegerProperty constituciontProperty() {
		return constitucion;
	}

	public int getFuerza() {
		return fuerza.get();
	}

	public void setFuerza(int fuerza) {
		this.fuerza.set(fuerza);
	}
	
	public IntegerProperty fuerzaProperty() {
		return fuerza;
	}

	public int getAgilidad() {
		return agilidad.get();
	}

	public void setAgilidad(int agilidad) {
		this.agilidad.set(agilidad);;
	}
	
	public IntegerProperty agilidadProperty() {
		return agilidad;
	}

	public int getPercepcion() {
		return percepcion.get();
	}

	public void setPercepcion(int percepcion) {
		this.percepcion.set(percepcion);
	}
	
	public IntegerProperty percepcionProperty() {
		return percepcion;
	}

	public int getVoluntad() {
		return voluntad.get();
	}

	public void setVoluntad(int voluntad) {
		this.voluntad.set(voluntad);
	}
	
	public IntegerProperty voluntadProperty() {
		return voluntad;
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
	

	public final IntegerProperty energiaProperty() {
		return this.energia;
	}
	

	public final int getEnergia() {
		return this.energiaProperty().get();
	}
	

	public final void setEnergia(final int energia) {
		this.energiaProperty().set(energia);
	}
	

	public final IntegerProperty miedoProperty() {
		return this.miedo;
	}
	

	public final int getMiedo() {
		return this.miedoProperty().get();
	}
	

	public final void setMiedo(final int miedo) {
		this.miedoProperty().set(miedo);
	}
	

}
