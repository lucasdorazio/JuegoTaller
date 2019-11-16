package entidades;

import controladores.ControladorDeLadrillos;
import edificio.Edificio;
import taller2.modelo.Dibujable;
import taller2.modelo.InformacionDibujable;

/**
 * 
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Ralph implements Desplazable, Dibujable{

	private static final int LADRILLOS_POR_TIRADA = 3;
	private int ladrillosTotales;
	private int ladrillosRestantes;
	private Posicion pos;
	private int velocidad;	//velocidad= pixeles/seg
	private int pasosRestantes;
	private EstadoRalph estado;
	private Direcciones dirActual;
	private ControladorDeLadrillos brickController;
	
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
		this.estado=EstadoRalph.NORMAL1;
	}
	
	/**
	 * Asigna parámetros para inicializar la fase de golpeo de Ralph
	 * Permite independizar la etapa de inicio de la de ejecución
	 */
	public void comenzarGolpeo() {
		this.estado= EstadoRalph.GOLPEANDO1;
		ladrillosRestantes=LADRILLOS_POR_TIRADA;
		System.out.println("Ralph inicio golpeo");
		//golpearEdif();
	}

	/**
	 * Asigna parámetros para inicializar la caminata de Ralph
	 * @param cantPasos determina la cantidad de pixeles que se moverá desde su posición de inicio
	 * @param direccion indica la dirección inicial del movimiento (izquierda o derecha)
	 */
	public void comenzarMovimiento(int cantPasos, Direcciones direccion) {
		pasosRestantes = cantPasos * 46; // Un paso es igual a 46 pixeles
		dirActual = direccion;
		if (dirActual == Direcciones.DERECHA)
			estado=EstadoRalph.CAMINANDO_DERECHA1;
		else estado=EstadoRalph.CAMINANDO_IZQUIERDA1;
		this.avanzar();
	}
	
	/**
	 * Fase de ejecución del golpeo
	 * @return true cuando no tiene que tirar más ladrillos
	 */
	public boolean golpearEdif() {			
		brickController.generarLadrillo(new Posicion(pos.getPosX()+((LADRILLOS_POR_TIRADA- ladrillosRestantes)*15),100));
		ladrillosRestantes--;			
		return (ladrillosRestantes==0);
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
					estado= EstadoRalph.CAMINANDO_IZQUIERDA1;
					pos.disminuirPosX();
				} else
					pos.aumentarPosX();
			} else if (pos.getPosX()-1 < Edificio.getLimiteIzquierdaEdificio()) {
				dirActual = Direcciones.DERECHA;
				estado=EstadoRalph.CAMINANDO_DERECHA1;
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

	public EstadoRalph getEstado() {
		return estado;
	}

	public void setEstado(EstadoRalph estado) {
		this.estado = estado;
	}

}
