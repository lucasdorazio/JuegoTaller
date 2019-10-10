package juego;

public class Ranking{
	
	private Jugador[] mejoresDiez;
	//cargamos con 10 jugadores en 00
	public Ranking() {
		 
	}
	
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
		}//ahre
	}

	//Hola Lucas
	

	
}
