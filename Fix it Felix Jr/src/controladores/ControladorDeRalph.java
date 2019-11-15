package controladores;

import java.util.LinkedList;
import java.util.List;

import entidades.Direcciones;
import entidades.Posicion;
import entidades.Ralph;

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
	private final double TIEMPO_ENTRE_LADRILLOS=0.3;
	private int velocidad;
	private boolean estaMoviendose;
	private boolean estaGolpeando;
	private int timerIntervaloGolpeo;
	private int timerIntervaloMovimiento;
	private int timerMover;
	private int timerGolpear;
	private Ralph ralph;

	/**
	 * 
	 * @param tiempoDeGolpeo recibe cada cuanto tiempo ralph realiza un golpe
	 */
	public ControladorDeRalph(int tiempoDeGolpeo, ControladorDeLadrillos brickController) {
		this.tiempoDeGolpeo=tiempoDeGolpeo;
		this.tiempoDeDesplazamiento= 12;
		this.velocidad=150;
		ralph= new Ralph(brickController);
		timerIntervaloGolpeo = 1000;
		timerIntervaloMovimiento = 0;
		estaMoviendose=false;
		estaGolpeando=false;
	}
	/**
	 * 
	 * @return cantidad de pasos que ralf dará en cierta direccion
	 * entre un minimo y un maximo fijo
	 */
	public int calcularCantPasos() {
		return (int) (Math.random() * (CANT_PASOS_MAX - CANT_PASOS_MIN + 1) + CANT_PASOS_MIN);
	}

	
	/**
	 * Se encarga de darle ordenes a ralph, tales como moverse,
	 * cambiar de direccion, golpear edificio
	 */
	public void actualizar() {
		Direcciones dir;
		if (estaMoviendose) {
			timerMover++;
			if (timerMover > 1000 / (velocidad * ControladorDeJuego.ACTUALIZACION)) {
				if (ralph.avanzar()) {
					estaMoviendose = false;
					timerIntervaloMovimiento = 0;
					System.out.println("Ralph termino de moverse y su posicion es (" + ralph.getPos().getPosX() + ";"
							+ ralph.getPos().getPosY() + ")");
				}
				timerMover=0;
			}
		} else if (estaGolpeando) {
			timerGolpear++;
			if (timerGolpear > TIEMPO_ENTRE_LADRILLOS* 1000 / ControladorDeJuego.ACTUALIZACION) {
				if (ralph.golpearEdif()) {
					estaGolpeando = false;
					timerIntervaloGolpeo = 0;
				}
				timerGolpear=0;
			}
		} else {
			timerIntervaloMovimiento++;
			timerIntervaloGolpeo++;
			if (timerIntervaloMovimiento > (tiempoDeDesplazamiento * 1000 / ControladorDeJuego.ACTUALIZACION)) {
				if ((int) (Math.random() * 2) == 0)
					dir = Direcciones.DERECHA;
				else
					dir = Direcciones.IZQUIERDA;
				ralph.comenzarMovimiento(calcularCantPasos(), dir);
				timerMover = 0;
				estaMoviendose = true;
			} else if (timerIntervaloGolpeo > (tiempoDeGolpeo * 1000 / ControladorDeJuego.ACTUALIZACION)) {
				ralph.comenzarGolpeo();
				timerGolpear = 0;
				estaGolpeando = true;
			}
		}
	}

	public void avanzarSeccion() {
	}
	@Override
	public List<Posicion> getListaPosEntidades() {
		List<Posicion> lista = new LinkedList<Posicion>();
		lista.add(ralph.getPos());
		return lista;
	}
}