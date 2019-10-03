package juego;

public class Dificultad {
	
	private static final int panelesRotos=15;
	private static final int velocidadLadrillos=20;
	private static final int frecuenciaGolpeo=8;
	private static final int cantObstaculos=3;
	private static final int tiempo=400;
	
	public static int getPanelesRotos(int mult) {
		return panelesRotos*mult;
	}
	public static int getVelocidadLadrillos(int mult) {
		return velocidadLadrillos*mult;
	}
	public static int getFrecuenciaGolpeo(int mult) {
		return frecuenciaGolpeo*mult;
	}
	public static int getCantObstaculos(int mult) {
		return cantObstaculos*mult;
	}
	public static int getTiempo(int nivel) {
		return tiempo-10*nivel;
	}
	
	
}
