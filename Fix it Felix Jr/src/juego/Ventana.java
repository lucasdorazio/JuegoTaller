package juego;

import taller2.modelo.Dibujable;
import taller2.modelo.InformacionDibujable;

public abstract class Ventana implements Dibujable{
	
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
	
	public InformacionDibujable getInformacionDibujable() {
		int x=180+21+(42+21)*this.getNroColumna();
		int y=42+(70+320)*(3-this.nroFila);
		char c;
		if (this.estoySana()) {
			c='_';
		} else {
			c='X';
		}
		return new InformacionDibujable(x, y, c);
	}
	

}
