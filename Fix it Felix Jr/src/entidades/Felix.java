package entidades;

import java.util.LinkedList;
import java.util.List;

import controladores.ControladorDeJuego;
import edificio.Seccion;
import edificio.Ventana;
import excepciones.NotAllowedMovementException;
import juego.Juego;

/**
 * Modela al personaje y jugador Felix, sus acciones y movimientos posibles
 * 
 * @author Lucas y Renzo
 *
 */
public class Felix {

	private static final int tiempoInvulnerabilidad = 2;

	private int vidas;
	private int timerInvulnerabilidad, timerReparacion, timerMovimiento;
	private boolean vulnerable;
	private boolean estaMoviendose, estaReparando, estaMuriendose;

	private Ventana ventanaActual;
	private EstadoFelix estado;
	private Seccion seccionActual;

	private static Felix INSTANCE;

	private Felix() {
		this.vidas = 3;
		this.vulnerable = true;
		this.timerInvulnerabilidad = 0;
		timerReparacion=0;
		timerMovimiento=0;
		estaMoviendose=false;
		estaMuriendose=false;
		estaReparando=false;
		estado=EstadoFelix.NORMAL;
	}

	/**
	 * 
	 * @return INSTANCIA de Felix, de acuerdo al patron de diseño Singleton
	 */
	public static Felix getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Felix();
		}
		return INSTANCE;
	}

	/**
	 * Repara (intenta cambiar el estado de los paneles a SANO) la ventana sobre la
	 * cual se encuentra posicionado
	 */
	public boolean reparar() {
		boolean reparada= false;
		if (!estaHaciendoAlgo()) {
			estado = EstadoFelix.REPARANDO;
			estaReparando = true;
			boolean sanaAntes = ventanaActual.estoySana();
			ventanaActual.repararse();
			if (!sanaAntes && ventanaActual.estoySana()) {
				reparada = true;
				Juego.getInstance().getJugador().sumarPuntaje(100);
				seccionActual.disminuirVentanasRestantes();
				Juego.getInstance().comprobarSeccionLimpia(seccionActual);
			}
		}
		return reparada;
	}
	
	public boolean estaHaciendoAlgo() {
		return (estaReparando || estaMoviendose || estaMuriendose);
	}

	public void recibirImpactoPastel() {
		vulnerable = false;
		Juego.getInstance().getJugador().sumarPuntaje(500);
		System.out.println("Felix se comió un pastel y ahora es invulnerable!");
	}

	public void recibirImpactoLadrillo() {
		if (vulnerable) {
			vidas--;
		}
	}

	public int getVidas() {
		return vidas;
	}

	public boolean isVulnerable() {
		return vulnerable;
	}

	public Ventana getVentanaActual() {
		return ventanaActual;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public void setVulnerable(boolean vulnerable) {
		this.vulnerable = vulnerable;
	}

	public void setVentanaActual(Ventana ventanaActual) {
		this.ventanaActual = ventanaActual;
	}

	public Seccion getSeccionActual() {
		return seccionActual;
	}

	public void setSeccionActual(Seccion seccionActual) {
		this.seccionActual = seccionActual;
	}

	public boolean mover(Direcciones dir) throws NotAllowedMovementException {
		Ventana proximaVentana;
		boolean seMovio = false;
		if (!estaHaciendoAlgo()) {
			if (ventanaActual.puedePasar(dir)) {
				switch (dir) {
				case DERECHA:
					proximaVentana = seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual
							.getNroColumna() + 1];
					if (proximaVentana.puedePasar(Direcciones.IZQUIERDA)) {
						ventanaActual = proximaVentana;
						seMovio = true;
					}
					break;
				case IZQUIERDA:
					proximaVentana = seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual
							.getNroColumna() - 1];
					if (proximaVentana.puedePasar(Direcciones.DERECHA)) {
						ventanaActual = proximaVentana;
						seMovio = true;
					}
					break;
				case ARRIBA:
					proximaVentana = seccionActual.getVentanas()[ventanaActual.getNroFila() - 1][ventanaActual
							.getNroColumna()];
					if (proximaVentana.puedePasar(Direcciones.ABAJO)) {
						ventanaActual = proximaVentana;
						seMovio = true;
					}
					break;
				case ABAJO:
					proximaVentana = seccionActual.getVentanas()[ventanaActual.getNroFila() + 1][ventanaActual
							.getNroColumna()];
					if (proximaVentana.puedePasar(Direcciones.ARRIBA)) {
						ventanaActual = proximaVentana;
						seMovio = true;
					}
					break;
				}
			}
		}
		if (seMovio) {
			estaMoviendose=true;
			estado=EstadoFelix.MOVIENDOSE;
		}
		return seMovio;
	}

	public void actualizarInvulnerabilidad() {
		if (this.vulnerable == false) {
			timerInvulnerabilidad++;
			if (timerInvulnerabilidad > tiempoInvulnerabilidad * 1000 / ControladorDeJuego.ACTUALIZACION) {
				this.vulnerable = true;
				timerInvulnerabilidad = 0;
			}
		}
	}
	
	public void actualizar() {
		if (estaMuriendose) {
		}
		if (estaReparando) {
			timerReparacion++;
			if (timerReparacion > 200 / ControladorDeJuego.ACTUALIZACION) {
				estado = EstadoFelix.NORMAL;
				timerReparacion = 0;
				estaReparando = false;
			}
		}
		if (estaMoviendose) {
			timerMovimiento++;
			if (timerMovimiento> 200 / ControladorDeJuego.ACTUALIZACION) {
				estado= EstadoFelix.NORMAL;
				timerMovimiento=0;
				estaMoviendose=false;
			}
		}
	}

	public Posicion getPos() {
		int posX = 0;
		int posY = 0;
		posX = 224 + 52 * ventanaActual.getNroColumna();
		posY = 98 + 80 * ventanaActual.getNroFila();
		return (new Posicion(posX, posY));
	}
	
	public InfoGraficable<EstadoFelix> getInfoGraficable(){
		InfoGraficable<EstadoFelix> info= new InfoGraficable<EstadoFelix>();
		List<Posicion> pos= new LinkedList<Posicion>();
		pos.add(getPos());
		List<EstadoFelix> estado= new LinkedList<EstadoFelix>();
		estado.add(this.estado);
		info.setListaEstados(estado);
		info.setListaPosiciones(pos);
		return info;
	}

}
