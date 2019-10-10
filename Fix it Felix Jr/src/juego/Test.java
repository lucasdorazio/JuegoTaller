package juego;

public class Test {

	public static void main(String[] args) {
		Juego j = new Juego();
		Seccion s=Edificio.getInstance().getSecciones()[0];
		imprimirSeccion(s);
		int timer=0;
		int random;
		Direcciones dir=null;
		while (!j.perdio()) {
			timer++;
			j.actualizar();
			//if (timer > 60000000) {
				random=(int) (Math.random()*4);
				System.out.println("random es " +random);
				switch (random){
				case 0: dir=Direcciones.ARRIBA;break;
				case 1: dir=Direcciones.ABAJO;break;
				case 2: dir=Direcciones.IZQUIERDA;break;
				case 3: dir=Direcciones.DERECHA;break;
				}
				Felix.getInstance().mover(dir);
				//Felix.getInstance().reparar();
				timer = 0;
			//}
		}
		if (Felix.getInstance().getVidas()==0) System.out.println("felix se quedo sin vidas");
		else System.out.println("se quedaron sin tiempo");
	}
	
	public static void imprimirSeccion (Seccion s) {
		Ventana v;
		for (int m=0;m<3;m++) {
			for (int n=0;n<5;n++) {
				v=s.getVentanas()[m][n];
				System.out.printf("%c: [%s]\t",v.queSoy(),v.estadoPaneles());
			}
			System.out.println(" ");
		}
	}
	
}
