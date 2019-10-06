package juego;

public interface Desplazable {
	
	public Posicion getPos();
	public void setPos(Posicion pos);
	public Direcciones obtenerDireccion();
	public boolean avanzar();

}
