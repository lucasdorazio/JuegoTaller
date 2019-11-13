package grafica;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.ControladorDePajaro;
import edificio.Edificio;
import edificio.EstadoPanel;
import edificio.Ventana;
import entidades.Direcciones;
import entidades.Felix;
import entidades.Pajaro;
import juego.Juego;

@SuppressWarnings("serial")
public class FrameJuego extends JFrame {

	private JPanel contentPane;
	private Image  fondo, seccion0, seccion1, seccion2, felix, ralph, ventanaComun, puerta, conHojas,
	semicircular, panelSemiRoto, panelSano, pajaro; 
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
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				m.setVisible(true);
			}
		});
		try {
			fondo = ImageIO.read(new File("src/grafica/Fondos/background.png"));
            seccion0 = ImageIO.read(new File ("src/grafica/fixitfelixcortado/edificio/edificio_150_seccion1.png"));
            seccion1 = ImageIO.read(new File ("src/grafica/fixitfelixcortado/edificio/edificio_150_seccion2.png"));
            seccion2 = ImageIO.read(new File ("src/grafica/fixitfelixcortado/edificio/edificio_150_seccion3.png"));
            felix = ImageIO.read(new File("src/grafica/fixitfelixcortado/Felix/slice102_@.png"));
            ventanaComun= ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice103_@.png"));
            conHojas=ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice106_@.png"));
            semicircular=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice602_@.png"));
            puerta=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice600_@.png"));
            panelSemiRoto=ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice05_05.png"));
            panelSano=ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice14_14.png"));
            pajaro=ImageIO.read(new File("src/grafica/fixitfelixcortado/pajaro/slice08_08.png"));
		} catch (IOException ex) {
            ex.printStackTrace();	
        }
		
		contentPane = new JPanel() {
			
			protected void paintComponent(Graphics g) {
				g.drawImage(fondo, 0, 0, this.getWidth(),this.getHeight(),null);
				paintSeccion(g);
				paintVentanas(g);
				paintPajaros(g);
				//g.drawImage(felix, Felix.getInstance().getVentanaActual(), dy1, felix.getWidth(null), felix.getHeight(null), null)
			};
			
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setBounds(100, 100, 675, 370);
	}
	
	private void paintSeccion(Graphics g) {
		Image seccionAct = null;
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
					g.drawImage(ventanaActual, 214+52*i+modifX, 20+80*j+modifY, ventanaActual.getWidth(null), ventanaActual.getHeight(null), null);
					paintPanelesComun(g, v);
					break;
				case "edificio.ConHojas":
					ventanaActual=conHojas;
					g.drawImage(ventanaActual, 214+52*i+modifX, 20+80*j+modifY, ventanaActual.getWidth(null), ventanaActual.getHeight(null), null);
					break;
				case "edificio.SemicircularSuperior":
					ventanaActual=semicircular;
					modifX=-11;
					g.drawImage(ventanaActual, 214+52*i+modifX, 20+80*j+modifY, ventanaActual.getWidth(null), ventanaActual.getHeight(null), null);
					break;
				case "edificio.Puerta":
					modifX=-11;
					modifY=-25;
					ventanaActual=puerta;
					g.drawImage(ventanaActual, 214+52*i+modifX, 20+80*j+modifY, ventanaActual.getWidth(null), ventanaActual.getHeight(null), null);
					break;
				}
				//g.drawImage(ventanaActual, 214+52*i+modifX, 20+80*j+modifY, ventanaActual.getWidth(null), ventanaActual.getHeight(null), null);
			}
		}
	}

	private void paintPanelesComun(Graphics g, Ventana v) {
		Image panelActual = null;
		int m=v.getNroColumna();
		int n=v.getNroFila();
		for (int i = 0; i < 2; i++) {
			if (v.getEstadoPaneles()[i] == EstadoPanel.MEDIO_ROTO)
				panelActual = panelSemiRoto;
			else if (v.getEstadoPaneles()[i] == EstadoPanel.SANO)
				panelActual = panelSano;
			if (v.getEstadoPaneles()[i] != EstadoPanel.ROTO) g.drawImage(panelActual, 224+52*m, 32+80*(n+1)+19*i, panelActual.getWidth(null), panelActual.getHeight(null), null);
		}
	}
	
	private void paintPajaros(Graphics g) {
		List<Pajaro> lista = Juego.getInstance().getListaPajaros();
		Iterator<Pajaro> ite = lista.iterator();
		Pajaro pajaro= null;
		while (ite.hasNext()) {
			pajaro = ite.next();
			g.drawImage(this.pajaro, pajaro.getPos().getPosX(), pajaro.getPos().getPosY(), this.pajaro.getWidth(null), this.pajaro.getHeight(null), null);
		}
	}
	public void paintComponent(Graphics g) {
		g.drawImage(fondo, 0, 0, this.getWidth(),this.getHeight(),null);
		paintSeccion(g);
		paintVentanas(g);
		paintPajaros(g);
	}
}
