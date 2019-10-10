package juego;

public class Comun extends Ventana {
	
	private Panel[] paneles= new Panel[2];	
	@Override
	public boolean puedoGenerarPastel() {
		return (paneles[0].getEstado()==EstadoPanel.ROTO);
	}
	
	public Comun (int nroFila, int nroCol, boolean tieneMacetero, boolean tieneMoldura, 
			EstadoPanel inferior, EstadoPanel superior) {
		super(nroFila, nroCol, tieneMacetero, tieneMoldura);
		paneles[0]= new Panel(inferior);
		paneles[1]= new Panel(superior);
		
	}

	@Override
	public void repararse() {
		if (paneles[0].getEstado()!=EstadoPanel.SANO) {
			paneles[0].repararse();
		}
		else if (paneles[1].getEstado()!=EstadoPanel.SANO)  
			paneles[1].repararse();
	}

	@Override
	public boolean estoySana(){
		return (paneles[0].getEstado()==EstadoPanel.SANO &&
				paneles[1].getEstado()==EstadoPanel.SANO);
	}

	@Override
	public boolean puedeAtravesarseLateralmente() {
		return true;
	}

	

}
