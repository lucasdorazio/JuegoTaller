package excepciones;

@SuppressWarnings("serial")
public class TooShortNameException extends ImproperNameException {
	
	public TooShortNameException() {
		error= "Nombre demasiado corto";
	}

}
