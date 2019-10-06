package juego;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ControladorDeLadrillos {
	
	private static List<Ladrillo> listaLadrillos= new LinkedList<Ladrillo>();
	private int timer=0;
	private int velocidad; 
	
	public static void generarLadrillo(Posicion pos) {
		listaLadrillos.add(new Ladrillo(pos));
	}
	
	public void actualizarLadrillos() {
		
		Ladrillo ladrillo;
		Iterator<Ladrillo> ite= listaLadrillos.iterator();
		while (ite.hasNext()) {
			ladrillo=ite.next();
			if (ladrillo.bajar()) ite.remove();
		}
	}

}
