package juego;
import taller2.modelo.Dibujable;
import taller2.modelo.InformacionDibujable;

public class Felix implements Dibujable{
	
	private static final int tiempoInvulnerabilidad=2;
	private static final double CONST_TIEMPO = 10000;
	
	private int vidas;
	private boolean vulnerable;
	private int timerInvulnerabilidad;
	
	private Ventana ventanaActual;
	
	private Seccion seccionActual;
	
	private static Felix INSTANCE;
	
	private Felix() {
		this.vidas=3;
		this.vulnerable=true;
		this.timerInvulnerabilidad=0;
	}
	
	public static Felix getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Felix();
		}
		return INSTANCE;
	}
	
	public void reparar() {
		boolean sanaAntes=ventanaActual.estoySana();
		ventanaActual.repararse();
		if (!sanaAntes && ventanaActual.estoySana()) {
			Juego.getJugador().sumarPuntaje(100);
			seccionActual.disminuirVentanasRestantes();
			Juego.comprobarSeccionLimpia(seccionActual);
		}
	}
	
	public void recibirImpactoPastel () {
		vulnerable=false;
		Juego.getJugador().sumarPuntaje(500);
	}
	
	public void recibirImpactoLadrillo() {
		if (!vulnerable) {
			vidas--;
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
			if ((!ventanaActual.tieneMoldura()) && (ventanaActual.getNroFila()!=0) &&
					(seccionActual.getVentanas()[ventanaActual.getNroFila()-1][ventanaActual.getNroColumna()].tieneMacetero()))
				this.ventanaActual=seccionActual.getVentanas()[ventanaActual.getNroFila()-1][ventanaActual.getNroColumna()];
			break;
		case ABAJO:
			if ((!ventanaActual.tieneMacetero()) && (ventanaActual.getNroFila()!=2) &&
					(seccionActual.getVentanas()[ventanaActual.getNroFila()+1][ventanaActual.getNroColumna()].tieneMoldura()))
				this.ventanaActual=seccionActual.getVentanas()[ventanaActual.getNroFila()+1][ventanaActual.getNroColumna()];
		case IZQUIERDA:
			if ((ventanaActual.puedeAtravesarseLateralmente()) &&(ventanaActual.getNroColumna()!=0) &&
					(seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()-1].puedeAtravesarseLateralmente()))
				this.ventanaActual=seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()-1];
		case DERECHA:
			if ((ventanaActual.puedeAtravesarseLateralmente()) && (ventanaActual.getNroColumna()!=4) &&
					(seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()+1].puedeAtravesarseLateralmente()))
				this.ventanaActual=seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()+1];
		}
	}
	
	public void actualizarInvulnerabilidad() {
		if (this.vulnerable == false) {
			timerInvulnerabilidad++;
			if (timerInvulnerabilidad > tiempoInvulnerabilidad * CONST_TIEMPO) {
				this.vulnerable = true;
				timerInvulnerabilidad = 0;
			}
		}
	}

	public InformacionDibujable getInformacionDibujable() {
		int x=180+21+(42+21)*getVentanaActual().getNroColumna();
		int y=42+(70+320)*(3-getVentanaActual().getNroFila());
		char c='F';
		return new InformacionDibujable(x, y, c);
	}
	
}
