package edificio;

import entidades.Direcciones;
import excepciones.NotAllowedMovementException;

/**
 * La clase Ventana será de la extienden todas los tipos de ventanas del edificio
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */

public abstract class Ventana implements Cloneable{
	
	private int nroFila;
	private int nroColumna;
	private boolean tieneMacetero;
	private boolean tieneMoldura;
	protected TipoVentana tipo;
	
	/**
	 * Constructor de ventana, inicializa sus atributos
	 * @param nroFila indica la posicion de la fila dentro de la matriz de ventanas que posee cada sección (va de 0 a 2)
	 * @param nroCol indica la posición de la columna dentro de la matriz de ventanas que posee cada sección (va de 0 a 4)
	 * @param tieneMacetero indica si una ventana tiene macetero y por ende si se puede atravesar verticalmente
	 * @param tieneMoldura indica si una ventana tiene moldura y por ende si se puede atravesar verticalmente
	 */
	public Ventana (int nroFila, int nroCol, boolean tieneMacetero, boolean tieneMoldura) {
		this.nroColumna=nroCol;
		this.nroFila=nroFila;
		this.tieneMacetero=tieneMacetero;
		this.tieneMoldura=tieneMoldura;
	}
	
	/**
	 * @return si una determinada ventana es capaz de poseer un pastel
	 */
	public abstract boolean puedoGenerarPastel();
	
	/**
	 * Implementación de la forma de reparar sus paneles para cada tipo de ventana
	 */
	public abstract void repararse();
	
	/**
	 * @return si una ventana tiene todos sus paneles sanos o no
	 */
	public abstract boolean estoySana();
	
	/**
	 * 
	 * @param dir
	 * @return si una ventana puede atravesarse en una determinada direccion, es necesario para el movimiento de Felix
	 */
	public abstract void puedePasar (Direcciones dir) throws NotAllowedMovementException;
	
	/**
	 * Determino si una ventana es o no igual a la otra ventana @param v
	 * @return si sus numeros de posicion en la matriz son los mismos
	 */
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
	
	public abstract EstadoPanel[] getEstadoPaneles();

	public TipoVentana getTipo() {
		return tipo;
	}
	
	public Ventana clone() {
		Ventana v;
		switch (tipo) {
		case COMUN:
			v= new Comun(nroFila, nroColumna, tieneMacetero, tieneMoldura, getEstadoPaneles()[0], getEstadoPaneles()[1]);
			break;
		case CONHOJAS:
			v= new ConHojas(nroFila, nroColumna, tieneMacetero, tieneMoldura, EstadoPanel.SANO, EstadoPanel.SANO, true);
			break;
		case CONHOJASCERRADA:
			v= new ConHojas(nroFila, nroColumna, tieneMacetero, tieneMoldura, EstadoPanel.SANO, EstadoPanel.SANO, false);
			break;
		case PUERTA:
			v= new Puerta(nroFila, nroColumna, tieneMacetero, tieneMoldura, getEstadoPaneles()[0], getEstadoPaneles()[1],
					getEstadoPaneles()[2], getEstadoPaneles()[3]);
			break;
		case SEMICIRCULAR:
			v= new SemicircularSuperior(nroFila, nroColumna, tieneMacetero, tieneMoldura, getEstadoPaneles()[0], getEstadoPaneles()[1],
					getEstadoPaneles()[2], getEstadoPaneles()[3], getEstadoPaneles()[4], getEstadoPaneles()[5], 
					getEstadoPaneles()[6], getEstadoPaneles()[7]);
			break;
		default:
			System.out.println("FATAL ERROR EN LA CLONACION DE VENTANAS");
			v=null;
			break;
		}
		return v;
	}
}
