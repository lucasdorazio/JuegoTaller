package juego;

public class Edificio {
	
	private static final int LIMITE_DERECHO_EDIFICIO = 520;
	
	private static final int LIMITE_IZQUIERDA_EDIFICIO = 180;
	
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
	
	public static int getLimiteDerechoEdificio() {
		return LIMITE_DERECHO_EDIFICIO;
	}

	public static int getLimiteIzquierdaEdificio() {
		return LIMITE_IZQUIERDA_EDIFICIO;
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
