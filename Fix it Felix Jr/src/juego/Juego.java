package juego;

import controladores.Controlador;
import controladores.ControladorDeJuego;
import controladores.ControladorDeLadrillos;
import controladores.ControladorDePajaro;
import controladores.ControladorDePastel;
import controladores.ControladorDeRalph;
import edificio.Edificio;
import edificio.Seccion;
import entidades.EstadoPajaro;
import entidades.EstadoPastel;
import entidades.EstadoRalph;
import entidades.Felix;
import entidades.InfoGraficable;
/**
 * Clase que conecta todas las componentes del juego, ya sea conociendolas
 * o usandolas como atributos propios.
 * Su metodo principal será el actualizar()
 * @author Lucas y Renzo
 *
 */
import entidades.Posicion;
public class Juego {
	
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
	
	private int timerTiempo;
	
	@SuppressWarnings("rawtypes")
	private Controlador controladores[];
	
	private int tiempo;
	
	private EstadoJuego estado;
	
	public static Juego getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Juego();
		}
		return INSTANCE;
	}
	
	private Juego() {
		//System.out.println("Ingrese su nickname");
		/*ranking.cargarMejoresJugadores();
		try{
			ranking.escribirRanking();
		} catch (Exception e) {
			System.out.println("No se pudo escribir");
		}//era para comprobar que lee y escribe bien*/
		jugador= new Jugador();
		nroNivel=0;
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
			reinicioNivel=false;
		} else {
			nivel.generarEdificio();
			Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[0].getVentanas()[2][2]);
		}
		controladores= new Controlador[4];
		brickController= new ControladorDeLadrillos(Dificultad.getVelocidadLadrillos(nroNivel));
		controladores[0]= brickController;
		controladores[1]= new ControladorDeRalph(Dificultad.getFrecuenciaGolpeo(nroNivel), brickController);
		controladores[2]= new ControladorDePajaro();
		controladores[3]= new ControladorDePastel();	
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[0]);
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
		reinicioSeccion=false;
	}
	/**
	 * permite la sincronizacion
	 * e interaccion entre las diferentes entidades del juego
	 */
	public void actualizar() {
		if (reinicioNivel) {
			iniciarNivel(true);
		} else if (reinicioSeccion) {
			reiniciarSeccion(); 
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
					controladores[i].actualizar();
				}
				Felix.getInstance().actualizar();
			}
		}
	}
	/**
	 * 
	 * @return verdadero si las vidas de Felix son 0
	 */
	public boolean perdio() {
		return (Felix.getInstance().getVidas()==0 || tiempo<=0);
	}
	
	public boolean gano() {
		if ((nroNivel == 9) && (nroSeccion == 2) && (Edificio.getInstance().getSecciones()[nroSeccion].getVentanasRestantes() == 0))
			return true;
		else return false;
	}
	
	
	/**
	 * carga la siguiente seccion del edificio 
	 */
	public void avanzarSeccion() {
		limpiarEntidades();
		nroSeccion++;
		Felix.getInstance().setSeccionActual(Edificio.getInstance().getSecciones()[nroSeccion]);
		Felix.getInstance().setVentanaActual(Edificio.getInstance().getSecciones()[nroSeccion].getVentanas()[2][Felix.getInstance().getVentanaActual().getNroColumna()]);
		pasarDeSeccion=false;
		System.out.println("Avanzaste a la seccion "+ (nroSeccion+1) + "del nivel "+ nroNivel);
	}
	/**
	 * iniciara un nuevo nivel desde cero 
	 */
	public void avanzarNivel() {
		limpiarEntidades();
		nroNivel++;
		iniciarNivel(false);
		pasarDeSeccion=false;
		pasarDeNivel=false;
		System.out.println("Victoria!! Avanzas al nivel " + (nroNivel+1));//nroNivel va de 0 a 9
	}
	
	public void limpiarEntidades() {
		for (int i=0;i<4;i++) {
			controladores[i].avanzarSeccion();
		}
	}
	
	public int getNroSeccion() {
		return nroSeccion;
	}	
	
	/**
	 * Analiza si la seccion actual (recibida por parametro) tiene todas sus 
	 * ventanas sanas 
	 * @param seccion recibe la seccion que se desea comprobar
	 */
	public void comprobarSeccionLimpia(Seccion seccion){
		if (seccion.getVentanasRestantes()==0) {
			if (nroSeccion == 2) {
				if (nroNivel!=9) pasarDeNivel=true;
			} else {
				pasarDeSeccion=true;
			}
		}
	}
	
	public void comenzarJuego(int nivel) {
		this.nroNivel=nivel;
		Juego.getInstance().iniciarNivel(false);
		Felix.getInstance().reiniciarVidas();
		jugador= new Jugador();
	}

	public void ladrilloGolpeoAFelix() {
		reinicioNivel=true;
	}

	public void pajaroGolpeoAFelix() {
		reinicioSeccion=true;
	}

	public Jugador getJugador() {
		return jugador;
	}
	
	public void setNroNivel (int nroNivel) {
		this.nroNivel=nroNivel;
	}
	
	public Posicion getPosRalph() {
		ControladorDeRalph ralphController= (ControladorDeRalph) controladores[1];
		return ralphController.getPosRalph();
	}
	
	@SuppressWarnings("unchecked")
	public InfoGraficable<EstadoPajaro> getInfoGraficablePajaros(){
		return controladores[2].getListaInfoGraficable();
	}

	public InfoGraficable<?> getInfoGraficableLadrillos(){
		return controladores[0].getListaInfoGraficable();
	}
	
	@SuppressWarnings("unchecked")
	public InfoGraficable<EstadoPastel> getInfoGraficablePastel(){ 
		return controladores[3].getListaInfoGraficable();
	}
	
	@SuppressWarnings("unchecked")
	public InfoGraficable<EstadoRalph> getInfoGraficableRalph() {
		return controladores[1].getListaInfoGraficable();
	}
	
	public void setEstado(EstadoJuego estado) {
		this.estado=estado;
	}
	
	public EstadoJuego getEstado() {
		return estado;
	}
	
	public int getPuntaje() {
		return jugador.getPuntaje();
	}
	
	public int getTime() {
		return tiempo;
	}
	
	public void alternarPausa() {
		if (getEstado() != EstadoJuego.PAUSA) {
			setEstado(EstadoJuego.PAUSA);
		} else
			Juego.getInstance().setEstado(EstadoJuego.NORMAL);
	}
	
}
