package excepciones;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class EmptyNameException extends ImproperNameException {

	public EmptyNameException() {
		error= "El nombre no puede estar vacío";
		JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", "Error!", JOptionPane.ERROR_MESSAGE);
	}
}
