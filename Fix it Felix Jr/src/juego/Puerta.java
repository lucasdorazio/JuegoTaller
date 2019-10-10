package juego;

/**
 * Es la clase que modela la ventana de tipo puerta con 4 paneles donde comenzar� Felix
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Puerta extends Semicircular {

	private Panel[] paneles= new Panel[4];
	
	/**
	 * Inicializa los atributos b�sicos de una ventana e indica el estado de sus 4 paneles
	 */
	public Puerta(int nroFila, int nroCol, boolean tieneMacetero, boolean tieneMoldura,
			EstadoPanel panel0, EstadoPanel panel1, EstadoPanel panel2, EstadoPanel panel3) {
		super(nroFila, nroCol, tieneMacetero, tieneMoldura);
		paneles[0]= new Panel(panel0);
		paneles[1]= new Panel(panel1);
		paneles[2]= new Panel(panel2);
		paneles[3]= new Panel(panel3);
		
	}
	/**
	 * Indica la forma en que se reparan sus paneles
	 */
	@Override
	public void repararse() {
		boolean seReparo=false;
		int i=0;
		while (!seReparo && i<4) {
			if (paneles[i].getEstado()!=EstadoPanel.SANO) {
				paneles[i].repararse();
				seReparo=true;
				//los paneles de 0 y 1 abajo, 2 y 3 arriba
			}
			i++;
		}
	}
	
	/**
	 * Determina si la puerta est� sana o no seg�n el estado de sus 4 paneles
	 */
	@Override
	public boolean estoySana() {
		return (paneles[0].getEstado()==EstadoPanel.SANO &&
				paneles[1].getEstado()==EstadoPanel.SANO &&
				paneles[2].getEstado()==EstadoPanel.SANO &&
				paneles[3].getEstado()==EstadoPanel.SANO);
	}

	@Override
	public boolean puedeAtravesarseLateralmente() {
		return true;
	}
}
