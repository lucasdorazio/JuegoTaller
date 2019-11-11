package juego;

import entidades.Direcciones;
import entidades.Felix;
import java.awt.*;

/**
 * Clase que contiene el main para la ejección del juego
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Test {

	public static void main(String[] args) {
		Frame miFrame= new Frame();
		int timer=0;
		int random;
		Direcciones dir=null;
		while (!Juego.getInstance().perdio()) {
			timer++;
			Juego.getInstance().actualizar();
			if (timer > 30000000) {
				Felix.getInstance().reparar();
				if (Felix.getInstance().getVentanaActual().estoySana()) {
					random=(int) (Math.random()*4);
					switch (random){
					case 0: dir=Direcciones.ARRIBA; break;
					case 1: dir=Direcciones.ABAJO; break;
					case 2: dir=Direcciones.IZQUIERDA; break;
					case 3: dir=Direcciones.DERECHA; break;
					}
					Felix.getInstance().mover(dir);
				}
				timer = 0;
			}
		}
		if (Felix.getInstance().getVidas()==0) System.out.println("Has perdido, Felix no tiene más vidas");
		else System.out.println("Has perdido, no hay más tiempo");
	}
}
