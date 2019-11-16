package controladores;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import excepciones.ImproperNameException;
import excepciones.InvalidCharacterNameException;
import excepciones.TooLongNameException;
import excepciones.TooShortNameException;
import grafica.FrameJuego;
import grafica.Menu;
import juego.Juego;
import juego.Jugador;
import juego.Ranking;

public class ControladorDeJuego {
	
	public static final int ACTUALIZACION=5;
	private FrameJuego frameJuego;
	private Timer timer;
	public static boolean perdio = false;
	public static boolean gano=false;
	private Ranking ranking;
	
	public FrameJuego getFrameJuego() {
		return frameJuego;
	}

	public ControladorDeJuego(Menu m) {
		frameJuego= new FrameJuego(m);
		timer= new Timer();
		ranking= new Ranking();
		ranking.leerRanking();
	}
	
	public void jugar() {
		frameJuego.setVisible(true);
		Juego.getInstance().iniciarNivel(false);
		TimerTask gameUpdate= new TimerTask() {
			public void run() {
				Juego.getInstance().actualizar();
				if (Juego.getInstance().perdio()) {
					perder(Juego.getInstance().getJugador());
					this.cancel();
					frameJuego.dispose();
				}
				if (Juego.getInstance().gano()) {
					ganar(Juego.getInstance().getJugador());
					this.cancel();
					frameJuego.dispose();
				}
//				else System.out.println("No entro al if de ganar");
			}
		};
		TimerTask viewUpdate= new TimerTask() {
			public void run() {
				frameJuego.repaint();
			}
		};
		timer.schedule(gameUpdate, 0, ACTUALIZACION);
		timer.schedule(viewUpdate, 0, ACTUALIZACION);
//		hiloJuego= new Thread(Juego.getInstance(),"hiloJuego");
//		hiloJuego.start();
//		Juego.getInstance().setNroNivel(nivelElegido);
//		Juego.getInstance().iniciarNivel(false);
	}
	
	public void ganar(Jugador jugador) {
		boolean nombreCorrecto=false;
		System.out.println("Ganaste, congratuleishon, tu punteaje fue:"+ jugador.getPuntaje());
		if (ranking.estaEntreLosMejoresCinco(jugador.getPuntaje())) {
			while (!nombreCorrecto) {
				try {
					String nombre= pedirNombre();
					nombreCorrecto = true;
					jugador.setNick(nombre);
				} catch (ImproperNameException e) {
					System.out.println("ERROR: " + e.getMessage());
				}
			}
			ranking.actualizarRankingNuevo(jugador);
		}
	}
	
	public void perder(Jugador jugador) {
		ControladorDeJuego.perdio=true;
		boolean nombreCorrecto=false;
		System.out.println("Lo lamento, has perdido. Tu punteaje fue: "+ jugador.getPuntaje());
		if (ranking.estaEntreLosMejoresCinco(jugador.getPuntaje())) {
			while (!nombreCorrecto) {
				try {
					String nombre= pedirNombre();
					nombreCorrecto = true;
					jugador.setNick(nombre);
				} catch (ImproperNameException e) {
					System.err.println("ERROR: " + e.getMessage());
				}
			}
			ranking.actualizarRankingNuevo(jugador);
		}
	}
	
	public String pedirNombre() throws ImproperNameException{
		Scanner teclado= new Scanner(System.in);
		String nombre;
		System.out.println("Ingrese su nombre");
		nombre= teclado.next();
		teclado.close();
		if (nombre.length() < 2) throw new TooShortNameException();
		if (nombre.length() > 20) throw new TooLongNameException();
		if (nombre.contains(" ")) throw new InvalidCharacterNameException();
		return nombre;
	}
	
	
	public Ranking getMejoresJugadores() {
		return ranking;
	}
}
