package juego;

/**
 * Clase que modela los paneles componentes de las ventanas
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Panel {
	
	private int golpesRestantes;
	
	private EstadoPanel estado;
	
	public Panel (EstadoPanel estado) {
		this.estado=estado;
		if (estado==EstadoPanel.SANO) this.golpesRestantes=0;
		else this.golpesRestantes=2;
	}
	
	/**
	 * Forma de transformar un panel a estado sano
	 */
	public void repararse() {
		golpesRestantes--;
		if (golpesRestantes==0) {
			this.estado= EstadoPanel.SANO;
		}
	}

	public void setGolpesRestantes(int golpesRestantes) {
		this.golpesRestantes = golpesRestantes;
	}

	public EstadoPanel getEstado() {
		return estado;
	}

	public void setEstado(EstadoPanel estado) {
		this.estado = estado;
	}
}
