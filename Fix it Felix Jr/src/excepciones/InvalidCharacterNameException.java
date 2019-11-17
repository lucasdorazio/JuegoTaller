package excepciones;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class InvalidCharacterNameException extends ImproperNameException {

	public InvalidCharacterNameException() {
		error="El nombre contiene espacios";
		JOptionPane.showMessageDialog(null, "El nombre contiene espacios", "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
}
