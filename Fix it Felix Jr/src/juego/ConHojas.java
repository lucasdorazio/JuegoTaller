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
		// TODO Auto-generated method stub

	}

	@Override
	public boolean estoySana() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean getAbierta() {
		return this.abierta;
	}

}
