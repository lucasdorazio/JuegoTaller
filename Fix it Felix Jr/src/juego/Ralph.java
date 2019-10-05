package juego;

public class Ralph extends Desplazable {
	private final int ladrillosPorTirada = 3;//static?
	private int ladrillos = 40;

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
			case
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
