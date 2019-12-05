package juego;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

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
			JOptionPane.showMessageDialog(null, "El archivo archivo.obj no se ha encontrado", "Error!", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarRankingNuevo(Jugador jugador) {
		int pos;
		if (dimL < 5) {
			mejoresCinco[dimL] = jugador;
			dimL++;
		} else {
			pos = 0;
			while (pos < dimL && jugador.getPuntaje() <= mejoresCinco[pos].getPuntaje())
				pos++;
			for (int i=4;i>pos;i--)
				mejoresCinco[i]= mejoresCinco[i-1];
			mejoresCinco[pos]=jugador;
		}
		escribirRankingNuevo();
	}
	
	public void escribirRanking() throws FileNotFoundException, IOException {
		ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("src/datos/archivo.obj"));
		for (int i = 0; i < 5; i++) {
			salida.writeObject(mejoresCinco[i]);
		}
		salida.close();
	}
	
	public void escribirRankingNuevo() {
		FileWriter archivo;
		PrintWriter pw;
		Jugador j;
		try {
			archivo= new FileWriter("src/datos/top_5.txt");
			pw= new PrintWriter(archivo);
			for (int i=0; i<dimL;i++) {
				j= mejoresCinco[i];
				pw.println(j.getNick() + " " + j.getPuntaje());
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void leerRanking() {
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
		if (dimL<5) return true;
		else return (puntaje > mejoresCinco[dimL-1].getPuntaje());
	}
		
}
