package juego;

public class Edificio {
	
	private Seccion secciones[];
	private Seccion seccionesOriginales[];
	
	private static Edificio INSTANCE;
	
	private Edificio() {
	}
	
	public static Edificio getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Edificio();
		}
		return INSTANCE;
	}
	
	public void reiniciarSeccion(int nroSeccion) {
		secciones[nroSeccion]=seccionesOriginales.clone()[nroSeccion];
	}
	
	public void reiniciarEdificio() {
		secciones=seccionesOriginales.clone();
	}
	
	public void setSecciones(Seccion[] secciones) {
		this.secciones=secciones;
	}
	
	public void guardarCopiaSecciones() {
		seccionesOriginales= secciones.clone();
	}
	
	public Seccion[] getSecciones() {
		return secciones;
	}
}
