package grafica;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Direcciones;
import entidades.Felix;
import juego.Juego;

@SuppressWarnings("serial")
public class FrameJuego extends JFrame {

	private JPanel contentPane;
	private Image imEdificio, felix, ralph;  
	private Thread hiloJuego;
	private Thread hiloPausa;

	public FrameJuego(Menu m) {
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent tecla) {
				switch (tecla.getKeyCode()) {
				case 37:
					System.out.println("se presiono la flechita pa izq");
					Felix.getInstance().mover(Direcciones.IZQUIERDA);
					break;
				case 38:
					System.out.println("se presiono la flechita pa arriba");
					Felix.getInstance().mover(Direcciones.ARRIBA);
					break;
				case 39:
					System.out.println("se presiono la flechita pa derecha");
					Felix.getInstance().mover(Direcciones.DERECHA);
					break;
				case 40:
					System.out.println("se presiono la flechita pa abajo");
					Felix.getInstance().mover(Direcciones.ABAJO);
					break;
				case 80:
					System.out.println("Se apreto la p");
					if (hiloPausa.isAlive()) {
						hiloPausa.stop();
					}
					break;
				default:
					System.out.println("otra tecla");
					break;
				}
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				m.setVisible(true);
			}
		});
		try {
            imEdificio = ImageIO.read(new File ("src/grafica/fixitfelixcortado/edificio/edificio_150_seccion1.png"));
            felix = ImageIO.read(new File("src/grafica/fixitfelixcortado/Felix/slice102_@.png"));
		} catch (IOException ex) {
            ex.printStackTrace();
        }
		
		contentPane = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(imEdificio, 180, 0, imEdificio.getWidth(null), imEdificio.getHeight(null), null);
				g.drawImage(felix, Felix.getInstance().getVentanaActual(), dy1, felix.getWidth(null), felix.getHeight(null), null)
			};
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setBounds(100, 100, 700, 560);
	}
	
}
