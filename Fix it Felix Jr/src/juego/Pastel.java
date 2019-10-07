package juego;

public class Pastel implements Impactable {

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
}
