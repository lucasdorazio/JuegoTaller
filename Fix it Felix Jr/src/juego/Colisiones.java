package juego;

import java.util.Iterator;
import java.util.List;

public class Colisiones {
	
	public Colisiones() {
		
	}
	
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
