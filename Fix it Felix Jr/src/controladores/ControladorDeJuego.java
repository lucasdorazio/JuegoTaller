package controladores;

import java.util.Timer;
import java.util.TimerTask;

import grafica.FrameJuego;
import grafica.Menu;
import juego.Juego;

public class ControladorDeJuego {
	private int nivelElegido = 0;
	private FrameJuego frameJuego;
	private Timer timer;
	
	public FrameJuego getFrameJuego() {
		return frameJuego;
	}

	public ControladorDeJuego(Menu m) {
		frameJuego= new FrameJuego(m);
		frameJuego.setVisible(false);
	}
	
	public void jugar() {
		boolean perdio = false;
		TimerTask gameUpdate= new TimerTask() {
			public void run() {
				Juego.getInstance().actualizar();
				frameJuego.repaint();
				if (perdio) timer.cancel();
			}
		};
		Juego.getInstance().setNroNivel(nivelElegido);
		Juego.getInstance().iniciarNivel(false);
		timer.schedule(gameUpdate, 1, 1000);
	}

}
