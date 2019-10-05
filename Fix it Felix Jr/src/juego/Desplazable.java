package juego;

public abstract class Desplazable {
	protected int velocidad;
	protected Posicion pos;
	
	public double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public Posicion getPos() {
		return pos;
	}
	public void setPos(Posicion pos) {
		this.pos = pos;
	}
	public abstract Direcciones obtenerDireccion();
	public abstract void avanzar();

}
