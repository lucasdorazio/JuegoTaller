package juego;

import taller2.modelo.Dibujable;
import taller2.modelo.InformacionDibujable;

public class Pastel implements Impactable, Dibujable {

	private int tiempoDeVida;
	private static final int CONST_TIEMPO=10000;	//Cantidad de llamadas al método por segundo
	
	private int timer;
	
	private Ventana ventanaActual;
	
	public Pastel(Ventana v) {
		this.ventanaActual=v;
		this.tiempoDeVida=20;
		this.timer=0;
	}
	

	public boolean disminuirTiempoDeVida() {
		timer++;
		if (timer>CONST_TIEMPO) {
			tiempoDeVida--;
			timer=0;
		}
		return (tiempoDeVida==0);
	}
	
	@Override
	public Ventana devolverVentana() {
		return this.ventanaActual;
	}


	@Override
	public InformacionDibujable getInformacionDibujable() {
		int x=180+21+(42+21)*ventanaActual.getNroColumna();
		int y=42+(70+320)*(3-ventanaActual.getNroFila());
		char c='.';
		return new InformacionDibujable(x, y, c);
	}
}
