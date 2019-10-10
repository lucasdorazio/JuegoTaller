package juego;

import java.util.ArrayList;

import taller2.modelo.Dibujable;
import taller2.vista.Graficador;

public class Test {

	public static void main(String[] args) {
		Juego j = new Juego();
		ArrayList<Dibujable> lista = new ArrayList<Dibujable>();
		
		while (!j.perdio()) {
			j.actualizarRalph();
			j.actualizar();
			Graficador.refrescarTopDown(lista, 1000);
		}
	}

}
