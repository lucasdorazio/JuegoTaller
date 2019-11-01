package controladores;

import entidades.Direcciones;
import entidades.Ralph;
import juego.Juego;

/**
 * ControladorDeRalph manejara al objeto de clase Ralph, sus movimientos
 * y comportamiento
 * @author Lucas y Renzo
 * @version 1.0
 */
public class ControladorDeRalph extends Controlador{
	private int tiempoDeGolpeo; // Cada cuantos segundos Ralph golpea
	private int tiempoDeDesplazamiento; // Cada cuantos segundos Ralph se mueve
	private static final int CANT_PASOS_MIN = 3;
	private static final int CANT_PASOS_MAX = 7;
	private boolean estaMoviendose;
	private boolean estaGolpeando;
	private int timerGolpeo;
	private int timerMovimiento;
	private Ralph ralph;

	/**
	 * 
	 * @param tiempoDeGolpeo recibe cada cuanto tiempo ralph realiza un golpe
	 */
	public ControladorDeRalph(int tiempoDeGolpeo, ControladorDeLadrillos brickController) {
		this.tiempoDeGolpeo=tiempoDeGolpeo;
		this.tiempoDeDesplazamiento= 12;
		ralph= new Ralph(brickController);
		timerGolpeo = 0;
		timerMovimiento = 0;
		estaMoviendose=false;
		estaGolpeando=false;
	}
	/**
	 * 
	 * @return cantidad de pasos que ralf dará en cierta direccion
	 * entre un minimo y un maximo fijo
	 */
	public int calcularCantPasos() {
		return (int) (Math.random() * (CANT_PASOS_MIN - CANT_PASOS_MAX + 1) + CANT_PASOS_MIN);
	}

	
	/**
	 * Se encarga de darle ordenes a ralph, tales como moverse,
	 * cambiar de direccion, golpear edificio
	 */
	public void actualizar() {
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
			if (timerMovimiento > (tiempoDeDesplazamiento * Juego.getConstTiempo())) { 
				if ((int) (Math.random() * 2) == 0)
					dir = Direcciones.DERECHA;
				else
					dir = Direcciones.IZQUIERDA;
				ralph.comenzarMovimiento(calcularCantPasos(), dir);
				estaMoviendose = true;
			} else if (timerGolpeo > (tiempoDeGolpeo * Juego.getConstTiempo())) {
				ralph.comenzarGolpeo();
				estaGolpeando = true;
			}
		}
	}
	
	public void avanzarSeccion() {
	}
}
