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
import entidades.Ladrillo;
import entidades.Pajaro;
import entidades.Posicion;
import entidades.Ralph;
import juego.Juego;

@SuppressWarnings("serial")
public class FrameJuego extends JFrame {

	private JPanel contentPane; 
	private Thread hiloJuego;
	private Thread hiloPausa;
	private Image  fondo, seccion0, seccion1, seccion2, felix, ralph, ventanaComun, puerta, conHojas, cerrada,
	semicircular, panelSemiRoto, panelSano, pajaro, pastel, ladrillo;

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
            ralph = ImageIO.read(new File("src/grafica/fixitfelixcortado/ralph/slice233_@.png"));
            ventanaComun= ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice103_@.png"));
            conHojas=ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice106_@.png"));
            cerrada=ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice105_@.png"));
            semicircular=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice602_@.png"));
            puerta=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice600_@.png"));
            panelSemiRoto=ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice05_05.png"));
            panelSano=ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice14_14.png"));
            pajaro=ImageIO.read(new File("src/grafica/fixitfelixcortado/pajaro/slice08_08.png"));
            pastel=ImageIO.read(new File("src/grafica/fixitfelixcortado/pastel/slice12_12.png"));
            ladrillo=ImageIO.read(new File("src/grafica/fixitfelixcortado/rocas/slice10_10.png"));
		} catch (IOException ex) {
            ex.printStackTrace();	
        }
		
		contentPane = new JPanel(){
			protected void paintComponent(Graphics g) {
				//super.paintComponent(g);
				g.drawImage(fondo, 0, 0, this.getWidth(),this.getHeight(),null);
				paintSeccion(g);
				paintVentanas(g);
				paintPajaros(g);
				paintPastel(g);
				//paintLadrillos(g);
				g.drawImage(felix, Felix.getInstance().getPos().getPosX(), Felix.getInstance().getPos().getPosY(), felix.getWidth(null), felix.getHeight(null), null);
				g.drawImage(ralph, Juego.getInstance().getPosRalph().getPosX(), Juego.getInstance().getPosRalph().getPosY(), ralph.getWidth(null), ralph.getHeight(null),null);
			};
		};
//		contentPane.setBounds(100,100,675,370);
//		contentPane.setVisible(true);
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
			g.drawImage(cerrada, 214+52*i, 20, cerrada.getWidth(null), cerrada.getHeight(null), null);
			for (int j=0; j<3; j++) {
				modifX=0;
				v=m[j][i];
				switch (v.getClass().getName()) {
				case "edificio.Comun":
					ventanaActual=ventanaComun;
					g.drawImage(ventanaActual, 214+52*i+modifX, 100+80*j+modifY, ventanaActual.getWidth(null), ventanaActual.getHeight(null), null);
					paintPanelesComun(g, v);
					break;
				case "edificio.ConHojas":
					ventanaActual=conHojas;
					g.drawImage(ventanaActual, 214+52*i+modifX, 100+80*j+modifY, ventanaActual.getWidth(null), ventanaActual.getHeight(null), null);
					break;
				case "edificio.SemicircularSuperior":
					ventanaActual=semicircular;
					modifX=-11;
					g.drawImage(ventanaActual, 214+52*i+modifX, 100+80*j+modifY, ventanaActual.getWidth(null), ventanaActual.getHeight(null), null);
					break;
				case "edificio.Puerta":
					modifX=-11;
					modifY=-25;
					ventanaActual=puerta;
					g.drawImage(ventanaActual, 214+52*i+modifX, 100+80*j+modifY, ventanaActual.getWidth(null), ventanaActual.getHeight(null), null);
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
			if (v.getEstadoPaneles()[i] != EstadoPanel.ROTO) g.drawImage(panelActual, 224+52*m, 51+80*(n+1)-19*i, panelActual.getWidth(null), panelActual.getHeight(null), null);
		}
	}
	
	private void paintPastel(Graphics g) {
		Posicion pos = Juego.getInstance().getPosPastel();
		if ( pos != null) {
			g.drawImage(pastel, pos.getPosX(), pos.getPosY(), pastel.getWidth(null), pastel.getHeight(null), null);
		}
	}
	//seria mejor que juego me de un getListaPosiciones?
	private void paintPajaros(Graphics g) {
		List<Posicion> lista = Juego.getInstance().getListaPosPajaros();
		Iterator<Posicion> ite = lista.iterator();
		Posicion pos;
		while (ite.hasNext()) {
			pos = ite.next();
			g.drawImage(pajaro, pos.getPosX(), pos.getPosY(), pajaro.getWidth(null), pajaro.getHeight(null), null);
		}
	}
	 private void paintLadrillos(Graphics g) {
		 List<Posicion> lista = Juego.getInstance().getListaPosLadrillos();
			Iterator<Posicion> ite = lista.iterator();
			Posicion pos;
			while (ite.hasNext()) {
				pos = ite.next();
				g.drawImage(ladrillo, pos.getPosX(), pos.getPosY(), ladrillo.getWidth(null), ladrillo.getHeight(null), null);
			}
	 }
	
	public void paintComponents(Graphics g) {
		g.drawImage(fondo, 0, 0, this.getWidth(),this.getHeight(),null);
		paintSeccion(g);
		paintVentanas(g);
		paintPajaros(g);
		paintPastel(g);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
	}
}
