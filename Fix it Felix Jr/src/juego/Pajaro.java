package juego;

public class Pajaro implements Impactable, Desplazable{
	
	private Direcciones direccion;
	
	private Posicion pos;

	public Pajaro(Posicion pos, Direcciones dir) {
		this.pos=pos;
		this.direccion=dir;
	}
	

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
		if (m!=-1 && n!=-1) return Edificio.getInstance().getSecciones()[Juego.getNroSeccion()].getVentanas()[m][n];
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
			return (pos.getPosX()>Juego.getLimiteDerechoMapa());
		} else {
			pos.disminuirPosX();
			return (pos.getPosX()<Juego.getLimiteIzquierdoMapa());
		}
	}
}
