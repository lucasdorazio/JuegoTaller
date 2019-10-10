package juego;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ControladorDePajaro {

	private static final int VELOCIDAD = 56;
	private static final int CONST_TIEMPO=60000000;	//Cantidad de llamadas al método por segundo
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

	public void generarPajaros() {	//Genero 1 pajaro cada tiempoDeSpawneo segundos
		Pajaro p;
		timerGeneracion++;
		int fila;
		Direcciones dir;
		if (timerGeneracion>tiempoDeSpawneo*CONST_TIEMPO) {
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

	public void actualizarPosPajaros() {
		timerMovimiento++;
		Pajaro pajaro;
		if (timerMovimiento > CONST_TIEMPO / VELOCIDAD) {
			Iterator<Pajaro> ite = listaDePajaros.iterator();
			while (ite.hasNext()) {
				pajaro = ite.next();
				if (pajaro.avanzar()) {
					ite.remove();
					System.out.println("Se elimino un pajaro");
				}
			}
			timerMovimiento=0;
		}
	}
	
	public void eliminarPajaros() {
		listaDePajaros.clear();
	}

}
