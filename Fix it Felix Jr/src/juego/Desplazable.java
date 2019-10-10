package juego;
/**
 * Interface que implementaran las clases capaces de desplazarse
 * teniendo una posicion, direccion y velocidad
 * @author Lucas y Renzo
 *
 */
public interface Desplazable {
	
	public Posicion getPos();
	public void setPos(Posicion pos);
	public Direcciones obtenerDireccion();
	public boolean avanzar();

}
