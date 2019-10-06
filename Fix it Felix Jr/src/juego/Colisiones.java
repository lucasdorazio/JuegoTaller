package juego;

import java.util.Iterator;
import java.util.List;

public class Colisiones {
	
	public void comprobarColisiones() {
		Ladrillo l;
		Pajaro p;
		List<Ladrillo> listaLadrillos= ControladorDeLadrillos.getListaLadrillos();
		List<Pajaro> listaPajaros= ControladorDePajaro.getListaPajaros();
		Pastel pastel=Juego.getPastel();
		Ventana actual=Felix.getInstance().getVentanaActual();
		Iterator<Ladrillo> iteLadrillos= listaLadrillos.iterator();
		Iterator<Pajaro> itePajaros= listaPajaros.iterator();
		if (pastel.devolverVentana().equals(actual)) Felix.getInstance().recibirPastel();
		while (iteLadrillos.hasNext()) {
			l=iteLadrillos.next();
			if (l.devolverVentana().equals(actual)){
				Felix.getInstance().recibirImpactoLadrillo();
				l.impactar();
				Juego.ladrilloGolpeaFelix();
			}
		}
		while (itePajaros.hasNext()) {
			p=itePajaros.next();
			if (p.devolverVentana().equals(actual)) {
				Felix.getInstance().recibirImpactoPajaro();
				p.impactar();
				Juego.pajaroGolpeaFelix();
			}
		}
	}
	
	public void colision(Impactable p) {
		//Felix.getInstance().recibirImpacto(p);
	}
}
