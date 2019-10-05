package juego;

public class Ladrillo extends Desplazable implements Impactable{
	
	private Direcciones direccion= Direcciones.ABAJO;
	//velocidad= fijar numero
	
	public Ladrillo (Posicion pos) {
		this.pos=pos;
	}
	
	public void impactar(){}

	@Override
	public void Impactar() {
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
