package juego;

import taller2.modelo.Dibujable;
import taller2.modelo.InformacionDibujable;

/**
 * 
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Ralph implements Desplazable, Dibujable{

	private static final int LADRILLOS_POR_TIRADA = 3;
	private static final double TIEMPO_ENTRE_LADRILLOS=0.5;
	private static final int CONST_TIEMPO=60000000;	//Cantidad de llamadas al método por segundo
	private int ladrillosTotales;
	private int ladrillosRestantes;
	private Posicion pos;
	private int velocidad;	//velocidad= pixeles/seg
	private int timer;
	private int pasosRestantes;
	private Direcciones dirActual;
	
	public int getLadrillos() {
		return ladrillosTotales;
	}

	public void setLadrillos(int ladrillos) {
		this.ladrillosTotales = ladrillos;
	}

	public void setPosicion(Posicion pos) {
		this.pos = pos;
	}
	
	/**
	 * Inicialización de valores por defecto de los atributos de Ralph 
	 */
	public Ralph() {
		this.ladrillosTotales = 40;	
		this.pos =new Posicion(350,390);	//Parte superior de la sección, en el centro
		this.velocidad=150;
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
		timer++;
		if (timer>TIEMPO_ENTRE_LADRILLOS*CONST_TIEMPO) {			
			ControladorDeLadrillos.generarLadrillo(new Posicion(pos.getPosX()+(ladrillosRestantes*15)-30,340));			//Genero ladrillos a 15, 0 y -15 pixeles de Ralph
			ladrillosRestantes--;			
			timer=0;
		}
		return (ladrillosRestantes==0);
	}

	
	/**
	 * Fase de ejecución de la caminata
	 * @return true cuando llego el fin de su movimiento
	 */
	@Override
	public boolean avanzar() { 
		timer++;
		if (timer > CONST_TIEMPO/velocidad) { 
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
			timer=0;
		}
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
