package controladores;

import java.util.Timer;
import java.util.TimerTask;

import grafica.FrameJuego;
import grafica.Menu;
import juego.Juego;

public class ControladorDeJuego {
	
	public static final int ACTUALIZACION=5;
	private FrameJuego frameJuego;
	private Timer timer;
	public static boolean perdio = false;
	public static boolean gano=false;
	
	public FrameJuego getFrameJuego() {
		return frameJuego;
	}

	public ControladorDeJuego(Menu m) {
		frameJuego= new FrameJuego(m);
		frameJuego.setVisible(true);
		timer= new Timer();
	}
	
	public void jugar() {
		Juego.getInstance().iniciarNivel(false);
		TimerTask gameUpdate= new TimerTask() {
			public void run() {
				if (!perdio || !gano) Juego.getInstance().actualizar();
				frameJuego.repaint();
				//if (perdio || gano) timer.cancel();
			}
		};
		timer.schedule(gameUpdate, 1, ACTUALIZACION);
//		hiloJuego= new Thread(Juego.getInstance(),"hiloJuego");
//		hiloJuego.start();
//		Juego.getInstance().setNroNivel(nivelElegido);
//		Juego.getInstance().iniciarNivel(false);
	}
}
