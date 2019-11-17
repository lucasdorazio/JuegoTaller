package controladores;

import java.applet.AudioClip;
import java.util.LinkedList;
import java.util.List;

import entidades.Direcciones;
import entidades.EstadoRalph;
import entidades.InfoGraficable;
import entidades.Posicion;
import entidades.Ralph;

/**
 * ControladorDeRalph manejara al objeto de clase Ralph, sus movimientos
 * y comportamiento
 * @author Lucas y Renzo
 * @version 1.0
 */
public class ControladorDeRalph extends Controlador<EstadoRalph>{
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
	private int timerSwapCaminata;
	private Ralph ralph;
	private AudioClip golpeRalph;

	/**
	 * 
	 * @param tiempoDeGolpeo recibe cada cuanto tiempo ralph realiza un golpe
	 */
	public ControladorDeRalph(int tiempoDeGolpeo, ControladorDeLadrillos brickController) {
		this.tiempoDeGolpeo=tiempoDeGolpeo;
		this.tiempoDeDesplazamiento= 6;
		this.velocidad=150;
		ralph= new Ralph(brickController);
		timerIntervaloGolpeo = 1000;
		timerIntervaloMovimiento = 0;
		estaMoviendose=false;
		estaGolpeando=false;
		golpeRalph= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/golpe3.wav"));
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
				timerSwapCaminata++;
				if (timerSwapCaminata == 20) {
					switch (ralph.getEstado()) {
					case CAMINANDO_DERECHA1:
						ralph.setEstado(EstadoRalph.CAMINANDO_DERECHA2);
						break;
					case CAMINANDO_DERECHA2:
						ralph.setEstado(EstadoRalph.CAMINANDO_DERECHA1);
						break;
					case CAMINANDO_IZQUIERDA1:
						ralph.setEstado(EstadoRalph.CAMINANDO_IZQUIERDA2);
						break;
					case CAMINANDO_IZQUIERDA2:
						ralph.setEstado(EstadoRalph.CAMINANDO_IZQUIERDA1);
						break;
					default:
						break;
					}
					timerSwapCaminata=0;
				}
				if (ralph.avanzar()) {
					ralph.setEstado(EstadoRalph.NORMAL1);
					estaMoviendose = false;
					timerIntervaloMovimiento = 0;
				}
				timerMover=0;
			}
		} else if (estaGolpeando) {
			timerGolpear++;
			if (timerGolpear > TIEMPO_ENTRE_LADRILLOS* 1000 / ControladorDeJuego.ACTUALIZACION) {
				golpeRalph.play();
				if (ralph.getEstado() == EstadoRalph.GOLPEANDO1) ralph.setEstado(EstadoRalph.GOLPEANDO2);
				else ralph.setEstado(EstadoRalph.GOLPEANDO1);
				if (ralph.golpearEdif()) {
					ralph.setEstado(EstadoRalph.NORMAL1);
					estaGolpeando = false;
					timerIntervaloGolpeo = 0;
				}
				timerGolpear=0;
			}
		} else {
//			ralph.setEstado(EstadosRalph.NORMAL1);
			timerIntervaloMovimiento++;
			timerIntervaloGolpeo++;
			if (timerIntervaloMovimiento > (tiempoDeDesplazamiento * 1000 / ControladorDeJuego.ACTUALIZACION)) {
				if ((int) (Math.random() * 2) == 0)
					dir = Direcciones.DERECHA;
				else
					dir = Direcciones.IZQUIERDA;
				ralph.comenzarMovimiento(8, dir);
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
	public InfoGraficable<EstadoRalph> getListaInfoGraficable() {
		InfoGraficable<EstadoRalph> info = new InfoGraficable<EstadoRalph>();
		List<Posicion> pos = new LinkedList<Posicion>();
		pos.add(ralph.getPos());
		List<EstadoRalph> estado = new LinkedList<EstadoRalph>();
		estado.add(ralph.getEstado());
		info.setListaEstados(estado);
		info.setListaPosiciones(pos);
		return info;
	}
	
	public Posicion getPosRalph() {
		return ralph.getPos();
	}
}