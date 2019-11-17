package excepciones;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class TooShortNameException extends ImproperNameException {
	
	public TooShortNameException() {
		error= "Nombre demasiado corto";
		JOptionPane.showMessageDialog(null, "Nombre demasiado corto", "Error!", JOptionPane.ERROR_MESSAGE);
	}

}
