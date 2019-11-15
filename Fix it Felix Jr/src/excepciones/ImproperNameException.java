package excepciones;

@SuppressWarnings("serial")
public class ImproperNameException extends RuntimeException {
	
	protected String error;
	
	public String toString() {
		return error;
	}

}
