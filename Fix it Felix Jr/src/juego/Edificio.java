package juego;

public class Edificio {
	
	private int seccionesRetantes;
	private Seccion secciones[] = new Seccion[3];
	
	public Edificio(int seccionesRestantes, Seccion[] secciones) {
		this.seccionesRetantes=seccionesRestantes;
		this.secciones=secciones;
	}
	
	public Edificio() {
		
	}
	
}
