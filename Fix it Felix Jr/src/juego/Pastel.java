package juego;

public class Pastel implements Impactable {

	private int tiempoDeVida;
	
	private int timer;
	
	private Ventana ventanaActual;
	
	public Pastel(Ventana v) {
		this.ventanaActual=v;
		this.tiempoDeVida=20;
		this.timer=0;
	}
	
	@Override
	public void impactar() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean disminuirTiempoDeVida() {
		timer++;
		if (timer>10000) {
			tiempoDeVida--;
			timer=0;
		}
		return (tiempoDeVida==0);
	}

	public Ventana devolverVentana() {
		return this.ventanaActual;
	}
}
