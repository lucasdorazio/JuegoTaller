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
		int posX=pos.getPosX();
		if (posY>=100 && posY<= 160) {
			m=0;
		} else {
			if (posY>=180 && posY<=240) {
				m=1;
			} else {
				if(posY>=260 && posY<=340) {
					m=2;
				}
			}
		}
		if (posX>=214 && posX<=252) {
			n=0;
		} else {
			if (posX>=266 && posX<=304) {
				n=1;
			} else {
				if(posX>=318 && posX<=356) {
					n=2;
				} else {
					if(posX>=370 && posX<=408) {
						n=3;
					} else {
						if(posX>=422 && posX<=460) {
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
		pos.aumentarPosY();
		return (pos.getPosY()>Juego.LIMITE_INFERIOR_MAPA);
	}

}
