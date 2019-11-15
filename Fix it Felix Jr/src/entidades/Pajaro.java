package entidades;

import edificio.Edificio;
import edificio.Ventana;
import juego.Juego;

/**
 * Clase que modela la entidad voladora que puede chocar a Felix
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Pajaro implements Impactable, Desplazable{
	
	private Direcciones direccion;
	
	private Posicion pos;

	public Pajaro(Posicion pos, Direcciones dir) {
		this.pos=pos;
		this.direccion=dir;
	}
	
	/**
	 * Devuelve la ventana en la que se encuentre el pajaro para determinar si choca o no con Felix
	 */
	@Override
	public Ventana devolverVentana() {
		int m=-1;
		int n=-1;
		int posY=pos.getPosY();
		int posX=pos.getPosX();//distancia entre el limite izquierdo y el comienzo de la seccion
		switch (posY) {
		case 110:
			m=0;
			break;
		case 100:
			m=1;
			break;
		case 280:
			m=2;
			break;
		default:
			break;
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

	@Override
	public Posicion getPos() {
		return this.pos;
	}

	@Override
	public void setPos(Posicion pos) {
		this.pos=pos;
		
	}

	@Override
	public boolean avanzar() {
		if (this.direccion == Direcciones.DERECHA) {
			pos.aumentarPosX();
													//System.out.println("Pajaro avanza a la derecha");
			return (pos.getPosX()>Juego.LIMITE_DERECHO_MAPA);
		} else {
			pos.disminuirPosX();
													//System.out.println("Pajaro avanza a la izquierda");
			return (pos.getPosX()<Juego.LIMITE_IZQUIERDO_MAPA);
		}
	}

}
