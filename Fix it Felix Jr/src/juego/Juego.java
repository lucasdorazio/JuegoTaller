package juego;

import java.util.Scanner;

public class Juego {
	
	private Nivel nivel;
	
	private static final int LIMITE_DERECHO_MAPA=700;
	
	private static final int LIMITE_IZQUIERDO_MAPA=0;
	
	private static final double CONST_TIEMPO = 10000;
	
	private static Jugador jugador;
	
	private int puntajePrevio;

	private static boolean pasarDeSeccion;
	
	private static boolean pasarDeNivel;
	
	private static boolean reinicioNivel;
	
	private static boolean reinicioSeccion;
	
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
		Scanner teclado= new Scanner(System.in);
		System.out.println("Ingrese su nickname");
		jugador= new Jugador(teclado.next());
		jugador.setPuntaje(0);
		puntajePrevio=0;
		nroNivel=0;
		this.iniciarNivel(false);
		pasarDeNivel=false;
		pasarDeSeccion=false;
		reinicioNivel=false;
		reinicioSeccion=false;
		colisiones= new Colisiones();
		timerPastel=0;
		tiempoGeneracionPastel=5;
		teclado.close();
	}
	
	public void iniciarNivel(boolean reinicio) {
		nroSeccion=0;
		nivel = new Nivel(nroNivel);
		if (reinicio) {
			Edificio.getInstance().reiniciarEdificio();
			this.eliminarEntidades();
			jugador.setPuntaje(puntajePrevio);
		} else {
			nivel.generarEdificio();
			puntajePrevio=jugador.getPuntaje();
		}
		ralphController = new ControladorDeRalph(Dificultad.getFrecuenciaGolpeo(nroNivel));
		brickController = new ControladorDeLadrillos(Dificultad.getVelocidadLadrillos(nroNivel));
		birdController = new ControladorDePajaro();
		pastel=null;	
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[0]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[0].getVentanas()[2][2]);
		tiempo=nivel.getTiempoMax();
	}
	
	public void reiniciarSeccion() {
		this.eliminarEntidades();
		Edificio.getInstance().reiniciarSeccion(nroSeccion);
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[nroSeccion]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[nroSeccion].getVentanas()[2][2]);
	}
	
	public void actualizar() {
		if (reinicioNivel) iniciarNivel(true);
		else if (reinicioSeccion) reiniciarSeccion();
		else {
			tiempo -= 1 / CONST_TIEMPO;
			if (pasarDeNivel)
				avanzarNivel();
			else if (pasarDeSeccion)
				avanzarSeccion();
			else {
				colisiones.comprobarColisiones();
				ralphController.manejarRalph();
				brickController.actualizarLadrillos();
				birdController.generarPajaros();
				birdController.actualizarPosPajaros();
				Felix.getInstance().actualizarInvulnerabilidad();
				if (pastel == null)
					this.generarPastel();
				else if (pastel.disminuirTiempoDeVida())
					pastel = null;
			}
		}
	}
	
	public boolean perdio() {
		return (Felix.getInstance().getVidas()==0 || tiempo<=0);
	}
	
	
	
	public void avanzarSeccion() {
		this.eliminarEntidades();
		nroSeccion++;
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[nroSeccion]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[nroSeccion].getVentanas()[2][Felix.getInstance().getVentanaActual().getNroColumna()]);
		pasarDeSeccion=false;
	}
	
	public void avanzarNivel() {
		this.eliminarEntidades();
		nroNivel++;
		iniciarNivel(false);
		pasarDeSeccion=false;
		pasarDeNivel=false;
		System.out.println("Victoria!! Avanzas al nivel " + (nroNivel+1));//nroNivel va de 0 a 9
	}
	
	public static int getNroSeccion() {
		return nroSeccion;
	}
	
	public static void ganar() {
		System.out.println("Ganaste, congratuleishon, tu punteaje fue:"+ jugador.getPuntaje());
	}
	
	public static void perder() {
		System.out.println("Perdiste, tu puntaje fue: "+ jugador.getPuntaje());
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
	
	public void eliminarEntidades() {
		birdController.eliminarPajaros();
		brickController.eliminarLadrillos();
		pastel=null;
	}

	public static void ladrilloGolpeoAFelix() {
		reinicioNivel=true;
		System.out.println("Te ha golpeado un ladrillo y se reiniciara el nivel");
	}

	public static void pajaroGolpeoAFelix() {
		reinicioSeccion=true;
		System.out.println("Te ha golpeado un pajaro y se reiniciara la seccion");
	}

	public static Jugador getJugador() {
		return jugador;
	}
	
}
