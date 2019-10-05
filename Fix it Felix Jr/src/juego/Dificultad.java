package juego;

public class Dificultad {
	//TERMINAR DE PASAR A MAYUSCULAS
	private static final double PROB_PANEL_ROTO=0.3;
	private static final int VELOCIDAD_LADRILLOS=20;
	private static final int frecuenciaGolpeo=8;
	private static final double probObstaculo=0.2;
	private static final double probVentAbierta=0.1;
	private static final int tiempo=400;
	
	public static double getProbPanelesRotos(int nivel) {
		return PROB_PANEL_ROTO*(1+nivel/10);
	}
	public static int getVelocidadLadrillos(int nivel) {
		return VELOCIDAD_LADRILLOS*(1+nivel/10);
	}
	public static int getFrecuenciaGolpeo(int nivel) {
		return frecuenciaGolpeo*(1+nivel/10);
	}
	public static double getProbObstaculos(int nivel) {
		return probObstaculo*(1+nivel/10);
	}
	public static double getProbVemtAbierta(int nivel) {
		return probVentAbierta*(1+nivel/10);
	}
	public static int getTiempo(int nivel) {
		return tiempo-10*nivel;
	}
	
	
}
