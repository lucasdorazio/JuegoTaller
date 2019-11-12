package grafica;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class ComoJugar extends JFrame {

	private JPanel contentPane;
	private Image imagen;
	//private Menu menu;
	
	/**
	 * Create the frame.
	 */
	public ComoJugar(Menu m) {
		//this.menu=m;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				m.setVisible(true);
			}
		});
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(imagen, 0, 0, this.getWidth(), this.getHeight(), null);
			};
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		URL urlImagen= getClass().getClassLoader().getResource("grafica/Fondos/Instrucciones.png");
		if (urlImagen == null) {
			System.err.println("No se encuentra el archivo fondoMenu.jpg");
		} else {
			try {
				imagen = ImageIO.read(urlImagen); // carga imagen en img
				//contentPane.getGraphics().drawImage(fondo, 0, 0, null);
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		
		
		JLabel lblEstoFunciona = new JLabel("Esto funciona");
		contentPane.add(lblEstoFunciona, BorderLayout.CENTER);
	}

}
