package grafica;

import java.applet.AudioClip;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import entidades.Direcciones;
import entidades.Felix;
import excepciones.NotAllowedMovementException;
import juego.EstadoJuego;
import juego.Juego;

public class KeyGameAdapter extends KeyAdapter {
	
	private AudioClip direccionInvalida, ventanaReparada;
	
	public KeyGameAdapter() {
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
		default:
			break;
		}
	}
}
