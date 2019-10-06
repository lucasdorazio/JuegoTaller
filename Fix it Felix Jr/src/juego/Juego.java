package juego;

public class Juego {
	
	private Nivel nivel;
	
	private static final int LIMITE_DERECHO_EDIFICIO = 500;
	
	private static final int LIMITE_IZQUIERDA_EDIFICIO = 100;
	
	private static final int LIMITE_DERECHO_MAPA=1000; //Revisar valor
	
	private static final int LIMITE_IZQUIERDO_MAPA=0;
	
	private static final double CONST_TIEMPO = 10000;

	private ControladorDeRalph ralphController;
	
	private ControladorDePajaro birdController;
	
	private ControladorDeLadrillos brickController;
	
	private static int puntaje;
	
	private int tiempo;
	
	private int timerPastel;
	
	private static Pastel pastel;
	
	private int tiempoGeneracionPastel;
	
	private static int nroNivel;
	
	private static int nroSeccion;
	
	private static boolean siguienteSeccion;
	
	private static boolean siguienteNivel;
	
	private Seccion[] seccionesOriginales;
	
	private Colisiones colisiones;
	
	
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
	
	public static Pastel getPastel() {
		return pastel;
	}
	
	public Juego() {
		
	}
	
	public void iniciarNivel() {
		nroSeccion=0;
		nivel = new Nivel(nroNivel);		
		ralphController = new ControladorDeRalph(Dificultad.getFrecuenciaGolpeo(nroNivel));
		brickController = new ControladorDeLadrillos(Dificultad.getVelocidadLadrillos(nroNivel));
		birdController = new ControladorDePajaro();
		nivel.generarEdificio();
		seccionesOriginales=Edificio.getInstance().getSecciones().clone();
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[0]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[0].getVentanas()[2][2]);
		tiempo=nivel.getTiempoMax();
	}
	
	public void avanzarSeccion() {
		nroSeccion++;
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[nroSeccion]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[nroSeccion].getVentanas()[2][Felix.getInstance().getVentanaActual().getNroColumna()]);
		siguienteSeccion=false;
	}
	
	public void avanzarNivel() {
		
		nroNivel++;
		iniciarNivel();
		siguienteSeccion=false;
		System.out.println("Victoria!! Avanzas al nivel " + (nroNivel+1));//nroNivel va de 0 a 9
	}
	
	public static void ganar() {
		System.out.println("Ganaste, congratuleishon, tu punteaje fue:"+ puntaje);
	}
	
	public void perder() {
		System.out.println("Perdiste, tu puntaje fue: "+ puntaje);
	}
	 
	public void actualizar() {
		tiempo-=1/CONST_TIEMPO;
		if (siguienteNivel) avanzarNivel();
		if (siguienteSeccion) avanzarSeccion();
		colisiones.comprobarColisiones();
		ralphController.manejarRalph();
		brickController.actualizarLadrillos();
		birdController.generarPajaros();
		birdController.actualizarPosPajaros();
		if (pastel==null) this.generarPastel();
		else if (pastel.disminuirTiempoDeVida()) pastel=null;
	}
	
	public void jugar() {
		while (tiempo>0 && Felix.getInstance().getVidas()>0) {
			actualizar();
		}
	}
	
	public void generarPastel() {
		timerPastel++;
		Ventana v;
		if (timerPastel > tiempoGeneracionPastel * CONST_TIEMPO) {
			v=this.obtenerVentanaAleatoria();
			if (v.puedoGenerarPastel()) pastel = new Pastel(v);
		}
	}
	
	private Ventana obtenerVentanaAleatoria() {
		int fila= (int) Math.random()*3;
		int columna= (int) Math.random()*5;
		return Edificio.getInstance().getSecciones()[nroSeccion].getVentanas()[fila][columna];
	}
	
	public static void comprobarSeccionLimpia(Seccion seccion){
		if (seccion.getVentanasRestantes()==0) {
			if (nroSeccion == 2) {
				if (nroNivel ==9) {
					ganar();
				} else {
					siguienteNivel=true;
				}
			} else {
				siguienteSeccion=true;
			}
		}
	}
	
	public void pajaroGolpeaFelix(){
		Edificio.getInstance().reiniciarSeccion(seccionesOriginales.clone(), nroSeccion);
		if (Felix.getInstance().getVidas() == 0) {
			perder();
		}
	}
	/*las seccionesOriginales no van a modificarse al enviar la copia. De esta manera se soporta
	 * que la seccion pueda reiniciarse mas de una vez. 
	*/
	public void ladrilloGolpeaFelix(){
		Edificio.getInstance().setSecciones(seccionesOriginales.clone());
		if (Felix.getInstance().getVidas() == 0) {
			perder();
		}
	}
	
	
}
