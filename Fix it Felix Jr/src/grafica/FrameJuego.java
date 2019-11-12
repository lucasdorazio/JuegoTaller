package grafica;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameJuego extends JFrame {
	
	private Image imEdificio;
		
	public FrameJuego(Menu m) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				m.setVisible(true);
			}
		});
		try {
            imEdificio = ImageIO.read(new File ("src/grafica/Fondos/fondoMenu3.jpg")); // carga imagen en img
            //contentPane.getGraphics().drawImage(fondo, 0, 0, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		setBounds(100, 100, 700, 800);
	}
	
}
