package juego;

import java.util.LinkedList;
import java.util.List;

public class Juego {
	
	private Nivel nivel;
	
	private Felix felix;
	
	private Edificio edificio;
	private ControladorDeRalph ralphController;
	//private ControladorDePajaros birdController;
	private int puntaje;
	private int tiempo;
	
	
	
	
	private static List<Impactable> impactables= new LinkedList<Impactable>();
	
	public static void generarLadrillo (Posicion posicion) {
		impactables.add(new Ladrillo(posicion));
	}
	
	public void iniciarNivel() {
		nivel = new Nivel(0);		//Ver como manejamos el nro de nivel en el que estamos
		edificio=nivel.generarEdificio();
		felix=Felix.getInstance();
		felix.setVentanaActual(edificio.getSecciones()[0].getVentanas()[2][2]);
		tiempo=nivel.getTiempoMax();
	}
	
	public void actualizar() {
		ralphController.manejarRalph();
		//birdController.generarPajaros();
		//birdController.actualizarPosPajaros();
		
		
	}
}
