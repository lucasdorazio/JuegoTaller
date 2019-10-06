package juego;

public class Ladrillo implements Impactable, Desplazable{
	
	private Direcciones direccion;
	
	private Posicion pos;
	
	public Ladrillo (Posicion pos) {
		this.pos=pos;
		this.direccion= Direcciones.ABAJO;
	}
	
	public Posicion getPos() {
		return this.pos;
	}
	
	public void setPos(Posicion pos) {
		this.pos=pos;
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

	public boolean avanzar() {
		pos.disminuirPosY();
		return (pos.getPosY()<0);
	}
}
