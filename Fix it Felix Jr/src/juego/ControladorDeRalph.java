package juego;

public class ControladorDeRalph {
	private int tiempoDeGolpeo;				//Cada cuantos segundos Ralph golpea
	private int tiempoDeDesplazamiento;		//Cada cuantos segundos Ralph se mueve
	private static final int CANT_PASOS_MIN= 5;
	private static final int CANT_PASOS_MAX= 12;
	private boolean estaMoviendose;
	private boolean estaGolpeando;
	private static int timer=0;
	private Ralph ralph;
	
	
	public int calcularCantPasos() {
		return (int) (Math.random() * (CANT_PASOS_MIN-CANT_PASOS_MAX+1) + CANT_PASOS_MIN);
	}
	
	public void manejarRalph() {
		timer++;
		Direcciones dir;
		if (timer%(tiempoDeDesplazamiento*10000) == 0) {	//Timer se aumenta aproximadamente 10000 veces por segundo
			if ((int) (Math.random()*2)== 0) dir=Direcciones.DERECHA;
			else dir=Direcciones.IZQUIERDA;
			ralph.avanzar(calcularCantPasos(), dir);
		}
		if (timer%(tiempoDeGolpeo*10000) == 0){
			ralph.golpearEdif();
		}
		
	}
	
	
}
