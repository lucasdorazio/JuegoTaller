package juego;

public class Dificultad {
	
	private static final int panelesRotos=15;
	private static final int velocidadLadrillos=20;
	private static final int frecuenciaGolpeo=8;
	private static final int cantObstaculos=3;
	private static final int tiempo=400;
	
	public int getPanelesRotos(int mult) {
		return panelesRotos*mult;
	}
	public int getVelocidadLadrillos(int mult) {
		return velocidadLadrillos*mult;
	}
	public int getFrecuenciaGolpeo(int mult) {
		return frecuenciaGolpeo*mult;
	}
	public int getCantObstaculos(int mult) {
		return cantObstaculos*mult;
	}
	public int getTiempo(int nivel) {
		return tiempo-10*nivel;
	}
	
	
}
