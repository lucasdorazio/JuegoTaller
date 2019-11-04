package entidades;

import edificio.Edificio;
import edificio.Ventana;
import juego.Juego;

/**
 * Clase que modela las rocas lanzadas por Ralph para molestar a Felix
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Ladrillo implements Impactable, Desplazable{
	
	private Direcciones direccion;
	
	private Posicion pos;
	
	/**
	 * Inicializa valores por defecto de los ladrillos
	 * @param pos indica en qué posicion se encontrará el nuevo ladrillo
	 */
	public Ladrillo (Posicion pos) {
		this.pos=pos;
		this.direccion= Direcciones.ABAJO;
	}
	
	@Override
	public Posicion getPos() {
		return this.pos;
	}
	
	@Override
	public void setPos(Posicion pos) {
		this.pos=pos;
	}

	/**
	 * Devuelve la ventana en la que se encuentra el ladrillo para determinar si choca o no con Felix
	 */
	@Override
	public Ventana devolverVentana() {
		int m=-1;
		int n=-1;
		int posY=pos.getPosY();
		int posX=pos.getPosX()-180;//distancia entre el limite izquierdo y el comienzo de la seccion
		if (posY>=33 && posY<= 102) {
			m=2;
		} else {
			if (posY>=135 && posY<=205) {
				m=1;
			} else {
				if(posY>=238 && posY<=307) {
					m=0;
				}
			}
		}
		if (posX>=22 && posX<=63) {
			n=0;
		} else {
			if (posX>=85 && posX<=127) {
				n=1;
			} else {
				if(posX>=149 && posX<=191) {
					n=2;
				} else {
					if(posX>=212 && posX<=254) {
						n=3;
					} else {
						if(posX>=276 && posX<=318) {
							n=4;
						}
					}
				}
			}
		}
		if (m!=-1 && n!=-1) return Edificio.getInstance().getSecciones()[Juego.getInstance().getNroSeccion()].getVentanas()[m][n];
		else return null;
	}

	@Override
	public Direcciones obtenerDireccion() {
		return this.direccion;
	}

	/**
	 * @return true cuando el ladrillo llega al limite inferior del mapa
	 */
	@Override
	public boolean avanzar() {
		pos.disminuirPosY();
		return (pos.getPosY()<0);
	}

}
