package juego;

public class Edificio {
	
	private int seccionesRetantes;
	
	@SuppressWarnings("unused")
	private Seccion secciones[];
	
	private static Edificio INSTANCE;
	
	private Edificio() {
		this.seccionesRetantes=3;
	}
	
	public static Edificio getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Edificio();
		}
		return INSTANCE;
	}

	public void setSecciones(Seccion[] secciones) {
		this.secciones=secciones;
	}
	public int getSeccionesRetantes() {
		return seccionesRetantes;
	}

	public void setSeccionesRetantes(int seccionesRetantes) {
		this.seccionesRetantes = seccionesRetantes;
	}
	
	public Seccion[] getSecciones() {
		return secciones;
	}
	
	

	
}
