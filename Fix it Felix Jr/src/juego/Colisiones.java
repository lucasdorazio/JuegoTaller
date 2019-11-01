package juego;
/**
  La clase Colisiones esta destinada al control y atencion de los choques 
 * durante el juego
 * 
 * @author Lucas Dorazio & Renzo Quaggia
 * @version 1.0
 */
import java.util.Iterator;
import java.util.List;

import controladores.ControladorDeLadrillos;
import controladores.ControladorDePajaro;
import edificio.Ventana;
import entidades.Felix;
import entidades.Ladrillo;
import entidades.Pajaro;
import entidades.Pastel;

public class Colisiones {
	
	public Colisiones() {
		
	}
	/**
	 * Metodo que revisa para cada entidad creada, si se encuentra en la misma
	 * ventana que Felix. Si es asi, invocará a los metodos correspondientes 
	 */
	public void comprobarColisiones() {
		Ladrillo l;
		Pajaro p;
		boolean fueImpactado=false;
		List<Ladrillo> listaLadrillos= ControladorDeLadrillos.getListaLadrillos();
		List<Pajaro> listaPajaros= ControladorDePajaro.getListaPajaros();
		Pastel pastel=Juego.getPastel();
		Ventana actualFelix=Felix.getInstance().getVentanaActual();
		Ventana	actualEntidad;
		Iterator<Ladrillo> iteLadrillos= listaLadrillos.iterator();
		Iterator<Pajaro> itePajaros= listaPajaros.iterator();
		if (pastel!= null && pastel.devolverVentana().equals(actualFelix)) {
			Felix.getInstance().recibirImpactoPastel();
			Juego.felixComePastel();
			fueImpactado=true;
		}
		while (iteLadrillos.hasNext() && !fueImpactado) {
			l=iteLadrillos.next();
			actualEntidad=l.devolverVentana();
			if (actualEntidad!= null && actualEntidad.equals(actualFelix)){
				Felix.getInstance().recibirImpactoLadrillo();
				Juego.ladrilloGolpeoAFelix();
				fueImpactado=true;
			}
		}
		while (itePajaros.hasNext() && !fueImpactado) {
			p=itePajaros.next();
			actualEntidad=p.devolverVentana();
			if (actualEntidad!= null && actualEntidad.equals(actualFelix)){
				Juego.pajaroGolpeoAFelix();
				fueImpactado=true;
			}
		}
	}
	
}
