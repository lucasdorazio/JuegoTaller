package juego;

public class Ralph /*extends Desplazable */{
	
	private static final int ladrillosPorTirada = 3;
	private int ladrillos;
	private int posX;
	private int velocidad;
	private static Ralph INSTANCE;
	
	
	public int getLadrillos() {
		return ladrillos;
	}

	public double getPosX() {
		return posX;
	}

	public void setLadrillos(int ladrillos) {
		this.ladrillos = ladrillos;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	private Ralph() {
		this.ladrillos=40;
		this.posX= 500;//cambiar valor
	}
	
	public static Ralph getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Ralph();
		}
		return INSTANCE;
	}
	
	public void golpearEdif() {
		if (ladrillos > ladrillosPorTirada) {
			for (int i = 0; i < ladrillosPorTirada; i++) {
				Juego.generarLadrillo(new Posicion(posX + i * 15 - 15,100));	 // Genero ladrillos a -15, 0 y 15 pixeles del centro de Ralph
				//comprobar si los ladrillos estan en una pos correcta
				//El 100 sería la posicino en Y de Ralph, ver si está bien o se la cambiamos
				ladrillos--;
				//Esperar 1 segundo
				try {
					Thread.sleep(1000);
				} catch (InterruptedException error) {
					error.printStackTrace();
				}
			}
		}
	}

	/*@Override
	public Direcciones obtenerDireccion() {
		return null;
	}*/

	
	public void avanzar(int cantPasos, Direcciones direccion) {
		for (int i=0;i<velocidad/5;i++) {
			//case
			posX+=5;
			try {
				Thread.sleep(100);
			} catch (InterruptedException error) {
				// TODO Auto-generated catch block
				error.printStackTrace();
			}
		}
		

	}

}
