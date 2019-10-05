package juego;

public class Dificultad {
	
	private static final double probPanelRoto=0.3;
	private static final int velocidadLadrillos=20;
	private static final int frecuenciaGolpeo=8;
	private static final double probObstaculo=0.2;
	private static final double probVentAbierta=0.1;
	private static final int tiempo=400;
	
	public static double getProbPanelesRotos(int nivel) {
		return probPanelRoto*(1+nivel/10);
	}
	public static int getVelocidadLadrillos(int nivel) {
		return velocidadLadrillos*(1+nivel/10);
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
