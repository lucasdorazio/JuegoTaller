package juego;

public class Seccion {
	
	private int ventanasRestantes;
	
	@SuppressWarnings("unused")
	private Ventana ventanas[][];
	
	public int getVentanasRestantes() {
		return ventanasRestantes;
	}
	public void setVentanasRestantes(int ventanasRestantes) {
		this.ventanasRestantes = ventanasRestantes;
	}
	
	public Seccion (int ventanasRestantes, Ventana[][] ventanas) {
		this.ventanasRestantes= ventanasRestantes;
		this.ventanas=ventanas;
	}
	
	
}
