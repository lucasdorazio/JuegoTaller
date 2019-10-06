package juego;

import java.util.Iterator;
import java.util.List;

public class Colisiones {
	
	public void comprobarColisiones() {
		List<Ladrillo> listaLadrillos= ControladorDeLadrillos.getListaLadrillos();
		List<Pajaro> listaPajaros= ControladorDePajaro.getListaPajaros();
		Pastel pastel=Juego.getPastel();
		Ventana actual=Felix.getInstance().getVentanaActual();
		Iterator<Ladrillo> iteLadrillos= listaLadrillos.iterator();
		Iterator<Pajaro> itePajaros= listaPajaros.iterator();
		if (pastel.devolverVentana().equals(actual)) Felix.getInstance().recibirImpacto(pastel);
		while (iteLadrillos.hasNext()) {
			
		}
		//preguntar por todos los ladrillos
		//preguntar por todos los pajaros
	}
	
	public void colision(Impactable p) {
		//Felix.getInstance().recibirImpacto(p);
	}
}
