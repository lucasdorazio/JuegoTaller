package controladores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import entidades.Direcciones;
import entidades.Felix;
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
		frameJuego.addKeyListener(new KeyAdapter() {
				public void keyPressed (KeyEvent tecla) {
					switch (tecla.getKeyCode()) {
						case 37:
							System.out.println("se presiono la flechita pa izq");
							Felix.getInstance().mover(Direcciones.IZQUIERDA);
							break;
						case 38:
							System.out.println("se presiono la flechita pa arriba");
							Felix.getInstance().mover(Direcciones.ARRIBA);
							break;
						case 39:
							System.out.println("se presiono la flechita pa derecha");
							Felix.getInstance().mover(Direcciones.DERECHA);
							break;
						case 40:
							System.out.println("se presiono la flechita pa abajo");
							Felix.getInstance().mover(Direcciones.ABAJO);
							break;
						case 80:
							System.out.println("Se apreto la p");
							if (hiloPausa.isAlive()) {
								hiloPausa.stop();
							}
							break;
						default: System.out.println("otra tecla");
						break;
					}
				}
		});
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
		hiloJuego= new Thread(Juego.getInstance(),"hiloJuego");
		hiloJuego.start();
		Juego.getInstance().setNroNivel(nivelElegido);
		Juego.getInstance().iniciarNivel(false);
		timer.schedule(gameUpdate, 1, 1000);
	}
}
