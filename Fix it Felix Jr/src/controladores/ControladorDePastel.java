package controladores;

import java.util.LinkedList;
import java.util.List;

import edificio.Edificio;
import edificio.Ventana;
import entidades.Felix;
import entidades.Pastel;
import entidades.Posicion;
import juego.Juego;

public class ControladorDePastel extends Controlador {

	//private int timer;

	private int tiempoSpawneo;

	private Pastel pastel;
	private int timer;

	public ControladorDePastel() {
		this.tiempoSpawneo = 5;
		timer=0;
	}

	public void actualizar() {
		if (pastel == null)
			this.generarPastel();
		else {
			if (pastel.devolverVentana().equals(Felix.getInstance().getVentanaActual())) {
				Felix.getInstance().recibirImpactoPastel();
				pastel = null;
			} else if (pastel.disminuirTiempoDeVida())
				pastel = null;

		}
	}
	
	public void comprobarColision() {
		if (pastel != null) {
			if (pastel.devolverVentana().equals(Felix.getInstance().getVentanaActual())) {
				Felix.getInstance().recibirImpactoPastel();
				pastel = null;
			} else if (pastel.disminuirTiempoDeVida())
				pastel = null;
		}
	}
	
	

	/**
	 * Intenta crear un pastel en alguna de las ventanas disponibles para hacerlo
	 */
	private void generarPastel() {
		timer++;
		Ventana v;
		if (timer > tiempoSpawneo *1000/5) {
			v = obtenerVentanaAleatoria();
			if (v.puedoGenerarPastel()) {
				pastel = new Pastel(v);
				System.out.println("PASTEL GENERADO EN VENTANA [" + v.getNroFila() + "][" + v.getNroColumna() + "]");
			}
			timer=0;
		}
	}

	/**
	 * 
	 * @return una ventana aleatoria entre las 15 que se encuentran en la seccion
	 */
	private Ventana obtenerVentanaAleatoria() {
		int fila = (int) (Math.random() * 3);
		int columna = (int) (Math.random() * 5);
		return Edificio.getInstance().getSecciones()[Juego.getInstance().getNroSeccion()].getVentanas()[fila][columna];
	}

	public void avanzarSeccion() {
		pastel = null;
	}
	
	public Posicion getPosPastel() {
		int posX=0;
		int posY=0;
		if (pastel!= null) {
			posX = 224+52*pastel.devolverVentana().getNroColumna();
			posY = 130+80*pastel.devolverVentana().getNroFila();
			return (new Posicion(posX, posY));
		} else return null;
	}

	@Override
	public List<Posicion> getListaPosEntidades() {
		List<Posicion> lista = new LinkedList<Posicion>();
		lista.add(getPosPastel());
		return lista;
	}

}
