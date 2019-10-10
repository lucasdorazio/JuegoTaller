package juego;

/**
 * La clase comun representa a las ventanas corrientes del edificio
 * @author Lucas & Renzo
 * @version 1.0
 */
public class Comun extends Ventana {
	
	private Panel[] paneles= new Panel[2];	
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
	 * Metodo repararse() repara un panel en caso de estar dañado
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
	 * Metodo estoySana() retorna un booleano en verdadero si el estado
	 * de ambos paneles de la ventana es SANO
	 */
	@Override
	public boolean estoySana(){
		return (paneles[0].getEstado()==EstadoPanel.SANO &&
				paneles[1].getEstado()==EstadoPanel.SANO);
	}

	/**
	 * 
	 */
	@Override
	public boolean puedeAtravesarseLateralmente() {
		return true;
	}

	public char queSoy() {
		return 'C';
	}

	
	public String estadoPaneles() {
		String cad="Paneles: ";
		for (int i=0;i<this.paneles.length;i++) {
			switch (this.paneles[i].getEstado()) {
			case MEDIO_ROTO: cad=cad + "m, ";break;
			case ROTO: cad=cad + "r, ";break;
			case SANO: cad=cad + "s, ";break;
			}
		}
		return cad;
	}
}
