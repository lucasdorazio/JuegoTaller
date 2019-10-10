package juego;

import java.util.ArrayList;

import taller2.modelo.Dibujable;
import taller2.vista.Graficador;

public class Test {

	public static void main(String[] args) {
		Juego j = new Juego();
		//lista.add(Felix.getInstance());
		while (!j.perdio()) {
			j.actualizarRalph();
			j.actualizar();
			Graficador.refrescarTopDown(Juego.getLista(), 1000);
			Felix.getInstance().mover(Direcciones.ARRIBA);
			Felix.getInstance().mover(Direcciones.ARRIBA);
			Felix.getInstance().mover(Direcciones.ARRIBA);
//			Felix.getInstance().mover(Direcciones.DERECHA);
		}
	}

}
