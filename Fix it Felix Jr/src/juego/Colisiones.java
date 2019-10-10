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
		Ventana actual=Felix.getInstance().getVentanaActual();
		Iterator<Ladrillo> iteLadrillos= listaLadrillos.iterator();
		Iterator<Pajaro> itePajaros= listaPajaros.iterator();
		if (pastel.devolverVentana().equals(actual)) {
			Felix.getInstance().recibirImpactoPastel();
			fueImpactado=true;
		}
		while (iteLadrillos.hasNext() && !fueImpactado) {
			l=iteLadrillos.next();
			if (l.devolverVentana().equals(actual)){
				Felix.getInstance().recibirImpactoLadrillo();
				//l.impactar();
				Juego.ladrilloGolpeoAFelix();
				fueImpactado=true;
			}
		}
		while (itePajaros.hasNext() && !fueImpactado) {
			p=itePajaros.next();
			if (p.devolverVentana().equals(actual)) {
				//p.impactar();
				Juego.pajaroGolpeoAFelix();
				fueImpactado=true;
			}
		}
	}
	
}
