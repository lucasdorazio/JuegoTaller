package juego;

import java.util.ArrayList;

import taller2.modelo.Dibujable;
import taller2.vista.Graficador;

public class Test {

	public static void main(String[] args) {
		/*Ventana[] v = new Ventanas[2];
		v[0]=new Comun(2, 2, true, true, EstadoPanel.SANO, EstadoPanel.SANO);
		v[1]=new Comun(2, 3, true, false, EstadoPanel.MEDIO_ROTO, EstadoPanel.SANO);
		*/
		Juego j = new Juego();
		ArrayList<Dibujable> lista = new ArrayList<Dibujable>();
		j.iniciarNivel(false);
		lista.add(Felix.getInstance());
		while (!j.perdio()) {
			j.actualizarRalph();
			j.actualizar();
			Graficador.refrescarTopDown(lista, 1000);
		}
		int i=0;
		i++;
	}

}
