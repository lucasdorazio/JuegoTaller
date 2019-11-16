package excepciones;

@SuppressWarnings("serial")
public class NotAllowedMovementException extends RuntimeException {
	
	private String error;
	
	public NotAllowedMovementException(boolean finDeMapa) {
		if (finDeMapa) {
			error= "Llegaste al límite del mapa";
		}else
			error= "No se puede atravesar la ventana";
	}
	
	public String getMessage() {
		return error;
	}

}
