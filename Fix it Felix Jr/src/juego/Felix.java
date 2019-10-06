package juego;

public class Felix {
	
	private int vidas;
	
	private boolean vulnerable;
	
	private Ventana ventanaActual;
	
	private Seccion seccionActual;
	
	private static Felix INSTANCE;
	
	private Felix() {
		this.vidas=3;
		this.vulnerable=true; 
	}
	
	public static Felix getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Felix();
		}
		return INSTANCE;
	}
	
public void reparar() {
		//Edificio.getInstance().getSecciones()[]
	}

	
	
	public int getVidas() {
		return vidas;
	}

	public boolean isVulnerable() {
		return vulnerable;
	}

	public Ventana getVentanaActual() {
		return ventanaActual;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public void setVulnerable(boolean vulnerable) {
		this.vulnerable = vulnerable;
	}

	public void setVentanaActual(Ventana ventanaActual) {
		this.ventanaActual = ventanaActual;
	}

	public Seccion getSeccionActual() {
		return seccionActual;
	}

	public void setSeccionActual(Seccion seccionActual) {
		this.seccionActual = seccionActual;
	}

}
