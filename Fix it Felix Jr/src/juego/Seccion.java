package juego;

public class Seccion {
	
	private int ventanasRestantes;
	
	private Ventana ventanas[][];
	
	public int getVentanasRestantes() {
		return ventanasRestantes;
	}
	public void setVentanasRestantes(int ventanasRestantes) {
		this.ventanasRestantes = ventanasRestantes;
	}
	
	public Seccion (int ventanasRestantes, Ventana[][] ventanas) {
		this.ventanasRestantes= ventanasRestantes;
		this.setVentanas(ventanas);
	}
	public Ventana[][] getVentanas() {
		return ventanas;
	}
	public void setVentanas(Ventana ventanas[][]) {
		this.ventanas = ventanas;
	}
	
	
}
