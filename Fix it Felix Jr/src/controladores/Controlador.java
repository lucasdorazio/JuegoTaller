package controladores;


import entidades.InfoGraficable;

public abstract class Controlador<E> {
	
	public abstract void actualizar();
	
	public abstract void avanzarSeccion();
	
	public abstract InfoGraficable<E> getListaInfoGraficable();

}
