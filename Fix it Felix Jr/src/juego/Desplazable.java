package juego;

public interface Desplazable {
	
	public Posicion getPos();
	public void setPos(Posicion pos);
	public abstract Direcciones obtenerDireccion();
	public abstract void avanzar();

}
