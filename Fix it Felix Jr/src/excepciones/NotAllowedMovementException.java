package excepciones;

import edificio.Ventana;

@SuppressWarnings("serial")
public class NotAllowedMovementException extends RuntimeException {
	
	private String error;
	private Ventana origen, destino;
	
	public NotAllowedMovementException(boolean finDeMapa) {
		if (finDeMapa) {
			error= "Llegaste al límite del mapa";
		}else
			error= "No se puede atravesar la ventana";
	}
	
	public Ventana getVentanaOrigen() {
		return origen;
	}
	
	public Ventana getVentanaDestino() {
		return destino;
	}
	
	public void setRuta(Ventana origen, Ventana destino) {
		this.origen=origen;
		this.destino=destino;
	}
	
	public String getMessage() {
		return error;
	}

}
