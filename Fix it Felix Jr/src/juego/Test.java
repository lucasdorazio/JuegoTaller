package juego;

public class Test {

	public static void main(String[] args) {
		Juego j = new Juego();
		Seccion s=Edificio.getInstance().getSecciones()[0];
		//imprimirSeccion(s);
		while (!j.perdio()) {
			j.actualizar();
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
