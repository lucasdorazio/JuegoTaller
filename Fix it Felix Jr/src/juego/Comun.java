package juego;

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

	/**
	 * retorna verdadero si Felix
	 * puede moverse hacia y desde la ventana desde izquierda y derecha
	 */
	@Override
	public boolean puedeAtravesarseLateralmente() {
		return true;
	}
}