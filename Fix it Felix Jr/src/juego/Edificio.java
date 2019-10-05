package juego;

public class Edificio {
	
	private int seccionesRetantes;
	
	@SuppressWarnings("unused")
	private Seccion secciones[];
	
	public Edificio(Seccion[] secciones) {
		this.secciones=secciones;
		seccionesRetantes=3;
	}

	public int getSeccionesRetantes() {
		return seccionesRetantes;
	}

	public void setSeccionesRetantes(int seccionesRetantes) {
		this.seccionesRetantes = seccionesRetantes;
	}

	
}
