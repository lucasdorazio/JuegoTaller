package controladores;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edificio.Ventana;
import entidades.Felix;
import entidades.Ladrillo;
import entidades.Posicion;
import juego.Juego;
/**
 * La clase ControladorDeLadrillos se encarga de la administracion
 * de los objetos de la clase Ladrillo
 * @author Lucas y Renzo
 *
 */
public class ControladorDeLadrillos extends Controlador{

	private static final int ACTUALIZACION_POSICION= 1000; 
	private static List<Ladrillo> listaLadrillos = new LinkedList<Ladrillo>();
	private int velocidad;
	private Timer timerr;

	/**
	 * 
	 * @param velocidad indica la velocidad a la cual el ladrillo se movera
	 * por la pantalla
	 */
	public ControladorDeLadrillos(int velocidad) {
		this.velocidad = velocidad;
		timerr= new Timer();
		TimerTask movimiento= new TimerTask() {			
			@Override
			public void run() {
				actualizar();
			}
		};
		timerr.schedule(movimiento, 0, ACTUALIZACION_POSICION);
	}
	
	public List<Ladrillo> getListaLadrillos(){
		return listaLadrillos;
	}

	/**
	 * Instancia un nuevo ladrillo
	 * @param pos recibe la posicion que se le asignara al Ladrillo
	 */
	public void generarLadrillo(Posicion pos) {
		Ladrillo l = new Ladrillo(pos);
		listaLadrillos.add(l);
		//System.out.println("Se tiró un ladrillo desde ("+ pos.getPosX()+ ", "+ pos.getPosY()+")");
	}

	/**
	 * maneja la posicion de cada ladrillo existente en todo momento
	 */
	public void actualizar() {
		boolean impacto = false;
		Ventana ventanaActualFelix = Felix.getInstance().getVentanaActual();
		Ventana ventanaActualLadrillo;
		Ladrillo ladrillo;
		Iterator<Ladrillo> ite = listaLadrillos.iterator();
		while (ite.hasNext() && !impacto) {
			ladrillo = ite.next();
			if (ladrillo.avanzar()) {
				ite.remove();
			} else {
				ventanaActualLadrillo = ladrillo.devolverVentana();
				if (ventanaActualLadrillo != null && ventanaActualLadrillo.equals(ventanaActualFelix)) {
					Juego.getInstance().ladrilloGolpeoAFelix();
					Felix.getInstance().recibirImpactoLadrillo();
					impacto = true;
				}
			}
		}
	}
	/**
	 * Descarta todos los ladrillos existentes
	 */
	public void avanzarSeccion() {
		listaLadrillos.clear();
	}

}
