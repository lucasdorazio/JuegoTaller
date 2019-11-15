package excepciones;

@SuppressWarnings("serial")
public class TooLongNameException extends ImproperNameException {
	
	public TooLongNameException() {
		error= "Nombre demasiado largo";
	}
}