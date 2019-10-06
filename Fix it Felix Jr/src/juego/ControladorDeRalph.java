package juego;

public class ControladorDeRalph {
	private int tiempoDeGolpeo; // Cada cuantos segundos Ralph golpea
	private int tiempoDeDesplazamiento; // Cada cuantos segundos Ralph se mueve
	private static final int CANT_PASOS_MIN = 5;
	private static final int CANT_PASOS_MAX = 12;
	private boolean estaMoviendose;
	private boolean estaGolpeando;
	private int timerGolpeo;
	private int timerMovimiento;
	private Ralph ralph;

	public ControladorDeRalph(int tiempoDeGolpeo) {
		this.tiempoDeGolpeo=tiempoDeGolpeo;
		this.tiempoDeDesplazamiento= 5;
		ralph= new Ralph();
		timerGolpeo = 0;
		timerMovimiento = 0;
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
			}
		} else if (estaGolpeando) {
			if (ralph.golpearEdif()) {
				estaGolpeando=false;
				timerGolpeo = 0;
			}
		} else {
			timerMovimiento++;
			timerGolpeo++;
			if (timerMovimiento > (tiempoDeDesplazamiento * 10000)) { // Timer se aumenta aproximadamente 10000 veces
																		// por segundo
				if ((int) (Math.random() * 2) == 0)
					dir = Direcciones.DERECHA;
				else
					dir = Direcciones.IZQUIERDA;
				ralph.comenzarMovimiento(calcularCantPasos(), dir);
				estaMoviendose = true;
			} else if (timerGolpeo > (tiempoDeGolpeo * 10000)) {
				ralph.comenzarGolpeo();
				estaGolpeando = true;
			}
		}
	}
}
