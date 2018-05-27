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

	/**
	 * Este m�todo devuelve el booleano que indica si el combate lo inicia el
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
	 * Este metodo calcula el da�o del ataque, del jugador o el enemigo
	 * 
	 * @param turno
	 *            booleano que indica quien combatir� y en funci�n de ello calcular�
	 *            el ataque de uno u otro
	 * @return
	 */

	public Posibilidades Atacar1(boolean turno) {

		this.turno = turno;

		if (turno = true) {

			int da�oUnidad = unidad1.getFuerza();
			int defensaEnemigo = enemigo1.getFortaleza();
			// int vitalidadEnemigo = enemigo1.getVitalidad();
			resultado = (da�oUnidad - defensaEnemigo) + tirada;

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

			int da�oEnemigo = enemigo1.getFuerza();
			int defensaUnidad = unidad1.getConstitucion();
			// int vitalidadUnidad = unidad1.getVitalidad();
			resultado = (da�oEnemigo - defensaUnidad) + tirada;

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

	public int Ataque2() {

		if (turno = true) {

			switch (resultadoAtaque) {

			case FALLO:
				vitalidadEnemigo = enemigo1.getVitalidad() - 0;
				return vitalidadEnemigo;
			case ROCE:
				vitalidadEnemigo = enemigo1.getVitalidad() - 20;
				return vitalidadEnemigo;
			case ACIERTO:
				vitalidadEnemigo = enemigo1.getVitalidad() - 50;
				return vitalidadEnemigo;
			case CRITICO:
				vitalidadEnemigo = enemigo1.getVitalidad() - 100;
				return vitalidadEnemigo;
			default:
				return 0;
			}
		} else if (turno = false) {
			
			switch (resultadoAtaque) {
			
			case FALLO:
				vitalidadUnidad = unidad1.getVitalidad() - 0;
				return vitalidadEnemigo;
			case ROCE:
				vitalidadUnidad = unidad1.getVitalidad() - 20;
				return vitalidadEnemigo;
			case ACIERTO:
				vitalidadUnidad = unidad1.getVitalidad() - 50;
				return vitalidadEnemigo;
			case CRITICO:
				vitalidadUnidad = unidad1.getVitalidad() - 100;
				return vitalidadEnemigo;
			default:
				return 0;
			}
		}
		return resultado;
	}

	public int tirada() {

		int numTirada = (int) (Math.random() * 22 + 1);
		tirada = numTirada;
		return tirada;
	}

	public void curar() {

	}

}
