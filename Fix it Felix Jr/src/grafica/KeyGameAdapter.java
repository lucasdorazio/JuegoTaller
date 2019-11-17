package grafica;

import java.applet.AudioClip;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import edificio.Ventana;
import entidades.Direcciones;
import entidades.Felix;
import excepciones.NotAllowedMovementException;
import juego.EstadoJuego;
import juego.Juego;

@SuppressWarnings("deprecation")
public class KeyGameAdapter extends KeyAdapter {
	
	private AudioClip sonidoP, direccionInvalida, ventanaReparada;
	
	public KeyGameAdapter() {
		sonidoP= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/notaA.wav"));
		direccionInvalida= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/direccionNoPermitida.wav"));
		ventanaReparada= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/ventanaReparada3.wav"));
	}
	
	public void keyPressed(KeyEvent tecla) {
		switch (tecla.getKeyCode()) {
		case 37:
			try {
				if (Juego.getInstance().getEstado() != EstadoJuego.PAUSA)
					Felix.getInstance().mover(Direcciones.IZQUIERDA);
			} catch (NotAllowedMovementException e) {
				direccionInvalida.play();
			}
			break;
		case 38:
			try {
				if (Juego.getInstance().getEstado() != EstadoJuego.PAUSA)
					Felix.getInstance().mover(Direcciones.ARRIBA);
			} catch (NotAllowedMovementException e) {
				direccionInvalida.play();
			}
			break;
		case 39:
			try {
				if (Juego.getInstance().getEstado() != EstadoJuego.PAUSA)
					Felix.getInstance().mover(Direcciones.DERECHA);
			} catch (NotAllowedMovementException e) {
				direccionInvalida.play();
			}
			break;
		case 40:
			try {
				if (Juego.getInstance().getEstado() != EstadoJuego.PAUSA)
					Felix.getInstance().mover(Direcciones.ABAJO);
			} catch (NotAllowedMovementException e) {
				direccionInvalida.play();
			}
			break;
		case 80:
			if (Juego.getInstance().getEstado() != EstadoJuego.PAUSA) {
				Juego.getInstance().setEstado(EstadoJuego.PAUSA);
			} else
				Juego.getInstance().setEstado(EstadoJuego.NORMAL);
			break;
		case 32:
			if (Juego.getInstance().getEstado() != EstadoJuego.PAUSA)
				if (Felix.getInstance().reparar())
					ventanaReparada.play();
			break;
		case 72: //H= hack para pasar de seccion si no hay ventanas rotas
			Juego.getInstance().comprobarSeccionLimpia(Felix.getInstance().getSeccionActual());
			break;
		case 87: //w voy arriba aunque haya obstaculo (tira error)
			Felix.getInstance().setVentanaActual(Felix.getInstance().getSeccionActual().getVentanas()[Felix.getInstance().getVentanaActual().getNroFila()-1][Felix.getInstance().getVentanaActual().getNroColumna()]);
			break;
		case 65: //A voy a la izquierda aunque haya obstaculo (tira error)
			Felix.getInstance().setVentanaActual(Felix.getInstance().getSeccionActual().getVentanas()[Felix.getInstance().getVentanaActual().getNroFila()][Felix.getInstance().getVentanaActual().getNroColumna()-1]);
			break;
		case 83: //S voy abajo aunque haya obstaculo (tira error)
			Felix.getInstance().setVentanaActual(Felix.getInstance().getSeccionActual().getVentanas()[Felix.getInstance().getVentanaActual().getNroFila()+1][Felix.getInstance().getVentanaActual().getNroColumna()]);
			break;
		case 68: //D voy a la derecha aunque haya obstaculo (tira error)
			Felix.getInstance().setVentanaActual(Felix.getInstance().getSeccionActual().getVentanas()[Felix.getInstance().getVentanaActual().getNroFila()][Felix.getInstance().getVentanaActual().getNroColumna()+1]);
			break;
		case 46:
			Ventana v= Felix.getInstance().getVentanaActual();
			if (!v.estoySana()) {
			while (!v.estoySana()) v.repararse();
			Juego.getInstance().getJugador().sumarPuntaje(100);
			Felix.getInstance().getSeccionActual().disminuirVentanasRestantes();
			Juego.getInstance().comprobarSeccionLimpia(Felix.getInstance().getSeccionActual());
			}
			break;
		case 76:
			Felix.getInstance().setVidas(0);
			break;
		default:
			System.out.println("otra tecla: " + tecla.getKeyCode());
			break;
		}
	}
}


//AudioClip sonido;
//sonido= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/ventanaReparada1.wav"));
//sonido.play();