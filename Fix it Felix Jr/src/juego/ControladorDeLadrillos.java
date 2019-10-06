package juego;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ControladorDeLadrillos {

	private static List<Ladrillo> listaLadrillos = new LinkedList<Ladrillo>();
	private int timer;
	private int velocidad;

	public ControladorDeLadrillos(int velocidad) {
		this.timer = 0;
		this.velocidad = velocidad;
	}

	public static void generarLadrillo(Posicion pos) {
		listaLadrillos.add(new Ladrillo(pos));
	}

	public void actualizarLadrillos() {
		timer++;
		if (timer > 10000 / velocidad) {
			Ladrillo ladrillo;
			Iterator<Ladrillo> ite = listaLadrillos.iterator();
			while (ite.hasNext()) {
				ladrillo = ite.next();
				if (ladrillo.avanzar())
					ite.remove();
			}
			timer=0;
		}
	}

}
