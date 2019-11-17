package grafica;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ComoJugar extends JPanel {
	
	private Image imagen;
	private JLabel botonAtras;
	private int alto=565;
	//private Menu menu;
	
	/**
	 * Create the frame.
	 */
	public ComoJugar(Menu m) {
		//this.menu=m;
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosed(WindowEvent e) {
//				m.setVisible(true);
//			}
//		});
		setBounds(0, 0, m.getWidth(), m.getHeight());
		setLayout(null);
		try {
			imagen= ImageIO.read(new File("src/grafica/Fondos/Instrucciones.png"));
			imagen= imagen.getScaledInstance(m.getWidth(), alto, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ImageIcon imagenBotonAtras = new ImageIcon("src/grafica/Otros/atras3.png");
		botonAtras = new JLabel("Atras");
		botonAtras.setBounds(15, 0, 75, 65);
		botonAtras.setIcon(new ImageIcon(imagenBotonAtras.getImage().getScaledInstance(botonAtras.getWidth(), botonAtras.getHeight(), Image.SCALE_SMOOTH)));
		botonAtras.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				m.visibilizarBotones();
			}
		});
		add(botonAtras);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imagen, 0, 0, this.getWidth(), alto, null);
	}

}
