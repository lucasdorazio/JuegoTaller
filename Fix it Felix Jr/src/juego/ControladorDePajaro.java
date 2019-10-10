package juego;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * La clase ControladorDeLadrillos se encarga de la administracion
 * de los objetos de la clase Pajaro
 * @author Lucas y Renzo
 *
 */
public class ControladorDePajaro {

	private static final int VELOCIDAD = 56;
	private int tiempoDeSpawneo;
	private static List<Pajaro> listaDePajaros;
	private int timerMovimiento,timerGeneracion;
	
	public static List<Pajaro> getListaPajaros(){
		return listaDePajaros;
	}
	
	public ControladorDePajaro() {
		this.tiempoDeSpawneo=15;
		listaDePajaros = new LinkedList<Pajaro>();
		timerMovimiento = 0;
		timerGeneracion=0;
	}
	/**
	 * Genera un pajaro cada cierto tiempo fijo
	 */
	public void generarPajaros() {	
		Pajaro p;
		timerGeneracion++;
		int fila;
		Direcciones dir;
		if (timerGeneracion>tiempoDeSpawneo*Juego.getConstTiempo()) {
			fila= (int) (Math.random()*3);
			if ((int) (Math.random()*2)==0) dir=Direcciones.DERECHA;
			else dir=Direcciones.IZQUIERDA;
			p=crearPajaro(fila,dir);
			listaDePajaros.add(p);
			timerGeneracion=0;
		}
	}
	
	private Pajaro crearPajaro(int fila, Direcciones dir) {
		int posY=0, posX;
		switch (fila){
		case 0: posY= 268; break;
		case 1: posY= 168; break;
		case 2: posY= 68; break;
		}
		if (dir==Direcciones.DERECHA) posX=Juego.getLimiteIzquierdoMapa();
		else posX=Juego.getLimiteDerechoMapa();
		System.out.println("Se genero un pajaro en ("+ posX+";"+ posY+")");
		return new Pajaro(new Posicion(posX, posY), dir);
	}

	/**
	 * Actualiza en cada momento la posicion actual de 
	 * los pajaros existentes
	 */
	public void actualizarPosPajaros() {
		timerMovimiento++;
		Pajaro pajaro;
		if (timerMovimiento > Juego.getConstTiempo() / VELOCIDAD) {
			Iterator<Pajaro> ite = listaDePajaros.iterator();
			while (ite.hasNext()) {
				pajaro = ite.next();
				if (pajaro.avanzar()) {
					ite.remove();
				}
			}
			timerMovimiento=0;
		}
	}
	/**
	 * Descarta los pajaros existentes
	 */
	public void eliminarPajaros() {
		listaDePajaros.clear();
	}

}
