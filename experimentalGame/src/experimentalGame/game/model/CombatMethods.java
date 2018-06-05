package experimentalGame.game.model;

public class CombatMethods {

	Squad unidad1 = new Squad();

	Enemy1 enemigo1 = new Enemy1();

	private boolean turno; // si es true comienza el jugador, si no, el enemigo

	private int resultado;
	private Posibilidades resultadoAtaque;
	private int tirada;
	private int vitalidadUnidad;
	private int vitalidadEnemigo;
	private int restaVitalidad;

	/**
	 * Este método devuelve el booleano que indica si el combate lo inicia el
	 * jugador o la computadora
	 * 
	 * @return turno
	 */

	public boolean iniciativa() {

		int numIniciativa = (int) (Math.random() * 10 + 1);

		if (numIniciativa <= 5) {
			turno = false;
		} else {
			turno = true;
		}
		return turno;

	}

	/**
	 * Este metodo calcula el daño del ataque, del jugador o el enemigo
	 * 
	 * @param turno
	 *            booleano que indica quien combatirá y en función de ello calculará
	 *            el ataque de uno u otro
	 * @return
	 */

	public Posibilidades Atacar1(boolean turno) {

		this.turno = turno;

		if (turno = true) {

			int dañoUnidad = unidad1.getFuerza();
			int defensaEnemigo = enemigo1.getFortaleza();
			// int vitalidadEnemigo = enemigo1.getVitalidad();
			resultado = (dañoUnidad - defensaEnemigo) + tirada;

			if (resultado <= 10) {
				resultadoAtaque = Posibilidades.FALLO;
			} else if (resultado <= 20) {
				resultadoAtaque = Posibilidades.ROCE;
			} else if (resultado <= 40) {
				resultadoAtaque = Posibilidades.ACIERTO;
			} else if (resultado <= 50) {
				resultadoAtaque = Posibilidades.CRITICO;
			}

		} else {

			int dañoEnemigo = enemigo1.getFuerza();
			int defensaUnidad = unidad1.getConstitucion();
			// int vitalidadUnidad = unidad1.getVitalidad();
			resultado = (dañoEnemigo - defensaUnidad) + tirada;

			if (resultado <= 10) {
				resultadoAtaque = Posibilidades.FALLO;
			} else if (resultado <= 20) {
				resultadoAtaque = Posibilidades.ROCE;
			} else if (resultado <= 40) {
				resultadoAtaque = Posibilidades.ACIERTO;
			} else if (resultado <= 50) {
				resultadoAtaque = Posibilidades.CRITICO;
			}
		}
		return resultadoAtaque;
	}

	/**
	 * Este método utiliza el resultadoAtaque del método anterior y dependiendo del
	 * caso que fuese le resta a la vitalidad del jugador/enemigo la cantidad
	 * pertienente
	 * 
	 * @return restaVitalidad, la resta que se hará a la vitalidad (se hace así
	 *         porque el método solo puede devolver un valor, pero puede ser resta
	 *         al jugador o al enemigo
	 */
	public int Ataque2() {

		if (turno = true) {

			switch (resultadoAtaque) {

			case FALLO:
				vitalidadEnemigo = enemigo1.getVitalidad() - 0;
				restaVitalidad = vitalidadEnemigo;
				return restaVitalidad;
			case ROCE:
				vitalidadEnemigo = enemigo1.getVitalidad() - 20;
				restaVitalidad = vitalidadEnemigo;
				return restaVitalidad;
			case ACIERTO:
				vitalidadEnemigo = enemigo1.getVitalidad() - 50;
				restaVitalidad = vitalidadEnemigo;
				return restaVitalidad;
			case CRITICO:
				vitalidadEnemigo = enemigo1.getVitalidad() - 100;
				restaVitalidad = vitalidadEnemigo;
				return restaVitalidad;
			default:
				return 0;
			}
		} else if (turno = false) {

			switch (resultadoAtaque) {

			case FALLO:
				vitalidadUnidad = unidad1.getVitalidad() - 0;
				restaVitalidad = vitalidadUnidad;
				return restaVitalidad;
			case ROCE:
				vitalidadUnidad = unidad1.getVitalidad() - 20;
				restaVitalidad = vitalidadUnidad;
				return restaVitalidad;
			case ACIERTO:
				vitalidadUnidad = unidad1.getVitalidad() - 50;
				restaVitalidad = vitalidadUnidad;
				return restaVitalidad;
			case CRITICO:
				vitalidadUnidad = unidad1.getVitalidad() - 100;
				restaVitalidad = vitalidadUnidad;
				return restaVitalidad;
			default:
				return 0;
			}
		}
		return restaVitalidad;
	}

	/**
	 * Simula la tirada de dados
	 * 
	 * @return tirada, el resultado de la tirada
	 * 
	 */
	public int tirada() {

		int numTirada = (int) (Math.random() * 22 + 1);
		tirada = numTirada;
		return tirada;
	}

	/**
	 * Se lleva a cabo la resta de los ataques sobre la vitalidad total del
	 * personaje/enemigo
	 */
	public void restarVida() {

		vitalidadEnemigo = vitalidadEnemigo - restaVitalidad;
		vitalidadUnidad = vitalidadUnidad - restaVitalidad;

	}

	public void curar() {

	}

}







