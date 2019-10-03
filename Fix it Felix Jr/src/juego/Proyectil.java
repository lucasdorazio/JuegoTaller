package juego;

public class Proyectil extends Entidad {

	private double velocidad;
	
	public Direcciones obtenerDireccion() {
		return Direcciones.ARRIBA;
	}
	
	public void impactar() {}
	
}
