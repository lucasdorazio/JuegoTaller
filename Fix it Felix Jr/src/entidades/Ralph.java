package entidades;

import java.util.Timer;
import java.util.TimerTask;

import controladores.ControladorDeLadrillos;
import edificio.Edificio;
import juego.Juego;
import taller2.modelo.Dibujable;
import taller2.modelo.InformacionDibujable;

/**
 * 
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Ralph implements Desplazable, Dibujable{

	private static final int LADRILLOS_POR_TIRADA = 3;
	public final int TIEMPO_ENTRE_LADRILLOS=500;
	private int ladrillosTotales;
	private int ladrillosRestantes;
	private Posicion pos;
	private int velocidad;	//velocidad= pixeles/seg
	private int timer;
	private int pasosRestantes;
	private Direcciones dirActual;
	private ControladorDeLadrillos brickController;
	private Timer timerr;
	
	public int getLadrillos() {
		return ladrillosTotales;
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	
	/**
	 * Inicialización de valores por defecto de los atributos de Ralph 
	 */
	public Ralph(ControladorDeLadrillos brickController) {
		this.brickController=brickController;
		this.ladrillosTotales = 40;	
		this.pos =new Posicion(260,6);	//Parte superior de la sección, en el centro
		this.velocidad=150;
		timerr= new Timer();
	}
	
	/**
	 * Asigna parámetros para inicializar la fase de golpeo de Ralph
	 * Permite independizar la etapa de inicio de la de ejecución
	 */
	public void comenzarGolpeo() {
		ladrillosRestantes=LADRILLOS_POR_TIRADA;
		this.timer=0;
		System.out.println("Ralph inicio golpeo");
		golpearEdif();
		TimerTask golpear= new TimerTask() {
			
			@Override
			public void run() {
				if (golpearEdif()) this.cancel();
			}
		};
		timerr.schedule(golpear, 0, TIEMPO_ENTRE_LADRILLOS);
	}

	/**
	 * Asigna parámetros para inicializar la caminata de Ralph
	 * @param cantPasos determina la cantidad de pixeles que se moverá desde su posición de inicio
	 * @param direccion indica la dirección inicial del movimiento (izquierda o derecha)
	 */
	public void comenzarMovimiento(int cantPasos, Direcciones direccion) {
		pasosRestantes = cantPasos * 46; // Un paso es igual a 46 pixeles
		dirActual = direccion;
		System.out.println("Ralph inicio movimiento");
		this.timer=0;
		this.avanzar();
	}
	
	/**
	 * Fase de ejecución del golpeo
	 * @return true cuando no tiene que tirar más ladrillos
	 */
	public boolean golpearEdif() {			
		brickController.generarLadrillo(new Posicion(pos.getPosX()+((LADRILLOS_POR_TIRADA- ladrillosRestantes)*15),100));			//Genero ladrillos a 15, 0 y -15 pixeles de Ralph
		ladrillosRestantes--;			
		if (ladrillosRestantes==0) {
			ladrillosRestantes=LADRILLOS_POR_TIRADA;
			return true;
		}else return false;
	}

	
	/**
	 * Fase de ejecución de la caminata
	 * @return true cuando llego el fin de su movimiento
	 */
	@Override
	public boolean avanzar() {  
			if (dirActual == Direcciones.DERECHA) {
				if (pos.getPosX() + 1 > Edificio.getLimiteDerechoEdificio()) {
					dirActual = Direcciones.IZQUIERDA;
					pos.disminuirPosX();
				} else
					pos.aumentarPosX();
			} else if (pos.getPosX()-1 < Edificio.getLimiteIzquierdaEdificio()) {
				dirActual = Direcciones.DERECHA;
				pos.aumentarPosX();
			} else
				pos.disminuirPosX();
			pasosRestantes--;
		return (pasosRestantes == 0);
	}

	@Override
	public Posicion getPos() {
		return this.pos;
	}

	@Override
	public void setPos(Posicion pos) {
		this.pos=pos;
	}

	@Override
	public Direcciones obtenerDireccion() {
		return this.dirActual;
	}

	@Override
	public InformacionDibujable getInformacionDibujable() {
		return new InformacionDibujable(pos.getPosX(), pos.getPosY(), 'R');
	}

}
