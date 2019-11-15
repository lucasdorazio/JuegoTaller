package edificio;

import entidades.Direcciones;

/**
 * La clase comun representa a las ventanas corrientes del edificio
 * @author Lucas & Renzo
 * @version 1.0
 */
public class Comun extends Ventana {
	
	private Panel[] paneles= new Panel[2];	
	
	/**
	 * retorna verdadero si un pastel puede crearse en esta ventana
	 * Podra crearse si el panel inferior esta completamente roto
	 */
	@Override
	public boolean puedoGenerarPastel() {
		return (paneles[0].getEstado()==EstadoPanel.ROTO);
	}
	/**
	 * 
	 * @param nroFila recibe el numero entero de fila que le correspondera a la ventana
	 * @param nroCol recibe el numero entero de columna que le correspondera a la ventana
	 * @param tieneMacetero si esta en verdadero, la ventana tendra macetero
	 * @param tieneMoldura si esta en verdadero, la ventana tendra moldura
	 * @param inferior recibe el estado del panel inferior de la ventana
	 * @param superior recibe el estado del panel inferior de la ventana
	 */
	public Comun (int nroFila, int nroCol, boolean tieneMacetero, boolean tieneMoldura, 
			EstadoPanel inferior, EstadoPanel superior) {
		super(nroFila, nroCol, tieneMacetero, tieneMoldura);
		tipo= TipoVentana.COMUN;
		paneles[0]= new Panel(inferior);
		paneles[1]= new Panel(superior);
		
	}

	/**
	 * cambia el estado del panel a SANO en caso de estar dañado
	 * Se repara primero el inferior
	 */
	@Override
	public void repararse() {
		if (paneles[0].getEstado()!=EstadoPanel.SANO) {
			paneles[0].repararse();
		}
		else if (paneles[1].getEstado()!=EstadoPanel.SANO)  
			paneles[1].repararse();
	}

	/**
	 * retorna verdadero si el estado
	 * de ambos paneles de la ventana es SANO
	 */
	@Override
	public boolean estoySana(){
		return (paneles[0].getEstado()==EstadoPanel.SANO &&
				paneles[1].getEstado()==EstadoPanel.SANO);
	}
	
	@Override
	public boolean puedePasar(Direcciones dir) {
		boolean puede=false;
		switch (dir) {
		case DERECHA:
			if (getNroColumna() != 4) puede=true;
			break;
		case IZQUIERDA:
			if (getNroColumna() != 0) puede=true;
			break;
		case ARRIBA:
			if ((getNroFila() != 0) && (!tieneMoldura())) puede=true;
			break;
		case ABAJO:
			if ((getNroFila() != 2) && (!tieneMacetero())) puede=true;
			break;
		}
		return puede;
	}
	@Override
	public EstadoPanel[] getEstadoPaneles() {
		EstadoPanel[] estados = new EstadoPanel[2];
		estados[0]=this.paneles[0].getEstado();
		estados[1]=this.paneles[1].getEstado();
		return estados;
	}
	
	
}