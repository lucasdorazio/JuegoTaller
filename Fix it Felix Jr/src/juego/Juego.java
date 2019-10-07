package juego;

public class Juego {
	
	private Nivel nivel;
	
	private static final int LIMITE_DERECHO_EDIFICIO = 500;
	
	private static final int LIMITE_IZQUIERDA_EDIFICIO = 100;
	
	private static final int LIMITE_DERECHO_MAPA=1000; //Revisar valor
	
	private static final int LIMITE_IZQUIERDO_MAPA=0;
	
	private static final double CONST_TIEMPO = 10000;

	private static int puntaje;

	private static boolean pasarDeSeccion;
	
	private static boolean pasarDeNivel;
	
	private static boolean reinicioNivel;
	
	private static int nroNivel;
	
	private static int nroSeccion;
	
	private static Pastel pastel;

	private ControladorDeRalph ralphController;
	
	private ControladorDePajaro birdController;
	
	private ControladorDeLadrillos brickController;
	
	private Colisiones colisiones;
	
	private double tiempo;
	
	private int timerPastel;
	
	private int tiempoGeneracionPastel;

	
	
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
		//Inicializar variables necesarias
	}
	
	public void iniciarNivel() {
		nroSeccion=0;
		nivel = new Nivel(nroNivel);		
		ralphController = new ControladorDeRalph(Dificultad.getFrecuenciaGolpeo(nroNivel));
		brickController = new ControladorDeLadrillos(Dificultad.getVelocidadLadrillos(nroNivel));
		birdController = new ControladorDePajaro();
		pastel=null;
		nivel.generarEdificio();
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[0]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[0].getVentanas()[2][2]);
		tiempo=nivel.getTiempoMax();
	}
	
	public void reiniciarNivel() {
		nroSeccion=0;
		Edificio.getInstance().reiniciarEdificio();
		ralphController = new ControladorDeRalph(Dificultad.getFrecuenciaGolpeo(nroNivel));
		brickController = new ControladorDeLadrillos(Dificultad.getVelocidadLadrillos(nroNivel));
		birdController = new ControladorDePajaro();
		pastel=null;
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[0]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[0].getVentanas()[2][2]);
		//tiempo=nivel.getTiempoMax();  Si reiniciamos el nivel también se reinicia el tiempo?
	}
	
	public void actualizar() {
		if (reinicioNivel) reiniciarNivel();
		else {
			tiempo-=1/CONST_TIEMPO;
			if (pasarDeNivel) avanzarNivel();
			else if (pasarDeSeccion) avanzarSeccion();
			else {
				colisiones.comprobarColisiones();
				ralphController.manejarRalph();
				brickController.actualizarLadrillos();
				birdController.generarPajaros();
				birdController.actualizarPosPajaros();
				Felix.getInstance().actualizarInvulnerabilidad();
				if (pastel==null) this.generarPastel();
				else if (pastel.disminuirTiempoDeVida()) pastel=null;
			}
		}
		
	}
	
	public void avanzarSeccion() {
		nroSeccion++;
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[nroSeccion]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[nroSeccion].getVentanas()[2][Felix.getInstance().getVentanaActual().getNroColumna()]);
		pasarDeSeccion=false;
	}
	
	public void avanzarNivel() {
		
		nroNivel++;
		iniciarNivel();
		pasarDeSeccion=false;
		pasarDeNivel=false;
		System.out.println("Victoria!! Avanzas al nivel " + (nroNivel+1));//nroNivel va de 0 a 9
	}
	
	public static int getNroSeccion() {
		return nroSeccion;
	}
	
	public static void ganar() {
		System.out.println("Ganaste, congratuleishon, tu punteaje fue:"+ puntaje);
	}
	
	public static void perder() {
		System.out.println("Perdiste, tu puntaje fue: "+ puntaje);
	}
	
	public void jugar() {
		while (tiempo>0 && Felix.getInstance().getVidas()>0) {	//No conviene preguntar por las vidas
			actualizar();
		}
	}
	
	private void generarPastel() {
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
					pasarDeNivel=true;
				}
			} else {
				pasarDeSeccion=true;
			}
		}
	}

	public static void ladrilloGolpeoAFelix() {
		reinicioNivel=true;
		System.out.println("Te ha golpeado un ladrillo y se reiniciara el nivel");
	}

	public static void pajaroGolpeoAFelix() {
		Edificio.getInstance().reiniciarSeccion(nroSeccion);
		pastel=null;
		/*Se justifica un metodo reiniciar seccion?
		Para eliminar todas las entidades que haya en esa sección*/
	}

	public static void sumarPuntaje(int puntos) {
		puntaje+=puntos;
	}
	
}
