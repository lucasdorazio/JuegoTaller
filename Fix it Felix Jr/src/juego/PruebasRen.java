package juego;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;

import excepciones.ImproperNameException;
import grafica.TopJugadores;

public class PruebasRen {

	public static void main(String[] args) {
//		boolean nombreCorrecto=false;
//		while (!nombreCorrecto) {
//			try {
//				Juego.getInstance().pedirNombre();
//				nombreCorrecto = true;
//			} catch (ImproperNameException e) {
//				System.out.println("ERROR: " + e.toString());
//			}
//		}
//		System.out.println("Salio del while");
//		System.out.println("Salio del while x2");
		
//		TopJugadores ranking = new TopJugadores();
//		ranking.setVisible(true);
//		ranking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			Jugador[] ranking= new Jugador[5];
			int dimL=0;
			
			BufferedReader br= new BufferedReader(new FileReader("dondeEstoy.txt"));
			
			String s=br.readLine();
			String nombre;
			int puntaje,espacio;
			Jugador j;
			while (s!=null) {
				espacio= s.indexOf(" ");
				nombre=s.substring(0, espacio);
				puntaje= Integer.parseInt(s.substring(espacio+1, s.length()));
				System.out.println(nombre + puntaje);
				j= new Jugador(nombre, puntaje);
				ranking[dimL]=j;
				dimL++;
				s=br.readLine();
			}
			br.close();
			System.out.println("salio");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
@SuppressWarnings("serial")
class Marco extends JFrame {
	public Marco() {
		setBounds(100, 100, 200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

/*
private void paintPastel(Graphics g) {
Posicion pos = Juego.getInstance().getPosPastel();
if ( pos != null) {
	g.drawImage(pastel, pos.getPosX(), pos.getPosY(), pastel.getWidth(null), pastel.getHeight(null), null);
}
}

public Posicion getPosPastel() {
return controladores[3].getListaPosEntidades().get(0);
}


@Override
public List<Posicion> getListaPosEntidades() {
	List<Posicion> lista = new LinkedList<Posicion>();
	lista.add(getPosPastel());
	return lista;
}

*/

//definir archivo, pw y j
/*		try {
			archivo= new FileWriter("dondeEstoy.txt",false);
			pw= new PrintWriter(archivo);
			for (int i=0;i<3;i++) {
				j= new Jugador("Nombre", (i+1)*50);
				pw.println(j.getNick()+ " "+ j.getPuntaje());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
