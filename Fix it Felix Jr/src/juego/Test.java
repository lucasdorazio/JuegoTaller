package juego;

import java.util.ArrayList;

import taller2.modelo.Dibujable;
import taller2.vista.Graficador;

public class Test {

	public static void main(String[] args) {
		Juego j = new Juego();
		ArrayList<Dibujable> lista = new ArrayList<Dibujable>();
		lista.add(Felix.getInstance());
		int timer=0;
		//lista.add(Felix.getInstance());

		while (!j.perdio()) {
			j.actualizar();
			Graficador.refrescarTopDown(Juego.getLista(), 1000);
			Felix.getInstance().mover(Direcciones.ARRIBA);
			Felix.getInstance().mover(Direcciones.ARRIBA);
			Felix.getInstance().mover(Direcciones.ARRIBA);
//			Felix.getInstance().mover(Direcciones.DERECHA);
		}
		if (Felix.getInstance().getVidas()==0) System.out.println("felix se quedo sin vidas");
		else System.out.println("se quedaron sin tiempo");
	}

}
