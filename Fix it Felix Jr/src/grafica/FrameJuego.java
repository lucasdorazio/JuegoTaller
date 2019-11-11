package grafica;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameJuego extends JFrame {
		
	private Menu menu;
	
	public FrameJuego(Menu m) {
		this.menu=m;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				m.setVisible(true);
			}
		});
		setBounds(100, 100, 50, 50);
	}
	
}
