package entidades;

import edificio.Ventana;
import juego.Juego;

/**
 * Clase que modela los pasteles otorgados por los Nicelanders y le suman puntos al jugador al tomarlos
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Pastel implements Impactable {

	private int tiempoDeVida;
	
	private int timer;
	
	private Ventana ventanaActual;
	
	/**
	 * Inicializa atributos necesarios
	 * @param v ubicación del pastel
	 */
	public Pastel(Ventana v) {
		this.ventanaActual=v;
		this.tiempoDeVida=15;	//Un pastel se mantendrá en una ventana por 15 segundos
		this.timer=0;
	}
	
	/**
	 * @return true cuando el pastel debe ser eliminado porque no le queda más tiempo de vida
	 */
	public boolean disminuirTiempoDeVida() {
		timer++;
		if (timer>Juego.getConstTiempo()) {
			tiempoDeVida--;
			timer=0;
		}
		return (tiempoDeVida==0);
	}
	
	/**
	 * Devuelve la ventana en la que se encuentre el pastel para determinar si choca o no con Felix
	 */
	@Override
	public Ventana devolverVentana() {
		return this.ventanaActual;
	}

}
