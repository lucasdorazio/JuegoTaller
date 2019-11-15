package controladores;

import java.util.List;

import entidades.InfoGraficable;
import entidades.Posicion;

public abstract class Controlador<E> {
	
	public abstract void actualizar();
	
	public abstract void avanzarSeccion();
	
	public abstract List<Posicion> getListaPosEntidades();
	
	public abstract List<E> getListaEstadoEntidades();
	
	public abstract InfoGraficable<E> getListaInfoGraficable();

}
