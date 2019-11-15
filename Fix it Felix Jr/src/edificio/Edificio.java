package edificio;

import juego.Nivel;

/**
 * Modela al conjunto de secciones y las administra
 * @author Lucas y Renzo
 * @version 1.1
 */
public class Edificio {
	
	private static final int LIMITE_DERECHO_EDIFICIO = 424;
	
	private static final int LIMITE_IZQUIERDA_EDIFICIO = 180;
	
	private Seccion secciones[];
	
	private Seccion seccionesOriginales[];
	
	private static Edificio INSTANCE;
	
	private Edificio() {
	}
	/**
	 * 
	 * @return Instancia de Edificio
	 * Patron Singleton
	 */
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
	/**
	 * Reinicia la seccion actual en la que transcurre el juego a su 
	 * distribucion original
	 * @param nroSeccion recibe el numero de la seccion correspondiente 
	 * que se quiere reiniciar del edificio
	 */
	public void reiniciarSeccion(int nroSeccion) {
		secciones[nroSeccion]=seccionesOriginales.clone()[nroSeccion];
		//Nivel.generarEdificio();
	}
	/**
	 * Reinicia el edificio a su distribucion original
	 */
	public void reiniciarEdificio() {
		secciones=seccionesOriginales.clone();
	}
	
	public void setSecciones(Seccion[] secciones) {
		this.secciones=secciones;
	}
	/**
	 * Almacena la distribucion original con la que se generó el edificio
	 */
	public void guardarCopiaSecciones() {
		seccionesOriginales= secciones.clone();
	}
	
	public Seccion[] getSecciones() {
		return secciones;
	}
}
