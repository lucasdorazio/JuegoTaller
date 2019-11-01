package controladores;

import edificio.Edificio;
import edificio.Ventana;
import entidades.Pastel;
import juego.Juego;

public class ControladorDePastel extends Controlador {
	
	private int timer;
	
	private int tiempoSpawneo;
	
	private Pastel pastel;
	
	public ControladorDePastel() {
		this.timer=0;
		this.tiempoSpawneo=5;
	}
	
	public void actualizar() {
		if (pastel== null)
			this.generarPastel();
		else if (pastel.disminuirTiempoDeVida())
			pastel = null;
	}
	
	/**
	 * Intenta crear un pastel en alguna de las ventanas disponibles para hacerlo
	 */
	private void generarPastel() {
		timer++;
		Ventana v;
		if (timer> tiempoSpawneo * Juego.getConstTiempo()) {
		v=obtenerVentanaAleatoria();
			if (v.puedoGenerarPastel()) {
				pastel = new Pastel(v);
				System.out.println("PASTEL GENERADO EN VENTANA ["+v.getNroFila()+"]["+v.getNroColumna()+"]");
			}
		}
	}
	
	/**
	 * 
	 * @return una ventana aleatoria entre las 15 que se encuentran en la seccion
	 */
	private Ventana obtenerVentanaAleatoria() {
		int fila= (int) (Math.random()*3);
		int columna= (int) (Math.random()*5);
		return Edificio.getInstance().getSecciones()[Juego.getNroSeccion()].getVentanas()[fila][columna];
	}
	
	public void avanzarSeccion() {
		pastel=null;
	}
	

}
