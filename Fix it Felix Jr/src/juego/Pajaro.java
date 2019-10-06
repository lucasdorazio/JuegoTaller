package juego;

public class Pajaro implements Impactable, Desplazable{
	
	private Direcciones direccion;
	private Posicion pos;
	private static final int FIN_DERECHO_MAPA=1000; //Revisar valor
	private static final int FIN_IZQUIERDO_MAPA=0;

	public Pajaro(Posicion pos, Direcciones dir) {
		this.pos=pos;
		this.direccion=dir;
	}
	
	@Override
	public void impactar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ventana devolverVentana() {
		// TODO Auto-generated method stub
		return null;
	}

	public Direcciones obtenerDireccion() {
		return this.direccion;
	}

	public Posicion getPos() {
		return this.pos;
	}

	public void setPos(Posicion pos) {
		this.pos=pos;
		
	}

	public boolean avanzar() {
		if (this.direccion == Direcciones.DERECHA) {
			pos.aumentarPosX();
			return (pos.getPosX()>FIN_DERECHO_MAPA);
		} else {
			pos.disminuirPosX();
			return (pos.getPosX()<FIN_IZQUIERDO_MAPA);
		}
	}
}
