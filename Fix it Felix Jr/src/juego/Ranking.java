package juego;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Clase que modela el almacenamiento y posición de los jugadores con mejores puntajes
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Ranking{
	
	private Jugador[] mejoresCinco;
	
	public Ranking() {
		 
	}
	
	/**
	 * Introduce (o no) a un jugador con un determinado puntaje en el ranking
	 * @param jugador
	 */
	public void actualizarRanking(Jugador jugador) {
		int pos=0;
		while (pos < 5 && jugador.getPuntaje() <= mejoresCinco[pos].getPuntaje()) {
			pos++;
		}
		if (pos < 5) {
			for (int j = 5; j > pos; j--) {
				mejoresCinco[j] = mejoresCinco[j - 1];
			}
			mejoresCinco[pos] = jugador;
		}
	}
	
	public void escribirRanking() throws FileNotFoundException, IOException {
		ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("datos/ranking.TXT"));
		for (int i = 0; i < 5; i++) {
			salida.writeObject(mejoresCinco[i]);
		}
		salida.close();
	}
	
	public void leerRanking() {
		Object[] jugadores = new Object[5];
		ObjectInputStream entrada = null;
		try {
			entrada = new ObjectInputStream(new FileInputStream("datos/ranking.TXT"));
		} catch (IOException e) {
			System.out.println("Archivo ranking.TXT no encontrado");
			e.printStackTrace();
		} 
		for (int i = 0; i < 5; i++) {
			try {
				jugadores[i]=entrada.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("De alguna forma, la clase no fue encontrada (preguntar)");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Archivo ranking.TXT no encontrado");
				e.printStackTrace();
			} 
		}
		mejoresCinco=(Jugador[]) jugadores;
	}
	
	public Jugador[] getmejoresDiez() {
		return mejoresCinco;
	}
	
}
