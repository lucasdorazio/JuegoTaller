package excepciones;

@SuppressWarnings("serial")
public class InvalidCharacterNameException extends ImproperNameException {

	public InvalidCharacterNameException() {
		error="El nombre contiene espacios";
	}
	
}
