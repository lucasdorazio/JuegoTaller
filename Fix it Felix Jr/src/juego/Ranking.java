package juego;

/**
 * Clase que modela el almacenamiento y posición de los jugadores con mejores puntajes
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Ranking{
	
	private Jugador[] mejoresDiez;
	
	public Ranking() {
		 
	}
	
	/**
	 * Introduce (o no) a un jugador con un determinado puntaje en el ranking
	 * @param jugador
	 */
	public void actualizarRanking(Jugador jugador) {
		int pos=0;
		while (pos < 10 && jugador.getPuntaje() <= mejoresDiez[pos].getPuntaje()) {
			pos++;
		}
		if (pos < 10) {
			for (int j = 10; j > pos; j--) {
				mejoresDiez[j] = mejoresDiez[j - 1];
			}
			mejoresDiez[pos] = jugador;
		}
	}	
}
