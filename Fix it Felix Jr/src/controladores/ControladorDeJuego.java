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
	private Thread hiloJuego;
	private Thread hiloPausa;
	
	public FrameJuego getFrameJuego() {
		return frameJuego;
	}

	public ControladorDeJuego(Menu m) {
		frameJuego= new FrameJuego(m);
		frameJuego.setVisible(true);
		timer= new Timer();
	}
	
	public void jugar() {
		boolean perdio = false;
		TimerTask gameUpdate= new TimerTask() {
			public void run() {
				Juego.getInstance().actualizar();
				frameJuego.repaint();
				//frameJuego.update(frameJuego.getGraphics());
				//frameJuego.paint(frameJuego.getGraphics());
				if (perdio) timer.cancel();
			}
		};
		timer.schedule(gameUpdate, 1, 100);
//		hiloJuego= new Thread(Juego.getInstance(),"hiloJuego");
//		hiloJuego.start();
//		Juego.getInstance().setNroNivel(nivelElegido);
//		Juego.getInstance().iniciarNivel(false);
	}
}
