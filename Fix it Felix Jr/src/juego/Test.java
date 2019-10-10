package juego;

public class Test {

	public static void main(String[] args) {
		Juego j = new Juego();
		Seccion s0=Edificio.getInstance().getSecciones()[0];
		imprimirSeccion(s0);
		/*System.out.println();
		Seccion s1=Edificio.getInstance().getSecciones()[1];
		imprimirSeccion(s1);
		System.out.println();
		Seccion s2=Edificio.getInstance().getSecciones()[2];
		imprimirSeccion(s2);*/
		int timer=0;
		int random;
		Direcciones dir= Direcciones.DERECHA;
		int cambio=0;
		//Direcciones dir=null;
		while (!j.perdio()) {
			timer++;
			j.actualizar();
			if (timer > 30000000) {
				Felix.getInstance().reparar();
				if (Felix.getInstance().getVentanaActual().estoySana()) {
					random=(int) (Math.random()*4);
					switch (random){
					case 0: dir=Direcciones.ARRIBA; break;
					case 1: dir=Direcciones.ABAJO; break;
					case 2: dir=Direcciones.IZQUIERDA; break;
					case 3: dir=Direcciones.DERECHA; break;
					}
					Felix.getInstance().mover(dir);
				}
				/*Felix.getInstance().reparar();
				if (Felix.getInstance().getVentanaActual().estoySana()) {
					Felix.getInstance().mover(dir);
					cambio++;
					if (cambio%4==0) {
						while (!Felix.getInstance().getVentanaActual().estoySana()) Felix.getInstance().reparar();
						Felix.getInstance().mover(Direcciones.ABAJO);
						if (dir==Direcciones.DERECHA) dir=Direcciones.IZQUIERDA;
						else dir=Direcciones.IZQUIERDA;
					}
				}*/
				//Felix.getInstance().mover(Direcciones.ABAJO);
				timer = 0;
			}
		}
		if (Felix.getInstance().getVidas()==0) System.out.println("felix se quedo sin vidas");
		else System.out.println("se quedaron sin tiempo");
	}
	
	public static void imprimirSeccion (Seccion s) {
		Ventana v;
		String cad;
		for (int m=0;m<3;m++) {
			for (int n=0;n<5;n++) {
				cad="";
				v=s.getVentanas()[m][n];
				System.out.printf("%c: [%s] ",v.queSoy(),v.estadoPaneles());
				if (v.tieneMacetero()) cad=cad+"_";
				if (v.tieneMoldura()) cad=cad+ "^^";
				System.out.printf("(%s)\t\t", cad);
			}
			System.out.println(" ");
		}
	}
	
}
