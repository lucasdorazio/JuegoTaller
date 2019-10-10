package juego;
/**
 * 
 * @author Lucas y Renzo
 *
 */
public class ControladorDeRalph {
	private int tiempoDeGolpeo; // Cada cuantos segundos Ralph golpea
	private int tiempoDeDesplazamiento; // Cada cuantos segundos Ralph se mueve
	private static final int CANT_PASOS_MIN = 3;
	private static final int CANT_PASOS_MAX = 7;
	private static final int CONST_TIEMPO=60000000;	//Cantidad de llamadas al método por segundo
	private boolean estaMoviendose;
	private boolean estaGolpeando;
	private int timerGolpeo;
	private int timerMovimiento;
	private Ralph ralph;

	public ControladorDeRalph(int tiempoDeGolpeo) {
		this.tiempoDeGolpeo=tiempoDeGolpeo;
		this.tiempoDeDesplazamiento= 12;
		ralph= new Ralph();
		timerGolpeo = 0;
		timerMovimiento = 0;
		estaMoviendose=false;
		estaGolpeando=false;
	}
	
	public int calcularCantPasos() {
		return (int) (Math.random() * (CANT_PASOS_MIN - CANT_PASOS_MAX + 1) + CANT_PASOS_MIN);
	}

	
	
	public void manejarRalph() {
		Direcciones dir;
		if (estaMoviendose) {
			if (ralph.avanzar()) {
				estaMoviendose = false;
				timerMovimiento = 0;
				System.out.println("Ralph termino de moverse y su posicion es (" +ralph.getPos().getPosX()+ ";" + ralph.getPos().getPosY()+")");
			}
		} else if (estaGolpeando) {
			if (ralph.golpearEdif()) {
				estaGolpeando=false;
				timerGolpeo = 0;
			}
		} else {
			timerMovimiento++;
			timerGolpeo++;
			if (timerMovimiento > (tiempoDeDesplazamiento * CONST_TIEMPO)) { 
				if ((int) (Math.random() * 2) == 0)
					dir = Direcciones.DERECHA;
				else
					dir = Direcciones.IZQUIERDA;
				ralph.comenzarMovimiento(calcularCantPasos(), dir);
				estaMoviendose = true;
			} else if (timerGolpeo > (tiempoDeGolpeo * CONST_TIEMPO)) {
				ralph.comenzarGolpeo();
				estaGolpeando = true;
			}
		}
	}
}
