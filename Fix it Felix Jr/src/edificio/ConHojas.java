package edificio;
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
	 * 	felix no será accesible lateralmente
	 */
	public ConHojas(int nroFila, int nroCol, boolean tieneMacetero, boolean tieneMoldura,
			EstadoPanel inferior, EstadoPanel superior, boolean abierta) {
		super(nroFila, nroCol, tieneMacetero, tieneMoldura);
		this.abierta=abierta;
		paneles[0]= new Panel(inferior);
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
	public boolean estoySana() {
		return (paneles[0].getEstado()==EstadoPanel.SANO &&
				paneles[1].getEstado()==EstadoPanel.SANO);
	}
	
	public boolean getAbierta() {
		return this.abierta;
	}

	/**
	 * retorna verdadero si Felix
	 * puede moverse hacia y desde la ventana desde izquierda y derecha
	 */
	@Override
	public boolean puedeAtravesarseLateralmente() {
		// TODO Auto-generated method stub
		return (!abierta);
	}
}
