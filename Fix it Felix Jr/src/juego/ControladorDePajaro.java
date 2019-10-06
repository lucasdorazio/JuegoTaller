package juego;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ControladorDePajaro {

	private static final int VELOCIDAD = 10; // cambiar valor
	private int tiempoDeSpawneo;
	private List<Pajaro> listaDePajaros;
	private int timer,timerGeneracion;

	public ControladorDePajaro() {
		// this.tiempoDeSpawneo=?
		listaDePajaros = new LinkedList<Pajaro>();
		timer = 0;
		timerGeneracion=0;
	}

	public void generarPajaros() {
		timerGeneracion++;
		int fila;
		Direcciones dir;
		if (timerGeneracion>tiempoDeSpawneo*10000) {
			fila= (int) (Math.random()*2);
			if ((int) (Math.random()*2)==0) dir=Direcciones.DERECHA
			
			
			timerGeneracion=0;
		}
	}

	public void actualizarPosPajaros() {
		timer++;
		Pajaro pajaro;
		if (timer > 10000 / VELOCIDAD) {
			Iterator<Pajaro> ite = listaDePajaros.iterator();
			while (ite.hasNext()) {
				pajaro = ite.next();
				if (pajaro.avanzar()) {
					ite.remove();
				}
			}
			timer=0;
		}
	}

}
