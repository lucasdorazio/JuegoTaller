package entidades;

import edificio.Seccion;
import edificio.Ventana;
import juego.Juego;

/**
 * Modela al personaje y jugador Felix, sus acciones y movimientos posibles
 * @author Lucas y Renzo
 *
 */
public class Felix{
	
	private static final int tiempoInvulnerabilidad=2;
	
	private int vidas;
	private boolean vulnerable;
	private int timerInvulnerabilidad;
	
	private Ventana ventanaActual;
	
	private Seccion seccionActual;
	
	private Posicion pos;
	
	private static Felix INSTANCE;
	
	private Felix() {
		this.vidas=3;
		this.vulnerable=true;
		this.timerInvulnerabilidad=0;
	}
	/**
	 * 
	 * @return INSTANCIA de Felix, de acuerdo al patron de diseño Singleton
	 */
	public static Felix getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Felix();
		}
		return INSTANCE;
	}
	/**
	 * Repara (intenta cambiar el estado de los paneles a SANO) la ventana
	 * sobre la cual se encuentra posicionado
	 */
	public void reparar() {
		boolean sanaAntes=ventanaActual.estoySana();
		ventanaActual.repararse();
		System.out.println("Felix da un martillazo");
		if (!sanaAntes && ventanaActual.estoySana()) {
			Juego.getInstance().getJugador().sumarPuntaje(100);
			seccionActual.disminuirVentanasRestantes();
			Juego.getInstance().comprobarSeccionLimpia(seccionActual);
			System.out.println("...y arregló una ventana!");
		}
	}
	
	public void recibirImpactoPastel () {
		vulnerable=false;
		Juego.getInstance().getJugador().sumarPuntaje(500);
		System.out.println("Felix se comió un pastel y ahora es invulnerable!");
	}
	
	public void recibirImpactoLadrillo() {
		if (vulnerable) {
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
	
	/*public void moverViejo(Direcciones dir) {
		switch (dir) {
		case ARRIBA:
			if ((!ventanaActual.tieneMoldura()) && (ventanaActual.getNroFila()!=0) &&
					!(seccionActual.getVentanas()[ventanaActual.getNroFila()-1][ventanaActual.getNroColumna()].tieneMacetero()))
				this.ventanaActual=seccionActual.getVentanas()[ventanaActual.getNroFila()-1][ventanaActual.getNroColumna()];
			System.out.println("Felix se desplaza hacia arriba y queda en la ventana ["+ventanaActual.getNroFila()+"]["+ventanaActual.getNroColumna()+"]");
			break;
		case ABAJO:
			if ((!ventanaActual.tieneMacetero()) && (ventanaActual.getNroFila()!=2) &&
					!(seccionActual.getVentanas()[ventanaActual.getNroFila()+1][ventanaActual.getNroColumna()].tieneMoldura()))
				this.ventanaActual=seccionActual.getVentanas()[ventanaActual.getNroFila()+1][ventanaActual.getNroColumna()];
			System.out.println("Felix se desplaza hacia abajo y queda en la ventana ["+ventanaActual.getNroFila()+"]["+ventanaActual.getNroColumna()+"]");
			break;
		case IZQUIERDA:
			if ((ventanaActual.puedeAtravesarseLateralmente()) &&(ventanaActual.getNroColumna()!=0) &&
					(seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()-1].puedeAtravesarseLateralmente()))
				this.ventanaActual=seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()-1];
			System.out.println("Felix se desplaza hacia la izquierda y queda en la ventana ["+ventanaActual.getNroFila()+"]["+ventanaActual.getNroColumna()+"]");
			break;
		case DERECHA:
			if ((ventanaActual.puedeAtravesarseLateralmente()) && (ventanaActual.getNroColumna()!=4) &&
					(seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()+1].puedeAtravesarseLateralmente()))
				this.ventanaActual=seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()+1];
			System.out.println("Felix se desplaza hacia la derecha y queda en la ventana ["+ventanaActual.getNroFila()+"]["+ventanaActual.getNroColumna()+"]");
			break;
		}
	}*/
	
	public void mover(Direcciones dir) {
		Ventana proximaVentana;
		if (ventanaActual.puedePasar(dir)) {
			switch (dir) {
			case DERECHA:
				proximaVentana = seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()+ 1];
				if (proximaVentana.puedePasar(Direcciones.IZQUIERDA))
					ventanaActual = proximaVentana;
				System.out.println("Felix se desplaza hacia la derecha y queda en la ventana ["
						+ ventanaActual.getNroFila() + "][" + ventanaActual.getNroColumna() + "]");
				break;
			case IZQUIERDA:
				proximaVentana = seccionActual.getVentanas()[ventanaActual.getNroFila()][ventanaActual.getNroColumna()- 1];
				if (proximaVentana.puedePasar(Direcciones.IZQUIERDA))
					ventanaActual = proximaVentana;
				System.out.println("Felix se desplaza hacia la izquierda y queda en la ventana ["
						+ ventanaActual.getNroFila() + "][" + ventanaActual.getNroColumna() + "]");
				break;
			case ARRIBA:
				proximaVentana = seccionActual.getVentanas()[ventanaActual.getNroFila() - 1][ventanaActual.getNroColumna()];
				if (proximaVentana.puedePasar(Direcciones.ABAJO))
					ventanaActual = proximaVentana;
				System.out.println("Felix se desplaza hacia arriba y queda en la ventana [" + ventanaActual.getNroFila()
						+ "][" + ventanaActual.getNroColumna() + "]");
				break;
			case ABAJO:
				proximaVentana = seccionActual.getVentanas()[ventanaActual.getNroFila() + 1][ventanaActual.getNroColumna()];
				if (proximaVentana.puedePasar(Direcciones.ARRIBA))
					ventanaActual = proximaVentana;
				System.out.println("Felix se desplaza hacia abajo y queda en la ventana [" + ventanaActual.getNroFila()
						+ "][" + ventanaActual.getNroColumna() + "]");
				break;
			}
		}
	}
	
	public void actualizarInvulnerabilidad() {
		if (this.vulnerable == false) {
			timerInvulnerabilidad++;
			if (timerInvulnerabilidad > tiempoInvulnerabilidad * Juego.CONST_TIEMPO) {
				this.vulnerable = true;
				timerInvulnerabilidad = 0;
			}
		}
	}
	/*if (posY>=33 && posY<= 102) {
		m=2;//33 es la base... 
	} else {
		if (posY>=135 && posY<=205) {
			m=1;//170
		} else {
			if(posY>=238 && posY<=307) {
				m=0;//272
			}
		}
	}
	*/
	// m filas---> determinan y, n columnas---> determinan x
	public Posicion getPos() {
		int posX;
		int posY;
		switch (getVentanaActual().getNroColumna()) {
		case 0:
			
			break;
		case 1:
			break;
		case 2: 
			break;
			
		}
		return new Posicion(posX, posY);
	}


	
}
