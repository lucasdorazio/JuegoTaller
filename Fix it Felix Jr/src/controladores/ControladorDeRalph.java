package controladores;

import java.util.Timer;
import java.util.TimerTask;

import entidades.Direcciones;
import entidades.Ralph;
import juego.Juego;

/**
 * ControladorDeRalph manejara al objeto de clase Ralph, sus movimientos
 * y comportamiento
 * @author Lucas y Renzo
 * @version 1.0
 */
public class ControladorDeRalph extends Controlador{
	private int tiempoDeGolpeo; // Cada cuantos segundos Ralph golpea
	private int tiempoDeDesplazamiento; // Cada cuantos segundos Ralph se mueve
	private static final int CANT_PASOS_MIN = 3;
	private static final int CANT_PASOS_MAX = 7;
	private boolean estaMoviendose;
	private boolean estaGolpeando;
	private int timerGolpeo;
	private int timerMovimiento;
	private Ralph ralph;
	private Timer timer;
	private HiloDependiente hiloGolpeo;
	private Thread hiloMovimiento;

	/**
	 * 
	 * @param tiempoDeGolpeo recibe cada cuanto tiempo ralph realiza un golpe
	 */
	public ControladorDeRalph(int tiempoDeGolpeo, ControladorDeLadrillos brickController) {
		this.tiempoDeGolpeo=tiempoDeGolpeo*1000;
		this.tiempoDeDesplazamiento= 12;
		ralph= new Ralph(brickController);
		timer= new Timer();
		timerGolpeo = 0;
		timerMovimiento = 0;
		estaMoviendose=false;
		estaGolpeando=false;
		TimerTask golpeo= new TimerTask() {			
			@Override
			public void run() {
				hiloGolpeo= new HiloDependiente(hiloMovimiento, ralph);
				hiloGolpeo.start();
			}
		};
		TimerTask movimiento= new TimerTask() {			
			@Override
			public void run() {
				hiloMovimiento= new Thread() {
					public void run() {
						Direcciones dir;
						if ((int) (Math.random() * 2) == 0)
							dir = Direcciones.DERECHA;
						else
							dir = Direcciones.IZQUIERDA;
						ralph.comenzarMovimiento(calcularCantPasos(), dir);
						while (!ralph.avanzar()) {
							try {
								Thread.sleep(7);	//(int) (1000/ralph.velocidad())
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				};
			}
		};
		timer.schedule(movimiento, 0, tiempoDeDesplazamiento);
		timer.schedule(golpeo, 0, tiempoDeGolpeo);
	}
	/**
	 * 
	 * @return cantidad de pasos que ralf dará en cierta direccion
	 * entre un minimo y un maximo fijo
	 */
	public int calcularCantPasos() {
		return (int) (Math.random() * (CANT_PASOS_MAX - CANT_PASOS_MIN + 1) + CANT_PASOS_MIN);
	}

	
	/**
	 * Se encarga de darle ordenes a ralph, tales como moverse,
	 * cambiar de direccion, golpear edificio
	 */
	public void actualizar() {
		Direcciones dir;
		if (estaMoviendose) {
			if (ralph.avanzar()) {
				estaMoviendose = false;
				timerMovimiento = 0;
				System.out.println("Ralph termino de moverse y su posicion es (" +ralph.getPos().getPosX()+ ";" + ralph.getPos().getPosY()+")");
			}
		} else if (estaGolpeando) {
			if (ralph.golpearEdif()) {
				estaGolpeando=false;
				timerGolpeo = 0;
			}
		} else {
			timerMovimiento++;
			timerGolpeo++;
			if (timerMovimiento > (tiempoDeDesplazamiento * Juego.CONST_TIEMPO)) { 
				if ((int) (Math.random() * 2) == 0)
					dir = Direcciones.DERECHA;
				else
					dir = Direcciones.IZQUIERDA;
				ralph.comenzarMovimiento(calcularCantPasos(), dir);
				estaMoviendose = true;
			} else if (timerGolpeo > (tiempoDeGolpeo * Juego.CONST_TIEMPO)) {
				ralph.comenzarGolpeo();
				estaGolpeando = true;
			}
		}
	}
	
	public void avanzarSeccion() {
	}
	
	private class HiloDependiente extends Thread {
		Thread t;
		Ralph ralph;
		public HiloDependiente (Thread t,Ralph r) {
			this.t=t;
			this.ralph=r;
		}
		
		public void run() {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (!ralph.golpearEdif()) {
				try {
					Thread.sleep(ralph.TIEMPO_ENTRE_LADRILLOS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}