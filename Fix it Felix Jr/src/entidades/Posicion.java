package entidades;

/**
 * Clase que modela la posición dentro del mapa a través de una coordenada en X y una coordenada en Y
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
	public void aumentarPosX(int pixeles) {
		posX+= pixeles;
	}
	
	public void aumentarPosY(int pixeles) {
		posY+= pixeles;
	}
	
	public void disminuirPosY(int pixeles) {
		posY-= pixeles;
	}
	
	public void disminuirPosX(int pixeles) {
		posX-= pixeles;
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
