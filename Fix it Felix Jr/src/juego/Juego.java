package juego;

import java.util.LinkedList;
import java.util.List;

public class Juego {
	
	private Nivel nivel;
	
	private Felix felix;
	
	private static final int LIMITE_DERECHO_EDIFICIO = 500;
	
	private static final int LIMITE_IZQUIERDA_EDIFICIO = 100;
	
	private static final int LIMITE_DERECHO_MAPA=1000; //Revisar valor
	
	private static final int LIMITE_IZQUIERDO_MAPA=0;
	
	private Edificio edificio;
	
	private ControladorDeRalph ralphController;
	
	//private ControladorDePajaros birdController;
	
	private ControladorDeLadrillos brickController;
	
	private int puntaje;
	
	private int tiempo;
	
	private int nroNivel;
	
	public static int getLimiteDerechoEdificio() {
		return LIMITE_DERECHO_EDIFICIO;
	}

	public static int getLimiteIzquierdaEdificio() {
		return LIMITE_IZQUIERDA_EDIFICIO;
	}

	public static int getLimiteDerechoMapa() {
		return LIMITE_DERECHO_MAPA;
	}

	public static int getLimiteIzquierdoMapa() {
		return LIMITE_IZQUIERDO_MAPA;
	}

	private static List<Impactable> impactables= new LinkedList<Impactable>();
	
	
	public void iniciarNivel() {
		nivel = new Nivel(this.nroNivel);		//Ver como manejamos el nro de nivel en el que estamos
		nivel.generarEdificio();
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[0]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[0].getVentanas()[2][2]);
		tiempo=nivel.getTiempoMax();
	}
	
	public void actualizar() {
		ralphController.manejarRalph();
		brickController.actualizarLadrillos();
		//birdController.generarPajaros();
		//birdController.actualizarPosPajaros();
		
		
	}
}
