package entidades;

import java.applet.AudioClip;
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

	private static final int tiempoInvulnerabilidad = 3;

	private int vidas;
	private int timerInvulnerabilidad, timerReparacion, timerMovimiento;
	private boolean vulnerable;
	private boolean estaMoviendose, estaReparando, estaMuriendose;

	private Ventana ventanaActual;
	private EstadoFelix estado;
	private Seccion seccionActual;

	private int timerMuerte;

	private static Felix INSTANCE;
	private AudioClip pajaroGolpeoFelix, ladrilloGolpeoFelix,mover;

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
		pajaroGolpeoFelix= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/felixGolpeado2.wav"));
		ladrilloGolpeoFelix= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/felixGolpeado1.wav"));
		mover= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/mover.wav"));
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
		estado= EstadoFelix.INVULNERABLE;
		vulnerable = false;
		Juego.getInstance().getJugador().sumarPuntaje(500);
	}

	public void recibirImpactoLadrillo() {
		if (vulnerable) {
			vidas--;
			ladrilloGolpeoFelix.play();
			estaMoviendose=false;
			estaReparando=false;
			estaMuriendose=true;
			estado= EstadoFelix.MUERTO;
			timerReparacion=0;
			timerMovimiento=0;
		}
	}
	
	public void recibirImpactoPajaro() {
		if (vulnerable) {
			pajaroGolpeoFelix.play();
			estaMoviendose = false;
			estaReparando = false;
			estaMuriendose = true;
			estado = EstadoFelix.MUERTO;
			timerReparacion = 0;
			timerMovimiento = 0;
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

	public void mover(Direcciones dir) throws NotAllowedMovementException {
		Ventana proximaVentana;
		if (!estaHaciendoAlgo()) {
			ventanaActual.puedePasar(dir);
			switch (dir) {
			case DERECHA:
				proximaVentana = seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()
						+ 1];
				proximaVentana.puedePasar(Direcciones.IZQUIERDA);
				ventanaActual = proximaVentana;
				break;
			case IZQUIERDA:
				proximaVentana = seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()
						- 1];
				proximaVentana.puedePasar(Direcciones.DERECHA);
				ventanaActual = proximaVentana;
				break;
			case ARRIBA:
				proximaVentana = seccionActual.getVentanas()[ventanaActual.getNroFila() - 1][ventanaActual
						.getNroColumna()];
				proximaVentana.puedePasar(Direcciones.ABAJO);
				ventanaActual = proximaVentana;
				break;
			case ABAJO:
				proximaVentana = seccionActual.getVentanas()[ventanaActual.getNroFila() + 1][ventanaActual
						.getNroColumna()];
				proximaVentana.puedePasar(Direcciones.ARRIBA);
				ventanaActual = proximaVentana;
				break;
			}
			mover.play();
			estaMoviendose = true;
			estado = EstadoFelix.MOVIENDOSE;
		}
	}
	
	public void actualizar() {
		if (estaMuriendose) {
			timerMuerte++;
			if (timerMuerte> 600 / ControladorDeJuego.ACTUALIZACION) {
				estado = EstadoFelix.NORMAL;
				timerMuerte= 0;
				estaMuriendose= false;
				ventanaActual= seccionActual.getVentanas()[2][2];
			}
		}
		if (!vulnerable) {
			timerInvulnerabilidad++;
			if (timerInvulnerabilidad > tiempoInvulnerabilidad * 1000 / ControladorDeJuego.ACTUALIZACION) {
				estado= EstadoFelix.NORMAL;
				vulnerable = true;
				timerInvulnerabilidad = 0;
			}
		}
		if (estaReparando) {
			timerReparacion++;
			if (timerReparacion > 200 / ControladorDeJuego.ACTUALIZACION) {
				if (!vulnerable) estado=EstadoFelix.INVULNERABLE;
				else estado= EstadoFelix.NORMAL;
				timerReparacion = 0;
				estaReparando = false;
			}
		}
		if (estaMoviendose) {
			timerMovimiento++;
			if (timerMovimiento> 200 / ControladorDeJuego.ACTUALIZACION) {
				if (!vulnerable) estado=EstadoFelix.INVULNERABLE;
				else estado= EstadoFelix.NORMAL;
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

	public void reiniciarVidas() {
		this.vidas=3;
	}

}
