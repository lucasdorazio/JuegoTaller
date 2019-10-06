package juego;

import java.util.LinkedList;
import java.util.List;

public class Juego {
	
	private Nivel nivel;
	
	private static final int LIMITE_DERECHO_EDIFICIO = 500;
	
	private static final int LIMITE_IZQUIERDA_EDIFICIO = 100;
	
	private static final int LIMITE_DERECHO_MAPA=1000; //Revisar valor
	
	private static final int LIMITE_IZQUIERDO_MAPA=0;

	private ControladorDeRalph ralphController;
	
	private ControladorDePajaro birdController;
	
	private ControladorDeLadrillos brickController;
	
	private int puntaje;
	
	private int tiempo;
	
	private static int nroNivel;
	
	private static int nroSeccion;
	
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

	//private static List<Impactable> impactables= new LinkedList<Impactable>();
	
	public Juego() {
		
	}
	
	public void iniciarNivel() {
		nivel = new Nivel(nroNivel);		
		ralphController = new ControladorDeRalph(Dificultad.getFrecuenciaGolpeo(nroNivel));
		brickController = new ControladorDeLadrillos(Dificultad.getVelocidadLadrillos(nroNivel));
		birdController = new ControladorDePajaro();
		nivel.generarEdificio();
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[0]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[0].getVentanas()[2][2]);
		tiempo=nivel.getTiempoMax();
	}
	
	public static void avanzarSeccion() {
		Edificio.getInstance().setSeccionesRetantes(Edificio.getInstance().getSeccionesRetantes()-1);
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[]);
		
	}
	
	public static void avanzarNivel() {
		
	}
	
	public static void ganar() {
		System.out.println("Ganaste, congratuleishon");
	}
	
	public void actualizar() {
		ralphController.manejarRalph();
		brickController.actualizarLadrillos();
		birdController.generarPajaros();
		birdController.actualizarPosPajaros();	
	}
	
	public static void comprobarSeccionLimpia(Seccion seccion){
		if (seccion.getVentanasRestantes()==0) {
			if (Edificio.getInstance().getSeccionesRetantes()== 1) {
				if (nroNivel ==9) {
					ganar();
				} else {
					avanzarNivel();
				}
			} else {
				avanzarSeccion();
			}
		}/* sacar secciones restantes a edificios, agregar a seccion el nro de seccion actual
		terminar los avanzares... mover de felix... seguir con el actualizar () crear while
		
		*/
	}
}
