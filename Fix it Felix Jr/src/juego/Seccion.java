package juego;

/**
 * Clase que modela el conjunto de ventanas que Felix debe reparar para avanzar a la siguiente seccion del edificio
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Seccion {
	
	private int ventanasRestantes;
	
	private Ventana ventanas[][];
	
	public int getVentanasRestantes() {
		return ventanasRestantes;
	}
	public void setVentanasRestantes(int ventanasRestantes) {
		this.ventanasRestantes = ventanasRestantes;
	}
	
	/**
	 * @param ventanasRestantes es el n�mero de ventanas no sanas de esta secci�n
	 * @param ventanas es la matriz de ventanas ya generada, con su respectiva distribuci�n de paneles y obst�culos
	 */
	public Seccion (int ventanasRestantes, Ventana[][] ventanas) {
		this.ventanasRestantes= ventanasRestantes;
		this.ventanas=ventanas;
	}
	
	public Ventana[][] getVentanas() {
		return ventanas;
	}
	public void setVentanas(Ventana ventanas[][]) {
		this.ventanas = ventanas;
	}
	
	public void disminuirVentanasRestantes() {
		ventanasRestantes--;
	}
	
}
