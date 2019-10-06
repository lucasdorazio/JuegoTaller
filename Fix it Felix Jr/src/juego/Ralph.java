package juego;

public class Ralph /* extends Desplazable */ {

	private static final int LIMITE_DERECHO_EDIFICIO = 500;
	private static final int LIMITE_IZQUIERDA_EDIFICIO = 100;
	private static final int LADRILLOS_POR_TIRADA = 3;
	private static final double TIEMPO_ENTRE_LADRILLOS=0.5;
	private int ladrillosTotales;
	private int ladrillosRestantes;
	private int posX;
	private int velocidad;	//velocidad= x pixeles/seg
	private int timer = 0;
	private int pasosRestantes;
	private Direcciones dirActual;
	private static Ralph INSTANCE;

	public int getLadrillos() {
		return ladrillosTotales;
	}

	public double getPosX() {
		return posX;
	}

	public void setLadrillos(int ladrillos) {
		this.ladrillosTotales = ladrillos;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	private Ralph() {
		this.ladrillosTotales = 40;
		this.posX = 500;// cambiar valor
	}

	public static Ralph getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Ralph();
		}
		return INSTANCE;
	}

	public boolean golpearEdif() {							//Retorna true cuando no tiene que tirar mas ladrillos
		timer++;
		if (timer>TIEMPO_ENTRE_LADRILLOS*10000) {												// timer se incrementa 10000 veces por segundo
			ControladorDeLadrillos.generarLadrillo(new Posicion(posX+(ladrillosRestantes*15)-30,100));			//Genero ladrillos a 15, 0 y -15 pixeles de Ralph
			ladrillosRestantes--;																//Revisar coordY de la posicion
			timer=0;
		}
		return (ladrillosRestantes==0);
	}
	
	public void comenzarGolpeo() {
		ladrillosRestantes=LADRILLOS_POR_TIRADA;
		golpearEdif();
	}

	/*
	 * @Override public Direcciones obtenerDireccion() { return null; }
	 */

	public void comenzarMovimiento(int cantPasos, Direcciones direccion) {
		pasosRestantes = cantPasos * 15; // Un paso es igual a 15 pixeles
		dirActual = direccion;
		this.avanzar();
	}

	public boolean avanzar() { // Devuelve true cuando no tiene que avanzar mas
		timer++;
		if (timer > 10000/velocidad) { 
			if (dirActual == Direcciones.DERECHA) {
				if (posX + 1 > LIMITE_DERECHO_EDIFICIO) {
					dirActual = Direcciones.IZQUIERDA;
					posX--;
				} else
					posX++;
			} else if (posX - 1 < LIMITE_IZQUIERDA_EDIFICIO) {
				dirActual = Direcciones.DERECHA;
				posX++;
			} else
				posX--;
			pasosRestantes--;
			timer=0;
		}
		return (pasosRestantes == 0);
	}

}
