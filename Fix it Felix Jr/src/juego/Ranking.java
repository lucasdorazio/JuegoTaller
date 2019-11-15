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
	
	private static Jugador[] mejoresCinco;
	
	public Ranking() {
		 mejoresCinco = new Jugador[5];
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
			for (int j = 4; j > pos; j--) {
				mejoresCinco[j] = mejoresCinco[j - 1];
			}
			mejoresCinco[pos] = jugador;
		}
		try {
			escribirRanking();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("El archivo archivo.obj no se ha encontrado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void escribirRanking() throws FileNotFoundException, IOException {
		ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("src/datos/archivo.obj"));
		for (int i = 0; i < 5; i++) {
			salida.writeObject(mejoresCinco[i]);
		}
		salida.close();
	}
	
	public void leerRanking() {
		//Object[] jugadores = new Object[5];
		//tira error invalid stream header
		ObjectInputStream entrada = null;
		try {
			entrada = new ObjectInputStream(new FileInputStream("src/datos/archivo.obj"));
		} catch (IOException e) {
			System.out.println("Archivo ranking.TXT no encontrado vez 1");
			e.printStackTrace();
		} 
		for (int i = 0; i < 5; i++) {
			try {
				mejoresCinco[i]=(Jugador) entrada.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("De alguna forma, la clase no fue encontrada (preguntar)");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Archivo ranking.TXT no encontrado vez 2");
				e.printStackTrace();
			} 
		}
	}
	
	public static Jugador[] getMejoresCinco() {
		if (mejoresCinco== null) cargarMejoresJugadores();
		return mejoresCinco;
	}
	// para probar stream
	public static void cargarMejoresJugadores() {
		mejoresCinco= new Jugador[5];
		mejoresCinco[0]= new Jugador("Manuel", 500);
		mejoresCinco[1]= new Jugador("juan", 400);
		mejoresCinco[2]= new Jugador("ricky", 300);
		mejoresCinco[3]= new Jugador("sofia", 200);
		mejoresCinco[4]= new Jugador("ernesto", 100);
		
		}

	public boolean estaEntreLosMejoresCinco(int puntaje) {
		return (puntaje > mejoresCinco[4].getPuntaje());
	}
		
}
