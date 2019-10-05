package juego;

import java.util.LinkedList;
import java.util.List;

public class Juego {
	
	private Nivel nivel;
	
	private Felix felix;
	
	private Edificio edificio;
	
	
	
	private static List<Impactable> impactables= new LinkedList<Impactable>();
	
	public static void generarLadrillo (Posicion posicion) {
		impactables.add(new Ladrillo(posicion));
	}
	
	public void iniciarNivel() {
		nivel.setNroNivel(0);
		edificio=nivel.generarEdificio();
		felix=Felix.getInstance();
		felix.setVentanaActual(edificio.getSecciones()[0].getVentanas()[2][2]);
	}
}
