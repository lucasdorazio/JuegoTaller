package controladores;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

	private static List<Ladrillo> listaLadrillos = new LinkedList<Ladrillo>();
	private int timer;
	private int velocidad;

	/**
	 * 
	 * @param velocidad indica la velocidad a la cual el ladrillo se movera
	 * por la pantalla
	 */
	public ControladorDeLadrillos(int velocidad) {
		this.timer = 0;
		this.velocidad = velocidad;
	}
	
	public static List<Ladrillo> getListaLadrillos(){
		return listaLadrillos;
	}

	/**
	 * Instancia un nuevo ladrillo
	 * @param pos recibe la posicion que se le asignara al Ladrillo
	 */
	public void generarLadrillo(Posicion pos) {
		Ladrillo l = new Ladrillo(pos);
		listaLadrillos.add(l);
		System.out.println("Se tiró un ladrillo desde ("+ pos.getPosX()+ ", "+ pos.getPosY()+")");
	}

	/**
	 * maneja la posicion de cada ladrillo existente en todo momento
	 */
	public void actualizar() {
		timer++;
		boolean impacto=false;
		Ventana ventanaActualFelix = Felix.getInstance().getVentanaActual();
		Ventana ventanaActualLadrillo;
		if (timer > Juego.CONST_TIEMPO / velocidad) {
			Ladrillo ladrillo;
			Iterator<Ladrillo> ite = listaLadrillos.iterator();
			while (ite.hasNext() && !impacto) {
				ladrillo = ite.next();
				if (ladrillo.avanzar()) {
					ite.remove();
				} else {
					ventanaActualLadrillo = ladrillo.devolverVentana();
					if (ventanaActualLadrillo!=null &&
							ventanaActualLadrillo.equals(ventanaActualFelix)) {
						Juego.getInstance().ladrilloGolpeoAFelix();
						Felix.getInstance().recibirImpactoLadrillo();
						impacto=true;
					}
				}
			}
			timer=0;
		}
	}
	/**
	 * Descarta todos los ladrillos existentes
	 */
	public void avanzarSeccion() {
		listaLadrillos.clear();
	}

}
