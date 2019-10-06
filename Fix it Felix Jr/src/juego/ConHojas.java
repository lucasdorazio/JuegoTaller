package juego;

public class ConHojas extends Ventana {
	
	private Panel[] paneles= new Panel[2];	
	
	public ConHojas(int nroFila, int nroCol, boolean tieneMacetero, boolean tieneMoldura,
			EstadoPanel inferior, EstadoPanel superior, boolean abierta) {
		super(nroFila, nroCol, tieneMacetero, tieneMoldura);
		this.abierta=abierta;
		paneles[0]= new Panel(inferior);
		paneles[1]= new Panel(superior);
	}

	private boolean abierta;
	
	
	@Override
	public boolean puedoGenerarPastel() {
		// TODO Auto-generated method stub
		return false;
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
	public boolean estoySana() {
		return (paneles[0].getEstado()==EstadoPanel.SANO &&
				paneles[1].getEstado()==EstadoPanel.SANO);
	}
	
	public boolean getAbierta() {
		return this.abierta;
	}

	@Override
	public boolean puedeAtravesarseLateralmente() {
		// TODO Auto-generated method stub
		return (!abierta);
	}

}
