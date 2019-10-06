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
		ventanaActual.repararse();
		Juego.comprobarSeccionLimpia(seccionActual);
	}

	public void recibirImpacto(Pajaro p) {
		vidas--;//falta que se reinicie la seccion, hay que guardarla
	}
	
	public void recibirImpacto (Pastel p) {
		
	}
	
	public void recibirImpacto(Ladrillo l) {
		if (!vulnerable) {
			
		}
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
	
	public void mover(Direcciones dir) {
		switch (dir) {
		case ARRIBA:
			if (!ventanaActual.tieneMoldura() && (ventanaActual.getNroFila())!=0)
				this.ventanaActual=seccionActual.getVentanas()[ventanaActual.getNroFila()-1][ventanaActual.getNroColumna()];
			break;
		case ABAJO:
			if (!ventanaActual.tieneMacetero() && (ventanaActual.getNroFila()!=2))
				this.ventanaActual=seccionActual.getVentanas()[ventanaActual.getNroFila()+1][ventanaActual.getNroColumna()];
		case IZQUIERDA:
			if (ventanaActual.puedeAtravesarseLateralmente() && (ventanaActual.getNroColumna()!=0))
				this.ventanaActual=seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()-1];
		case DERECHA:
			if ((ventanaActual.getNroColumna()!=4) && seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()+1].puedeAtravesarseLateralmente())
				this.ventanaActual=seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()+1];
		}
	}

}
