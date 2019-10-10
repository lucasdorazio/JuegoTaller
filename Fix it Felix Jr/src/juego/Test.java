package juego;

public class Test {

	public static void main(String[] args) {
		Juego j = new Juego();
		int timer=0;
		while (!j.perdio()) {
			j.actualizar();
			Felix.getInstance().mover(Direcciones.ARRIBA);
			Felix.getInstance().mover(Direcciones.ARRIBA);
			Felix.getInstance().mover(Direcciones.ARRIBA);
//			Felix.getInstance().mover(Direcciones.DERECHA);
		}
		if (Felix.getInstance().getVidas()==0) System.out.println("felix se quedo sin vidas");
		else System.out.println("se quedaron sin tiempo");
	}

}
