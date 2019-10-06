package juego;

public class Ladrillo implements Impactable, Desplazable{
	
	private Direcciones direccion= Direcciones.ABAJO;
	private Posicion pos;
	//velocidad= fijar numero
	
	public Ladrillo (Posicion pos) {
		this.pos=pos;
	}
	
	public Posicion getPos() {
		return this.pos;
	}
	
	public void setPos(Posicion pos) {
		this.pos=pos;
	}
	
	public boolean bajar() {
		pos.disminuirPosY();
		return (pos.getPosY()<0);
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

	@Override
	public Direcciones obtenerDireccion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void avanzar() {
		// TODO Auto-generated method stub
		
	}
	
}
