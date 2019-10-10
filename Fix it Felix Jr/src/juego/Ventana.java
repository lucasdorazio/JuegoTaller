package juego;

public abstract class Ventana{
	
	private int nroFila;
	private int nroColumna;
	private boolean tieneMacetero;
	private boolean tieneMoldura;
	
	public Ventana (int nroFila, int nroCol, boolean tieneMacetero, boolean tieneMoldura) {
		this.nroColumna=nroCol;
		this.nroFila=nroFila;
		this.tieneMacetero=tieneMacetero;
		this.tieneMoldura=tieneMoldura;
	}
	
	public abstract char queSoy();
	public abstract String estadoPaneles();
	public abstract boolean puedoGenerarPastel();
	
	public abstract void repararse();
	
	public abstract boolean estoySana();
	
	public abstract boolean puedeAtravesarseLateralmente();
	
	public boolean equals(Ventana v) {
		return (v.getNroColumna()==this.getNroColumna() && v.getNroFila()==this.getNroFila());
	}

	public int getNroFila() {
		return nroFila;
	}

	public int getNroColumna() {
		return nroColumna;
	}

	public boolean tieneMacetero() {
		return tieneMacetero;
	}

	public void setTieneMacetero(boolean tieneMacetero) {
		this.tieneMacetero = tieneMacetero;
	}

	public boolean tieneMoldura() {
		return tieneMoldura;
	}

	public void setTieneMoldura(boolean tieneMoldura) {
		this.tieneMoldura = tieneMoldura;
	}
	

}
