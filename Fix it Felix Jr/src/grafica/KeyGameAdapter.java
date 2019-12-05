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
		case KeyEvent.VK_LEFT:
			try {
				if (Juego.getInstance().getEstado() != EstadoJuego.PAUSA)
					Felix.getInstance().mover(Direcciones.IZQUIERDA);
			} catch (NotAllowedMovementException e) {
				direccionInvalida.play();
			}
			break;
		case KeyEvent.VK_UP:
			try {
				if (Juego.getInstance().getEstado() != EstadoJuego.PAUSA)
					Felix.getInstance().mover(Direcciones.ARRIBA);
			} catch (NotAllowedMovementException e) {
				direccionInvalida.play();
			}
			break;
		case KeyEvent.VK_RIGHT:
			try {
				if (Juego.getInstance().getEstado() != EstadoJuego.PAUSA)
					Felix.getInstance().mover(Direcciones.DERECHA);
			} catch (NotAllowedMovementException e) {
				direccionInvalida.play();
			}
			break;
		case KeyEvent.VK_DOWN:
			try {
				if (Juego.getInstance().getEstado() != EstadoJuego.PAUSA)
					Felix.getInstance().mover(Direcciones.ABAJO);
			} catch (NotAllowedMovementException e) {
				direccionInvalida.play();
			}
			break;
		case KeyEvent.VK_P:
			Juego.getInstance().alternarPausa();
			break;
		case KeyEvent.VK_SPACE:
			if (Juego.getInstance().getEstado() != EstadoJuego.PAUSA)
				if (Felix.getInstance().reparar())
					ventanaReparada.play();
			break;
		default:
			break;
		}
	}
}
