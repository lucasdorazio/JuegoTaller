package controladores;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edificio.Ventana;
import entidades.Direcciones;
import entidades.EstadoPajaro;
import entidades.Felix;
import entidades.InfoGraficable;
import entidades.Pajaro;
import entidades.Posicion;
import juego.Juego;
/**
 * La clase ControladorDeLadrillos se encarga de la administracion
 * de los objetos de la clase Pajaro
 * @author Lucas y Renzo
 *
 */
public class ControladorDePajaro extends Controlador<EstadoPajaro>{

	private static final int VELOCIDAD = 1;
	private int tiempoDeSpawneo;
	private int timerGeneracion;
	private int timerMovimiento;
	private int timerSwap;
	private static List<Pajaro> listaDePajaros;
	
	public List<Pajaro> getListaPajaros(){
		return listaDePajaros;
	}
	
	public ControladorDePajaro() {
		this.tiempoDeSpawneo=3;
		listaDePajaros = new LinkedList<Pajaro>();
		timerGeneracion=0;
		timerMovimiento=0;
		timerSwap=0;
	}
	
	public void actualizar() {
		generarPajaros();
		moverPajaros();
	}
	
	/**
	 * Genera un pajaro cada cierto tiempo fijo
	 */
	public void generarPajaros() {	
		Pajaro p;
		timerGeneracion++;
		int fila;
		Direcciones dir;
		if (timerGeneracion>tiempoDeSpawneo* 1000 /ControladorDeJuego.ACTUALIZACION) {
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
		case 0: posY= 110; break;
		case 1: posY= 190; break;
		case 2: posY= 280; break;
		}
		if (dir==Direcciones.DERECHA) posX=Juego.LIMITE_IZQUIERDO_MAPA;
		else posX=Juego.LIMITE_DERECHO_MAPA;
		return new Pajaro(new Posicion(posX, posY), dir);
	}

	/**
	 * Actualiza en cada momento la posicion actual de 
	 * los pajaros existentes
	 */
	public void moverPajaros() {
		boolean cambiar = false;
		timerMovimiento++;
		Pajaro pajaro;
		boolean impacto=false;
		Ventana ventanaActualPajaro;
		Ventana ventanaActualFelix=Felix.getInstance().getVentanaActual();
		if (timerMovimiento >VELOCIDAD * ControladorDeJuego.ACTUALIZACION /1000) {
			timerSwap++;
			Iterator<Pajaro> ite = listaDePajaros.iterator();
			if (timerSwap == 50) {
				cambiar =true; 
				timerSwap =0;
			}
			while (ite.hasNext() && !impacto) {
				pajaro = ite.next();
				if ( cambiar) {
					switch (pajaro.getEstado()) {
					case VOLANDO1:
						pajaro.setEstado(EstadoPajaro.VOLANDO2);
						break;
					case VOLANDO2:
						pajaro.setEstado(EstadoPajaro.VOLANDO1);
						break;
					case VOLANDO3:
						pajaro.setEstado(EstadoPajaro.VOLANDO4);
						break;
					case VOLANDO4:
						pajaro.setEstado(EstadoPajaro.VOLANDO3);
						break;
					default:
						break;
					}
					
				}
				if (pajaro.avanzar()) {
					ite.remove();
				}
				else {
					ventanaActualPajaro=pajaro.devolverVentana();
					if (ventanaActualPajaro!= null && 
							ventanaActualPajaro.equals(ventanaActualFelix)) {
						Juego.getInstance().pajaroGolpeoAFelix();
						impacto=true;
					}
				}
			}
			timerMovimiento=0;
			System.out.println(timerSwap);	
		}
	}
	/**
	 * Descarta los pajaros existentes
	 */
	public void avanzarSeccion() {
		listaDePajaros.clear();
	}

//	@Override
//	public List<Posicion> getListaPosEntidades() {
//		List<Posicion> listaPosiciones = new LinkedList<Posicion>();
//		Iterator<Pajaro> ite = listaDePajaros.iterator();
//		Pajaro pajaro;
//		while (ite.hasNext()) {
//			pajaro = ite.next();
//			listaPosiciones.add(pajaro.getPos());
//		}
//		return listaPosiciones;
//	}
//	
//	@Override
//	public List<EstadoPajaro> getListaEstadoEntidades() {
//		List<EstadoPajaro> listaEstados = new LinkedList<EstadoPajaro>();
//		Iterator<Pajaro> ite = listaDePajaros.iterator();
//		Pajaro pajaro;
//		while (ite.hasNext()) {
//			pajaro = ite.next();
//			listaEstados.add(pajaro.getEstado());
//		}
//		return listaEstados;
//	}

	@Override
	public InfoGraficable<EstadoPajaro> getListaInfoGraficable() {
		InfoGraficable<EstadoPajaro> info = new InfoGraficable<EstadoPajaro>();
		Iterator<Pajaro> ite = listaDePajaros.iterator();
		List<EstadoPajaro> listaEstados = new LinkedList<EstadoPajaro>();
		List<Posicion> listaPosiciones = new LinkedList<Posicion>();
		Pajaro pajaro;
		while (ite.hasNext()) {
			pajaro = ite.next();
			listaPosiciones.add(pajaro.getPos());
			listaEstados.add(pajaro.getEstado());
		}
		info.setListaEstados(listaEstados);
		info.setListaPosiciones(listaPosiciones);
		return info;
	}
	

}
