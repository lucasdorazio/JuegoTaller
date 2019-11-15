package entidades;

import java.util.List;

public class InfoGraficable<E> {
	// hubiera sido mas conveniente que no sean listas, y que el juego cree una lista de
	// infoGraficable(cada elemento es la InfoGraficable de una entidad), pero no se como crear
	// un estado generico (private <E> estado) ya que cada Estado es una clase diferente(EstadoPajaro, EstadoPastel)
	private List<E> listaEstados;
	
	private List<Posicion> listaPosiciones;

	
	public List<E> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<E> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public List<Posicion> getListaPosiciones() {
		return listaPosiciones;
	}

	public void setListaPosiciones(List<Posicion> listaPosiciones) {
		this.listaPosiciones = listaPosiciones;
	}

}
