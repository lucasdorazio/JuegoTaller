package juego;
import java.util.List;
import java.util.Scanner;

import controladores.Controlador;
import controladores.ControladorDeJuego;
import controladores.ControladorDeLadrillos;
import controladores.ControladorDePajaro;
import controladores.ControladorDePastel;
import controladores.ControladorDeRalph;
import edificio.Edificio;
import edificio.Seccion;
import entidades.Felix;
/**
 * Clase que conecta todas las componentes del juego, ya sea conociendolas
 * o usandolas como atributos propios.
 * Su metodo principal será el actualizar()
 * @author Lucas y Renzo
 *
 */
import entidades.Posicion;
import excepciones.ImproperNameException;
import excepciones.InvalidCharacterNameException;
import excepciones.TooLongNameException;
import excepciones.TooShortNameException;
public class Juego implements Runnable{
	
	private static Juego INSTANCE;
	
	private Nivel nivel;
	
	public static final int LIMITE_DERECHO_MAPA=675;
	
	public static final int LIMITE_IZQUIERDO_MAPA=0;
	
	public static final int LIMITE_INFERIOR_MAPA=370;
	
	public static final int CONST_TIEMPO = 60000000;
	
	private  Jugador jugador;

	private  boolean pasarDeSeccion;
	
	private  boolean pasarDeNivel;
	
	private  boolean reinicioNivel;
	
	private  boolean reinicioSeccion;
	
	private  int nroNivel;
	
	private  int nroSeccion;
	
	private int puntajePrevioNivel;
	private int puntajePrevioSeccion;

	private int timerTiempo;
	
	private Controlador controladores[];
	
	private int tiempo;
	
	private Ranking ranking;
	
	public static Juego getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Juego();
		}
		return INSTANCE;
	}
	
	private Juego() {
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
		jugador= new Jugador();
		puntajePrevioNivel=0;
		nroNivel=0;
		//this.iniciarNivel(false);
		pasarDeNivel=false;
		pasarDeSeccion=false;
		reinicioNivel=false;
		reinicioSeccion=false;
		timerTiempo=0;
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
			jugador.setPuntaje(puntajePrevioNivel);
			reinicioNivel=false;
		} else {
			nivel.generarEdificio();
			puntajePrevioNivel=jugador.getPuntaje();
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
		jugador.setPuntaje(puntajePrevioSeccion);
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
		if (perdio()) perder();
		else {
			if (reinicioNivel) {
				iniciarNivel(true);
				System.out.println("Quedan " + Felix.getInstance().getSeccionActual().getVentanasRestantes()
						+ " ventanas restantes");
			} else if (reinicioSeccion) {
				reiniciarSeccion();
				System.out.println("Quedan " + Felix.getInstance().getSeccionActual().getVentanasRestantes()
						+ " ventanas restantes");
			} else {
				timerTiempo++;
				if (timerTiempo > 1000 / ControladorDeJuego.ACTUALIZACION) {
					tiempo--;
					timerTiempo = 0;

				}
				if (pasarDeNivel)
					avanzarNivel();
				else if (pasarDeSeccion)
					avanzarSeccion();
				else {
					for (int i = 0; i < 4; i++) {
						// if (i==1) continue;
						// if (i==2) continue;
						controladores[i].actualizar();
					}
					Felix.getInstance().actualizarInvulnerabilidad();
				}
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
		puntajePrevioSeccion=jugador.getPuntaje();
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
		boolean nombreCorrecto=false;
		System.out.println("Ganaste, congratuleishon, tu punteaje fue:"+ jugador.getPuntaje());
		if (ranking.estaEntreLosMejoresCinco(jugador.getPuntaje())) {
			while (!nombreCorrecto) {
				try {
					pedirNombre();
					nombreCorrecto = true;
				} catch (ImproperNameException e) {
					System.out.println("ERROR: " + e.toString());
				}
			}
			ranking.actualizarRanking(jugador);
		}
	}
	
	public void pedirNombre() throws ImproperNameException{
		Scanner teclado= new Scanner(System.in);
		String nombre;
		System.out.println("Felicitaciones, su puntaje esta en el Top5, ingrese su nombre");
		nombre= teclado.next();
		teclado.close();
		if (nombre.length() < 2) throw new TooShortNameException();
		if (nombre.length() > 20) throw new TooLongNameException();
		if (nombre.contains(" ")) throw new InvalidCharacterNameException();
		jugador.setNick(nombre);
	}
	
	public void perder() {
		boolean nombreCorrecto=false;
		System.out.println("Lo lamento, has perdido. Tu punteaje fue: "+ jugador.getPuntaje());
		if (ranking.estaEntreLosMejoresCinco(jugador.getPuntaje())) {
			while (!nombreCorrecto) {
				try {
					pedirNombre();
					nombreCorrecto = true;
				} catch (ImproperNameException e) {
					System.out.println("ERROR: " + e.toString());
				}
			}
			ranking.actualizarRanking(jugador);
		}
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
