package grafica;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.applet.AudioClip;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edificio.Edificio;
import edificio.EstadoPanel;
import edificio.Ventana;
import entidades.EstadoFelix;
import entidades.EstadoPajaro;
import entidades.EstadoPastel;
import entidades.EstadoRalph;
import entidades.Felix;
import entidades.InfoGraficable;
import entidades.Posicion;
import juego.Juego;

@SuppressWarnings({ "serial", "deprecation" })
public class FrameJuego extends JFrame {

	private JPanel contentPane; 
	private Image  fondo, seccion0, seccion1, seccion2, macetero, moldura,
	felix, felixReparando, felixMoviendose, 
	ralph, ralphGolpeando1, ralphGolpeando2, ralphDerecha1, ralphDerecha2,
	ventanaComun, conHojas, cerrada, semicircular, panelSemiRoto, panelSano,
	pajaro1, pajaro2, pajaro3, pajaro4, pastel1, pastel2, ladrillo,
	semi1, semi2, semi3, semi4, semi5, semi6, semi7, semi8, puerta0, puerta1, puerta2, puerta3, puerta4;
	private AudioClip pajaroGolpeoFelix, ladrilloGolpeoFelix, golpeRalph;

	public FrameJuego(Menu m) {
		addKeyListener(new KeyGameAdapter());
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
            seccion1 = ImageIO.read(new File ("src/grafica/fixitfelixcortado/edificio/seccion2.png"));
            seccion2 = ImageIO.read(new File ("src/grafica/fixitfelixcortado/edificio/seccion3.png"));
            felix = ImageIO.read(new File("src/grafica/fixitfelixcortado/Felix/slice102_@.png"));
            felixReparando = ImageIO.read(new File("src/grafica/fixitfelixcortado/Felix/slice135_@.png"));
            felixMoviendose= ImageIO.read(new File("src/grafica/fixitfelixcortado/Felix/slice103_@.png"));
            ralph = ImageIO.read(new File("src/grafica/fixitfelixcortado/ralph/slice146_@.png"));
            ralphGolpeando1= ImageIO.read(new File("src/grafica/fixitfelixcortado/ralph/slice168_@.png"));
            ralphGolpeando2= ImageIO.read(new File("src/grafica/fixitfelixcortado/ralph/slice167_@.png"));
            ralphDerecha1= ImageIO.read(new File("src/grafica/fixitfelixcortado/ralph/slice147_@.png"));
            ralphDerecha2= ImageIO.read(new File("src/grafica/fixitfelixcortado/ralph/slice148_@.png"));
            ventanaComun= ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice103_@.png"));
            conHojas=ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice106_@.png"));
            cerrada=ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice105_@.png"));
            semicircular=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice602_@.png"));
            panelSemiRoto=ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice05_05.png"));
            panelSano=ImageIO.read(new File("src/grafica/fixitfelixcortado/ventanas_y_panel/slice14_14.png"));
            pajaro1=ImageIO.read(new File("src/grafica/fixitfelixcortado/pajaro/slice08_08.png"));
            pajaro2=ImageIO.read(new File("src/grafica/fixitfelixcortado/pajaro/slice09_09.png"));
            pajaro3=ImageIO.read(new File("src/grafica/fixitfelixcortado/pajaro/slice41_41.png"));
            pajaro4=ImageIO.read(new File("src/grafica/fixitfelixcortado/pajaro/slice61_61.png"));
            pastel1=ImageIO.read(new File("src/grafica/fixitfelixcortado/pastel/slice12_12.png"));
            pastel2=ImageIO.read(new File("src/grafica/fixitfelixcortado/pastel/slice13_13.png"));
            ladrillo=ImageIO.read(new File("src/grafica/fixitfelixcortado/rocas/slice10_10.png"));
            semi1=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice602_1@.png"));
            semi2=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice602_2@.png"));
            semi3=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice602_3@.png"));
            semi4=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice602_4@.png"));
            semi5=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice602_5@.png"));
            semi6=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice602_6@.png"));
            semi7=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice602_7@.png"));
            semi8=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice602_8@.png"));
            macetero=ImageIO.read(new File("src/grafica/fixitfelixcortado/obstaculos/macetero.png"));
            moldura=ImageIO.read(new File("src/grafica/fixitfelixcortado/obstaculos/slice22_22.png"));
            puerta0=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice600_@.png"));
            puerta1=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice601_@.png"));
            puerta2=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice596_@.png"));
            puerta3=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice592_@.png"));
            puerta4=ImageIO.read(new File("src/grafica/fixitfelixcortado/semicirculares/slice594_4@.png"));
            
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
				paintLadrillos(g);
//				g.drawImage(felix, Felix.getInstance().getPos().getPosX(), Felix.getInstance().getPos().getPosY(), felix.getWidth(null), felix.getHeight(null), null);
				paintFelix(g);
				paintRalph(g);
			};
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		Dimension tamañoPantalla= Toolkit.getDefaultToolkit().getScreenSize();
		int ancho= Juego.LIMITE_DERECHO_MAPA;
		int alto=Juego.LIMITE_INFERIOR_MAPA;
		setBounds((tamañoPantalla.width-ancho)/2,(tamañoPantalla.height-alto)/2, ancho, alto);
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
		Ventana[][] m=Edificio.getInstance().getSecciones()[Juego.getInstance().getNroSeccion()].getVentanas();
		Ventana v;
		for (int i=0; i<5; i++) {
			if (Juego.getInstance().getNroSeccion()==2) g.drawImage(cerrada, 214+52*i, 30, cerrada.getWidth(null), cerrada.getHeight(null), null);
			else
				g.drawImage(cerrada, 214+52*i, 20, cerrada.getWidth(null), cerrada.getHeight(null), null);
			for (int j=0; j<3; j++) {
				v=m[j][i];
				switch (v.getTipo()) {
				case COMUN:
					g.drawImage(ventanaComun, 214+52*i, 100+80*j, ventanaComun.getWidth(null), ventanaComun.getHeight(null), null);
					paintPanelesComun(g, v);
					break;
				case CONHOJAS:
					g.drawImage(conHojas, 214+52*i, 100+80*j, conHojas.getWidth(null), conHojas.getHeight(null), null);
					break;
				case CONHOJASCERRADA:
					g.drawImage(cerrada, 214+52*i, 100+80*j, cerrada.getWidth(null), cerrada.getHeight(null), null);
					break;
				case SEMICIRCULAR:
					paintPanelesSemicircular(g, v);
					break;
				case PUERTA:
					paintPanelesPuerta(g, v);
					break;
				}
				if (v.tieneMacetero())
					g.drawImage(macetero, 219+52*i, 144+80*j, macetero.getWidth(null), macetero.getHeight(null), null);
				if (v.tieneMoldura())
					g.drawImage(moldura, 213+52*i, 100+80*j, moldura.getWidth(null), moldura.getHeight(null), null);
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
	
	private void paintPanelesSemicircular(Graphics g, Ventana v) {
		Image ventana = null;
		int cantRotos = 0;
		EstadoPanel estados[] = v.getEstadoPaneles();
		for (int i = 0; i < estados.length; i++) {
			if (estados[i] != EstadoPanel.SANO) {
				cantRotos++;
			}
		}
		switch (cantRotos) {
		case 0:
			ventana=semicircular;
			break;
		case 1:
			ventana=semi1;
			break;
		case 2:
			ventana=semi2;
			break;
		case 3:
			ventana=semi3;
			break;
		case 4:
			ventana=semi4;
			break;
		case 5:
			ventana=semi5;
			break;
		case 6:
			ventana=semi6;
			break;
		case 7:
			ventana=semi7;
			break;
		case 8:
			ventana=semi8;
			break;
		default:
			break;
		}
		g.drawImage(ventana, 214+52*v.getNroColumna()-11, 100+80*v.getNroFila(), ventana.getWidth(null), ventana.getHeight(null), null);
	}
	
	private void paintPanelesPuerta(Graphics g, Ventana v) {
		Image ventana = null;
		int cantRotos = 0;
		EstadoPanel estados[] = v.getEstadoPaneles();
		for (int i = 0; i < estados.length; i++) {
			if (estados[i] != EstadoPanel.SANO) {
				cantRotos++;
			}
		}
		switch (cantRotos) {
		case 0:
			ventana=puerta0;
			break;
		case 1:
			ventana=puerta1;
			break;
		case 2:
			ventana=puerta2;
			break;
		case 3:
			ventana=puerta3;
			break;
		case 4:
			ventana=puerta4;
			break;
		default:
			break;
		}
		g.drawImage(ventana, 214+52*v.getNroColumna()-11, 100+80*v.getNroFila()-25, ventana.getWidth(null), ventana.getHeight(null), null);
	}
	
	private void paintPastel(Graphics g) {
		Image imagen = null;
		InfoGraficable<EstadoPastel> info = Juego.getInstance().getInfoGraficablePastel();
		Posicion pos = info.getListaPosiciones().get(0);
		EstadoPastel estado;
		if ( pos != null) {
			 estado = info.getListaEstados().get(0);
			if (estado == EstadoPastel.NORMAL1)
				imagen = pastel1;
			else
				imagen = pastel2;
			g.drawImage(imagen, pos.getPosX(), pos.getPosY(), imagen.getWidth(null), imagen.getHeight(null), null);
		}
	}
	//seria mejor que juego me de un getListaPosiciones?
	private void paintPajaros(Graphics g) {
		int i=0;
		Image imagen = null;
		List<Posicion> posiciones = Juego.getInstance().getInfoGraficablePajaros().getListaPosiciones();
		List<EstadoPajaro> estados = Juego.getInstance().getInfoGraficablePajaros().getListaEstados();
		Iterator<Posicion> ite = posiciones.iterator();
		Posicion pos;
		while (ite.hasNext()) {
			pos = ite.next();
			switch (estados.get(i)) {
			case VOLANDO1:
				imagen = pajaro1;
				break;
			case VOLANDO2:
				imagen = pajaro2;
				break;
			case VOLANDO3:
				imagen = pajaro3;
				break;
			case VOLANDO4:
				imagen = pajaro4;
				break;
			default:
				break;
			}
			g.drawImage(imagen, pos.getPosX(), pos.getPosY(), imagen.getWidth(null), imagen.getHeight(null), null);
			i++;
		}
	}
	 private void paintLadrillos(Graphics g) {
		 	List<Posicion> lista = Juego.getInstance().getInfoGraficableLadrillos().getListaPosiciones();
			Iterator<Posicion> ite = lista.iterator();
			Posicion pos;
			while (ite.hasNext()) {
				pos = ite.next();
				g.drawImage(ladrillo, pos.getPosX(), pos.getPosY(), ladrillo.getWidth(null), ladrillo.getHeight(null), null);
			}
	 }
	 
	private void paintRalph(Graphics g) {
		Image imagen = null;
		InfoGraficable<EstadoRalph> info = Juego.getInstance().getInfoGraficableRalph();
		Posicion pos = info.getListaPosiciones().get(0);
		EstadoRalph estado = info.getListaEstados().get(0);
		switch (estado) {
		case NORMAL1:
			imagen= ralph;
			break;
		case GOLPEANDO1:
			imagen= ralphGolpeando1;
			break;
		case GOLPEANDO2:
			imagen= ralphGolpeando2;
			break;
		case CAMINANDO_DERECHA1:
			imagen= ralphDerecha1;
			break;
		case CAMINANDO_DERECHA2:
			imagen= ralphDerecha2;
			break;
		case CAMINANDO_IZQUIERDA1:
			imagen= rotarImagen(ralphDerecha1);
			break;
		case CAMINANDO_IZQUIERDA2:
			imagen= rotarImagen(ralphDerecha2);
			break;
		}
		g.drawImage(imagen, pos.getPosX(), pos.getPosY(), imagen.getWidth(null), imagen.getHeight(null), null);
	}
	
	private void paintFelix(Graphics g) {
		Image imagen= null;
		InfoGraficable<EstadoFelix> info= Felix.getInstance().getInfoGraficable();
		Posicion pos= info.getListaPosiciones().get(0);
		EstadoFelix estado= info.getListaEstados().get(0);
		switch (estado) {
			case NORMAL:
				imagen=felix;
				break;
			case REPARANDO:
				imagen=felixReparando;
				break;
			case MOVIENDOSE:
				imagen=felixMoviendose;
				break;
			default:
				System.out.println("no deberia entrar aca");
				imagen=pajaro1;
				break;
		}
		g.drawImage(imagen, pos.getPosX(), pos.getPosY(), imagen.getWidth(null), imagen.getHeight(null), null);
	}
	
	public void paintComponents(Graphics g) {
		g.drawImage(fondo, 0, 0, this.getWidth(),this.getHeight(),null);
		paintSeccion(g);
		paintVentanas(g);
		paintPajaros(g);
		paintPastel(g);
	}

//	flip horizontal (espejo)
	public Image rotarImagen(Image image) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter((BufferedImage) image, null);
	}
}
