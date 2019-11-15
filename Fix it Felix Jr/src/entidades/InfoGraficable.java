package entidades;

import java.util.List;

public class InfoGraficable<E> {
	
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
