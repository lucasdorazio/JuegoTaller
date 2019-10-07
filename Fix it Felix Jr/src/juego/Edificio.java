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
		secciones[nroSeccion]=seccionesOriginales[nroSeccion];
	}
	
	public void reiniciarEdificio() {
		secciones=seccionesOriginales;
	}
	
	public void setSecciones(Seccion[] secciones) {
		this.secciones=secciones;
	}
	
	public void guardarCopiaSecciones() {
		seccionesOriginales=secciones;
	}
	
	public Seccion[] getSecciones() {
		return secciones;
	}
}
