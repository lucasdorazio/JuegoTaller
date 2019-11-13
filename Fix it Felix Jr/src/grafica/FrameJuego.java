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

import edificio.Edificio;
import edificio.Ventana;
import entidades.Direcciones;
import entidades.Felix;
import juego.Juego;

@SuppressWarnings("serial")
public class FrameJuego extends JFrame {

	private JPanel contentPane;
	private Image  seccion0, seccion1, seccion2, seccionAct, felix, ralph, ventanaComun, puerta, conHojas, semicircular; 
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
            seccion0 = ImageIO.read(new File ("src/grafica/fixitfelixcortado/edificio/edificio_150_seccion1.png"));
            seccion1 = ImageIO.read(new File ("src/grafica/fixitfelixcortado/edificio/edificio_150_seccion2.png"));
            seccion2 = ImageIO.read(new File ("src/grafica/fixitfelixcortado/edificio/edificio_150_seccion3.png"));
            felix = ImageIO.read(new File("src/grafica/fixitfelixcortado/Felix/slice102_@.png"));
            ventanaComun= ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice100_@.png"));
            conHojas=ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice106_@.png"));;
            semicircular=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice602_@.png"));;
            puerta=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice600_@.png"));;
		} catch (IOException ex) {
            ex.printStackTrace();	
        }
		
		contentPane = new JPanel() {
			
			protected void paintComponent(Graphics g) {
				switch (Juego.getInstance().getNroSeccion()) {
				case 0:
					seccionAct=seccion0;
					break;
				case 1:
					seccionAct=seccion1;
					break;
				case 2:
					seccionAct=seccion2;
				}
				g.drawImage(seccionAct, 180, 0, seccionAct.getWidth(null), seccionAct.getHeight(null), null);
				paintVentanas(g);
				
				//g.drawImage(felix, Felix.getInstance().getVentanaActual(), dy1, felix.getWidth(null), felix.getHeight(null), null)
			};
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setBounds(100, 100, 675, 560);
	}
	
	private void paintVentanas(Graphics g) {
		Image ventanaActual = null;
		Ventana[][] m=Edificio.getInstance().getSecciones()[Juego.getInstance().getNroSeccion()].getVentanas();
		Ventana v;
		int modifY, modifX;
		for (int i=0; i<5; i++) {
			modifY=0;
			for (int j=1; j<4; j++) {
				modifX=0;
				v=m[j-1][i];
				switch (v.getClass().getName()) {
				case "edificio.Comun":
					ventanaActual=ventanaComun;
					break;
				case "edificio.ConHojas":
					ventanaActual=conHojas;
					break;
				case "edificio.SemicircularSuperior":
					ventanaActual=semicircular;
					modifX=-11;
					break;
				case "edificio.Puerta":
					modifX=-11;
					modifY=-25;
					ventanaActual=puerta;
					break;
				}
				g.drawImage(ventanaActual, 214+52*i+modifX, 20+80*j+modifY, ventanaActual.getWidth(null), ventanaActual.getHeight(null), null);
			}
		}
	}
}
