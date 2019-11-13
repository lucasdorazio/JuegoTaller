package juego;
/**
 * Clase que almacena probabilidades y demas datos
 * que agregan dificultad al juego conforme se pasa de nivel
 * @author Lucas
 *
 */
public class Dificultad {
	
	private static final double PROB_PANEL_ROTO=0.3;
	private static final int VELOCIDAD_LADRILLOS=110;
	private static final int FRECUENCIA_GOLPEO=8;
	private static final double PROB_OBSTACULO=0.03;			
	private static final double PROB_VENTANA_ABIERTA=0.1;
	private static final int TIEMPO=400;
	
	public static double getProbPanelesRotos(int nivel) {
		return PROB_PANEL_ROTO*(1+nivel/10);
	}
	public static int getVelocidadLadrillos(int nivel) {
		return VELOCIDAD_LADRILLOS*(1+nivel/10);
	}
	public static int getFrecuenciaGolpeo(int nivel) {
		return FRECUENCIA_GOLPEO*(1-nivel/10);
	}
	public static double getProbObstaculos(int nivel) {
		return PROB_OBSTACULO*(1+nivel/10);
	}
	public static double getProbVentAbierta(int nivel) {
		return PROB_VENTANA_ABIERTA*(1+nivel/10);
	}
	public static int getTiempo(int nivel) {
		return TIEMPO-10*nivel;
	}
	
	
}
