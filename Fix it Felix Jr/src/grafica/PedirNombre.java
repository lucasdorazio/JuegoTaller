package grafica;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PedirNombre extends JFrame {
	
	private JPanel contentPane;
	private JButton btnAceptar;

	public PedirNombre(String s) {
		Dimension tamañoPantalla= Toolkit.getDefaultToolkit().getScreenSize();
		setTitle(s);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		int ancho=200;
		int alto=50;
		setBounds((tamañoPantalla.width-ancho)/2,(tamañoPantalla.height-alto)/2, ancho, alto);
		contentPane = new JPanel() {
			protected void paintComponent(Graphics g) {
//				g.drawImage(fondo, 0, 0, this.getWidth(), this.getHeight(), null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
	}
	
}
