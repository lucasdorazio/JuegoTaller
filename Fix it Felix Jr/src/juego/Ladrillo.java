package juego;

public class Ladrillo extends Desplazable implements Impactable{
	
	private Direcciones direccion= Direcciones.ABAJO;
	//velocidad= fijar numero
	
	public Ladrillo (Posicion pos) {
		this.pos=pos;
	}
	
	public void impactar(){}
	
}
