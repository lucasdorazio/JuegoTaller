package controladores;

import java.util.List;

import entidades.Posicion;

public abstract class Controlador {
	
	public abstract void actualizar();
	
	public abstract void avanzarSeccion();
	
	public abstract List<Posicion> getListaPosEntidades();

}
