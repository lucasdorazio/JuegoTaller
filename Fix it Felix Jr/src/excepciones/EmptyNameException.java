package excepciones;

@SuppressWarnings("serial")
public class EmptyNameException extends ImproperNameException {

	public EmptyNameException() {
		error= "El nombre no puede estar vacío";
	}
}
