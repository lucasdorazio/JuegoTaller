package juego;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
	private int dimL;
	
	public Ranking() {
		 mejoresCinco = new Jugador[5];
		 dimL=0;
	}
	
	public int getCantJugadores() {
		return dimL;
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
	
	public void leerRankingNuevo() {
		BufferedReader br;
		String linea, nombre;
		int puntaje, espacio;
		try {
			br= new BufferedReader(new FileReader("src/datos/top_5.txt"));
			linea= br.readLine();
			while (linea != null) {
				espacio= linea.indexOf(" ");
				nombre= linea.substring(0, espacio);
				puntaje= Integer.parseInt(linea.substring(espacio+1, linea.length()));
				mejoresCinco[dimL]= new Jugador(nombre, puntaje);
				dimL++;
				linea= br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Jugador[] getMejoresCinco() {
		if (mejoresCinco== null) cargarMejoresJugadores();
		return mejoresCinco;
	}
	// para probar stream
	public void cargarMejoresJugadores() {
		mejoresCinco= new Jugador[5];
		dimL=5;
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
