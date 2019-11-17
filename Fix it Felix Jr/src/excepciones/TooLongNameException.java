package excepciones;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class TooLongNameException extends ImproperNameException {
	
	public TooLongNameException() {
		error= "Nombre demasiado largo";
		JOptionPane.showMessageDialog(null, "Nombre demasiado largo", "Error!", JOptionPane.ERROR_MESSAGE);
	}
}