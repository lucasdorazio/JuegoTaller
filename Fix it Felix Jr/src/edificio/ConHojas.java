package edificio;

import entidades.Direcciones;
import excepciones.NotAllowedMovementException;

/**
 * La clase ConHojas representa las ventanas de dos paneles con hojas 
 * ya sean abiertas o cerradas
 * @author Lucas
 *
 */
public class ConHojas extends Ventana {
	
	private boolean abierta;
	
	private Panel[] paneles= new Panel[2];	
	/**
	 * 
	 * @param nroFila recibe el numero entero de fila que le correspondera a la ventana
	 * @param nroCol recibe el numero entero de columna que le correspondera a la ventana
	 * @param tieneMacetero si esta en verdadero, la ventana tendra macetero
	 * @param tieneMoldura si esta en verdadero, la ventana tendra moldura
	 * @param inferior recibe el estado del panel inferior de la ventana
	 * @param superior recibe el estado del panel inferior de la ventana
	 * @param abierta define si la ventana esta abierta o no. En caso de estarlo, 
	 * 	felix no ser� accesible lateralmente
	 */
	public ConHojas(int nroFila, int nroCol, boolean tieneMacetero, boolean tieneMoldura,
			EstadoPanel inferior, EstadoPanel superior, boolean abierta) {
		super(nroFila, nroCol, tieneMacetero, tieneMoldura);
		this.abierta = abierta;
		if (abierta)
			tipo = TipoVentana.CONHOJAS;
		else
			tipo = TipoVentana.CONHOJASCERRADA;
		paneles[0] = new Panel(inferior);
		paneles[1]= new Panel(superior);
	}

	/**
	 * retorna verdadero si un pastel puede crearse en esta ventana
	 * Podra crearse si el panel inferior esta completamente roto
	 */
	@Override
	public boolean puedoGenerarPastel() {
		return false;
	}

	/**
	 * cambia el estado del panel a SANO en caso de estar da�ado
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
	public boolean estoySana() {
		return (paneles[0].getEstado()==EstadoPanel.SANO &&
				paneles[1].getEstado()==EstadoPanel.SANO);
	}
	
	public boolean getAbierta() {
		return this.abierta;
	}

	@Override
	public void puedePasar(Direcciones dir) {
		switch (dir) {
		case DERECHA:
			if (getNroColumna() == 4) throw new NotAllowedMovementException(true);
			else if (abierta) throw new NotAllowedMovementException(false);
			break;
		case IZQUIERDA:
			if (getNroColumna() == 0) throw new NotAllowedMovementException(true);
			else if (abierta) throw new NotAllowedMovementException(false);
			break;
		case ARRIBA:
			if (getNroFila()== 0) throw new NotAllowedMovementException(true);
			else if (tieneMoldura()) throw new NotAllowedMovementException(false);
			break;
		case ABAJO:
			if (getNroFila()== 2) throw new NotAllowedMovementException(true);
			else if (tieneMacetero()) throw new NotAllowedMovementException(false);
			break;
		}
	}

	@Override
	public EstadoPanel[] getEstadoPaneles() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
