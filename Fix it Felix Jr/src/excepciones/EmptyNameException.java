package excepciones;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class EmptyNameException extends ImproperNameException {

	public EmptyNameException() {
		error= "El nombre no puede estar vac�o";
		JOptionPane.showMessageDialog(null, "El nombre no puede estar vac�o", "Error!", JOptionPane.ERROR_MESSAGE);
	}
}
