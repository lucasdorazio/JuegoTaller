package juego;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import controladores.Controlador;
import controladores.ControladorDeLadrillos;
import controladores.ControladorDePajaro;
import controladores.ControladorDePastel;
import controladores.ControladorDeRalph;
import edificio.Edificio;
import edificio.Seccion;
import entidades.Felix;
import entidades.Ladrillo;
import entidades.Pajaro;
/**
 * Clase que conecta todas las componentes del juego, ya sea conociendolas
 * o usandolas como atributos propios.
 * Su metodo principal será el actualizar()
 * @author Lucas y Renzo
 *
 */
import entidades.Posicion;
public class Juego implements Runnable{
	
	private static Juego INSTANCE;
	
	private Nivel nivel;
	
	public static final int LIMITE_DERECHO_MAPA=675;
	
	public static final int LIMITE_IZQUIERDO_MAPA=0;
	
	public static final int LIMITE_INFERIOR_MAPA=370;
	
	public static final int CONST_TIEMPO = 60000000;
	
	private  Jugador jugador;
	
	private int puntajePrevio;

	private  boolean pasarDeSeccion;
	
	private  boolean pasarDeNivel;
	
	private  boolean reinicioNivel;
	
	private  boolean reinicioSeccion;
	
	private  int nroNivel;
	
	private  int nroSeccion;
	
	private Controlador controladores[];
	
	private double tiempo;
	
	private Ranking ranking;
	
	public static Juego getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Juego();
		}
		return INSTANCE;
	}
	
	private Juego() {
		Scanner teclado= new Scanner(System.in);
		//System.out.println("Ingrese su nickname");
		ranking = new Ranking();
		/*ranking.cargarMejoresJugadores();
		try{
			ranking.escribirRanking();
		} catch (Exception e) {
			System.out.println("No se pudo escribir");
		}//era para comprobar que lee y escribe bien*/
		ranking.leerRanking();
		//cambie el set por mandarlo al constructor
		jugador= new Jugador("nuevo", 0);
		puntajePrevio=0;
		nroNivel=0;
		//this.iniciarNivel(false);
		pasarDeNivel=false;
		pasarDeSeccion=false;
		reinicioNivel=false;
		reinicioSeccion=false;
		teclado.close();
	}
	/**
	 * iniciarNivel comienza un nuevo nivel(generando
	 * el edificio principalmente) o reinicia el actual
	 * @param reinicio indica si el inicio de este nivel es un reinicio o no
	 */
	public void iniciarNivel(boolean reinicio) {
		ControladorDeLadrillos brickController;
		nroSeccion=0;
		nivel = new Nivel(nroNivel);
		if (reinicio) {
			Edificio.getInstance().reiniciarEdificio();
			for (int i=0;i<4;i++) {
				controladores[i].avanzarSeccion();
			}
			jugador.setPuntaje(puntajePrevio);
			reinicioNivel=false;
		} else {
			nivel.generarEdificio();
			puntajePrevio=jugador.getPuntaje();
		}
		controladores= new Controlador[4];
		brickController= new ControladorDeLadrillos(Dificultad.getVelocidadLadrillos(nroNivel));
		controladores[0]= brickController;
		controladores[1]= new ControladorDeRalph(Dificultad.getFrecuenciaGolpeo(nroNivel), brickController);
		controladores[2]= new ControladorDePajaro();
		controladores[3]= new ControladorDePastel();	
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[0]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[0].getVentanas()[2][2]);
		tiempo=nivel.getTiempoMax();
	}
	/**
	 * reinicia la seccion actual en la que transcurre el juego
	 */
	public void reiniciarSeccion() {
		for (int i=0;i<4;i++) {
			controladores[i].avanzarSeccion();
		}
		Edificio.getInstance().reiniciarSeccion(nroSeccion);
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[nroSeccion]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[nroSeccion].getVentanas()[2][2]);
		reinicioSeccion=false;
	}
	/**
	 * permite la sincronizacion
	 * e interaccion entre las diferentes entidades del juego
	 */
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
				for (int i=0;i<4;i++) {
					if (i!=2)
					controladores[i].actualizar();
				}
				Felix.getInstance().actualizarInvulnerabilidad();
			}
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * @return verdadero si las vidas de Felix son 0
	 */
	public boolean perdio() {
		return (Felix.getInstance().getVidas()==0 || tiempo<=0);
	}
	
	
	/**
	 * carga la siguiente seccion del edificio 
	 */
	public void avanzarSeccion() {
		for (int i=0;i<4;i++) {
			controladores[i].avanzarSeccion();
		}
		nroSeccion++;
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[nroSeccion]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[nroSeccion].getVentanas()[2][Felix.getInstance().getVentanaActual().getNroColumna()]);
		pasarDeSeccion=false;
		System.out.println("Avanzaste a la seccion "+ nroSeccion);
	}
	/**
	 * iniciara un nuevo nivel desde cero 
	 */
	public void avanzarNivel() {
		for (int i=0;i<4;i++) {
			controladores[i].avanzarSeccion();
		}
		nroNivel++;
		iniciarNivel(false);
		pasarDeSeccion=false;
		pasarDeNivel=false;
		System.out.println("Victoria!! Avanzas al nivel " + (nroNivel+1));//nroNivel va de 0 a 9
	}
	
	public int getNroSeccion() {
		return nroSeccion;
	}
	
	public void ganar() {
		System.out.println("Ganaste, congratuleishon, tu punteaje fue:"+ jugador.getPuntaje());
	}
	
	
	/**
	 * Analiza si la seccion actual (recibida por parametro) tiene todas sus 
	 * ventanas sanas 
	 * @param seccion recibe la seccion que se desea comprobar
	 */
	public void comprobarSeccionLimpia(Seccion seccion){
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


	public void ladrilloGolpeoAFelix() {
		reinicioNivel=true;
		System.out.println("Te ha golpeado un ladrillo y se reiniciará el nivel");
	}

	public void pajaroGolpeoAFelix() {
		reinicioSeccion=true;
		System.out.println("Te ha golpeado un pajaro y se reiniciara la seccion");
	}

	public Jugador getJugador() {
		return jugador;
	}
	
	public Jugador[] mejoresCinco(){
		return ranking.getmejoresDiez();
	}
	
	public void setNroNivel (int nroNivel) {
		this.nroNivel=nroNivel;
	}
	//verificar si se puede hacer de otra manera
	public List<Posicion> getListaPosPajaros(){
		return controladores[2].getListaPosEntidades();
	}

	public List<Posicion> getListaPosLadrillos(){
		return controladores[0].getListaPosEntidades();
	}
	
	public Posicion getPosPastel() {
		return controladores[3].getListaPosEntidades().get(0);
	}
	
	public Posicion getPosRalph() {
		return controladores[1].getListaPosEntidades().get(0);
	}
}
