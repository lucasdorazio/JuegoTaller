package entidades;

/**
 * Clase que modela la posici�n dentro del mapa a trav�s de una coordenada en X y una coordenada en Y
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Posicion {
	private int posX;
	
	private int posY;
	
	public Posicion(int posX, int posY) {
		this.posX=posX;
		this.posY=posY;
	}
	
	public Posicion getPoscion() {
		return this;
	}
	
	public void aumentarPosX() {
		posX++;
	}
	
	public void aumentarPosY() {
		posY++;
	}
	
	public void disminuirPosY() {
		posY--;
	}
	
	public void disminuirPosX() {
		posX--;
	}
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
}