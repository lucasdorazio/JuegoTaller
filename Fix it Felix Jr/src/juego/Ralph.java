package juego;

public class Ralph extends Desplazable {
	
	
	private static final int ladrillosPorTirada = 3;
	
	private int ladrillos;
	
	public double posX;

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

	public void setPosX(double posX) {
		this.posX = posX;
	}

	private Ralph() {
		this.ladrillos=40;
		this.posX= 500.0;//cambiar valor
	}
	
	public Ralph getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Ralph();
		}
		return INSTANCE;
	}
	
	public void golpearEdif() {
		if (ladrillos > 3) {
			for (int i = 0; i < ladrillosPorTirada; i++) {
				Juego.generarLadrillo(new Posicion(pos.getPosX() + i * 15 - 15,pos.getPosY()));	 // Genero ladrillos a -15, 0 y 15 pixeles del centro de Ralph
				//comprobar si los ladrillos estan en una pos correcta
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

	@Override
	public Direcciones obtenerDireccion() {
		return null;
	}

	@Override
	public void avanzar() {
		for (int i=0;i<velocidad/5;i++) {
			//case
			pos.setPosX(pos.getPosX()+5);
			try {
				Thread.sleep(100);
			} catch (InterruptedException error) {
				// TODO Auto-generated catch block
				error.printStackTrace();
			}
		}
		

	}

}
