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
		lista.add(Felix.getInstance());
		int timer=0;
		while (!j.perdio()) {
			j.actualizar();
		}
		if (Felix.getInstance().getVidas()==0) System.out.println("felix se quedo sin vidas");
		else System.out.println("se quedaron sin tiempo");
	}

}
