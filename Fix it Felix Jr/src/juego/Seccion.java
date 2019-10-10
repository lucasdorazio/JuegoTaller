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
	 * @param ventanasRestantes es el número de ventanas no sanas de esta sección
	 * @param ventanas es la matriz de ventanas ya generada, con su respectiva distribución de paneles y obstáculos
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
