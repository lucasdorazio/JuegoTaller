package juego;

public class Seccion {
	
	private int ventanasRestantes;
	private Ventana ventanas[][] = new Ventana[3][5];
	
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
