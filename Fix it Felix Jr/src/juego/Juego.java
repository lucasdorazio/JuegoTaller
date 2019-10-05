package juego;

import java.util.LinkedList;
import java.util.List;

public class Juego {
	private static List<Impactable> impactables= new LinkedList<Impactable>();
	
	public static void generarLadrillo (Posicion posicion) {
		impactables.add(new Ladrillo(posicion));
	}
}
