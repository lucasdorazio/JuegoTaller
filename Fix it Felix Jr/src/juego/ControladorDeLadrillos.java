package juego;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ControladorDeLadrillos {

	private static List<Ladrillo> listaLadrillos = new LinkedList<Ladrillo>();
	private static final int CONST_TIEMPO=60000000;	//Cantidad de llamadas al método por segundo
	private int timer;
	private int velocidad;

	public ControladorDeLadrillos(int velocidad) {
		this.timer = 0;
		this.velocidad = velocidad;
	}
	
	public static List<Ladrillo> getListaLadrillos(){
		return listaLadrillos;
	}

	public static void generarLadrillo(Posicion pos) {
		Ladrillo l = new Ladrillo(pos);
		listaLadrillos.add(l);
		System.out.println("Se genero un ladrillo en ("+ pos.getPosX()+ ", "+ pos.getPosY()+")");
	}

	public void actualizarLadrillos() {
		timer++;
		if (timer > CONST_TIEMPO / velocidad) {
			Ladrillo ladrillo;
			Iterator<Ladrillo> ite = listaLadrillos.iterator();
			while (ite.hasNext()) {
				ladrillo = ite.next();
				if (ladrillo.avanzar()) {
					ite.remove();
					System.out.println("Se eliminó un ladrillo");
				}
			}
			timer=0;
		}
	}
	
	public void eliminarLadrillos() {
		listaLadrillos.clear();
	}

}
