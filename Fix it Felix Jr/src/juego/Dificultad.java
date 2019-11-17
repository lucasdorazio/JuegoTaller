package juego;
/**
 * Clase que almacena probabilidades y demas datos
 * que agregan dificultad al juego conforme se pasa de nivel
 * @author Lucas
 *
 */
public class Dificultad {
	
	private static final double PROB_PANEL_ROTO=0.3;
	private static final int VELOCIDAD_LADRILLOS=130;
	private static final int FRECUENCIA_GOLPEO=6;
	private static final double PROB_OBSTACULO=0.03;			
	private static final double PROB_VENTANA_ABIERTA=0.1;
	private static final int TIEMPO=400;
	
	public static double getProbPanelesRotos(int nivel) {
		return (PROB_PANEL_ROTO * ((double) nivel/10 + 1));
	}
	public static int getVelocidadLadrillos(int nivel) {
		return (int) (VELOCIDAD_LADRILLOS * ((double) nivel/10 + 1));
	}
	public static int getFrecuenciaGolpeo(int nivel) {
		return (int) (FRECUENCIA_GOLPEO + ((FRECUENCIA_GOLPEO/2)* (1 - (double) nivel/10)));
	}
	public static double getProbObstaculos(int nivel) {
		return (PROB_OBSTACULO * ((double) nivel/10 + 1));
	}
	public static double getProbVentAbierta(int nivel) {
		return (PROB_VENTANA_ABIERTA * ((double) nivel/10 + 1));
	}
	public static int getTiempo(int nivel) {
		return TIEMPO-10*nivel;
	}
	
	
}
