package entidades;

import edificio.Ventana;

/**
 * Interface que implementaran las clases capaces de impactar/interactuar contra
 * el jugador
 * @author Lucas
 *
 */
public interface Impactable {	

	/**
	 * 
	 * @return ventana en la cual se encuentra actualmente el Impactable
	 */
	public Ventana devolverVentana();
}
