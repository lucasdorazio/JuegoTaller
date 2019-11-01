package edificio;
/**
 * Clase que nos permite agrupar las ventanas que tengan más de dos paneles
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public abstract class Semicircular extends Ventana {

	
	
	public Semicircular(int nroFila, int nroCol, boolean tieneMacetero, boolean tieneMoldura) {
		super(nroFila, nroCol, tieneMacetero, tieneMoldura);
	}

	@Override
	public boolean puedoGenerarPastel() {
		return false;
	}

	public abstract void repararse();

	public abstract boolean estoySana();

}
