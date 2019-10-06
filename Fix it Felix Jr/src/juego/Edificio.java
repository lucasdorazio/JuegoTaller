package juego;

public class Edificio {
	
	private Seccion secciones[];
	
	private static Edificio INSTANCE;
	
	private Edificio() {
	}
	
	public static Edificio getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Edificio();
		}
		return INSTANCE;
	}
	
	public void reiniciarSeccion(Seccion[] secciones, int nroSeccion) {
		this.secciones[nroSeccion]=secciones[nroSeccion];
	}
	
	public void setSecciones(Seccion[] secciones) {
		this.secciones=secciones;
	}
	
	public Seccion[] getSecciones() {
		return secciones;
	}
}
