package grafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

@SuppressWarnings({ "serial" })
public class FrameJuego extends JFrame {

	private JLabel lblPuntaje, lblTiempo, lblScore;
	private JPanel contentPane; 
	private HashMap<EstadoFelix,Image> felix;
	private HashMap<EstadoRalph,Image> ralph;
	private HashMap<Integer, Image> seccion;
	private Image  fondo, macetero, moldura, pausa,
	ventanaComun, conHojas, cerrada, semicircular, panelSemiRoto, panelSano,
	pajaro1, pajaro2, pajaro3, pajaro4, pastel1, pastel2, ladrillo,
	semi1, semi2, semi3, semi4, semi5, semi6, semi7, semi8, puerta0, puerta1, puerta2, puerta3, puerta4, corazon;

	public FrameJuego(Menu m) {
		addKeyListener(new KeyGameAdapter());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				m.pausarJuego();
				if (JOptionPane.showConfirmDialog(null, "¿Realmente desea salir del juego", "Confirmar salida", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE) == 0) 
					m.juegoCerrado();
				else m.reanudarJuego();
			}
		});
		try {
			felix = new HashMap<EstadoFelix, Image>();
			ralph = new HashMap<EstadoRalph, Image>();
			seccion = new HashMap<Integer, Image>();
			felix.put(EstadoFelix.NORMAL,  ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/Felix/slice102_@.png")));
			felix.put(EstadoFelix.REPARANDO, ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/Felix/slice135_@.png")));
            felix.put(EstadoFelix.MOVIENDOSE,  ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/Felix/slice103_@.png")));
            felix.put(EstadoFelix.MUERTO, ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/Felix/slice293_@.png")));
            felix.put(EstadoFelix.INVULNERABLE, ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/Felix/slice101_@.png")));
            ralph.put(EstadoRalph.GOLPEANDO1, ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/ralph/slice168_@.png")));
			ralph.put(EstadoRalph.NORMAL1, ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/ralph/slice146_@.png")));
			ralph.put(EstadoRalph.GOLPEANDO2, ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/ralph/slice167_@.png")));
			ralph.put(EstadoRalph.CAMINANDO_DERECHA1, ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/ralph/slice147_@.png")));
			ralph.put(EstadoRalph.CAMINANDO_DERECHA2, ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/ralph/slice148_@.png")));
			ralph.put(EstadoRalph.CAMINANDO_IZQUIERDA1, rotarImagen(ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/ralph/slice147_@.png"))));
			ralph.put(EstadoRalph.CAMINANDO_IZQUIERDA2, rotarImagen(ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/ralph/slice148_@.png"))));
			fondo = ImageIO.read(this.getClass().getClassLoader().getResource("grafica/Fondos/fondoDefinitivo.png"));
			seccion.put(0, ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/edificio/edificio_150_seccion1.png")));
			seccion.put(1, ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/edificio/seccion2.png")));
			seccion.put(3, ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/edificio/seccion3.png")));
            pausa= ImageIO.read(this.getClass().getClassLoader().getResource("grafica/Otros/pausa.png"));

        
            ventanaComun= ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/ventanas_y_panel/slice103_@.png"));
            conHojas=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/ventanas_y_panel/slice106_@.png"));
            cerrada=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/ventanas_y_panel/slice105_@.png"));
            semicircular=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice602_@.png"));
            panelSemiRoto=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/ventanas_y_panel/slice05_05.png"));
            panelSano=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/ventanas_y_panel/slice14_14.png"));
            pajaro1=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/pajaro/slice08_08.png"));
            pajaro2=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/pajaro/slice09_09.png"));
            pajaro3=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/pajaro/slice41_41.png"));
            pajaro4=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/pajaro/slice61_61.png"));
            pastel1=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/pastel/slice12_12.png"));
            pastel2=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/pastel/slice13_13.png"));
            ladrillo=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/rocas/slice10_10.png"));
            semi1=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice602_1@.png"));
            semi2=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice602_2@.png"));
            semi3=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice602_3@.png"));
            semi4=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice602_4@.png"));
            semi5=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice602_5@.png"));
            semi6=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice602_6@.png"));
            semi7=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice602_7@.png"));
            semi8=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice602_8@.png"));
            macetero=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/obstaculos/macetero.png"));
            moldura=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/obstaculos/slice22_22.png"));
            puerta0=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice600_@.png"));
            puerta1=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice601_@.png"));
            puerta2=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice596_@.png"));
            puerta3=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice592_@.png"));
            puerta4=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/fixitfelixcortado/semicirculares/slice594_4@.png"));
            corazon=ImageIO.read(this.getClass().getClassLoader().getResource("grafica/Otros/corazon.png"));
		} catch (IOException ex) {
            ex.printStackTrace();	
        }
		
		contentPane = new JPanel(){
			protected void paintComponent(Graphics g) {
				//super.paintComponent(g);
				g.drawImage(fondo, 0, 0, this.getWidth(), this.getHeight(),null);
				paintInfo(g);
				paintSeccion(g);
				paintVentanas(g);
				paintPajaros(g);
				paintPastel(g);
				paintLadrillos(g);
				paintRalph(g);
				paintFelix(g);
				paintRalph(g);
			};
		};
		lblScore = new JLabel("SCORE");
		lblScore.setFont(crearFuente("src/grafica/Fuentes/ARCADE_N.TTF", 20));
		lblScore.setForeground(Color.WHITE);
		lblScore.setBounds(0, 0, 100, 20);
		lblPuntaje = new JLabel("000000");
		lblPuntaje.setFont(crearFuente("src/grafica/Fuentes/ARCADE_N.TTF", 20));
		lblPuntaje.setForeground(Color.WHITE);
		lblPuntaje.setBounds(0, 30, 200, 20);
		lblTiempo = new JLabel();
		lblTiempo.setFont(crearFuente("src/grafica/Fuentes/ARCADE_N.TTF", 20));
		lblTiempo.setForeground(Color.WHITE);
		lblTiempo.setBounds(0, 60, 200, 20);
		contentPane.add(lblPuntaje);
		contentPane.add(lblTiempo);
		contentPane.add(lblScore);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		Dimension tamañoPantalla= Toolkit.getDefaultToolkit().getScreenSize();
		int ancho= Juego.LIMITE_DERECHO_MAPA;
		int alto=Juego.LIMITE_INFERIOR_MAPA;
		setBounds((tamañoPantalla.width-ancho)/2,(tamañoPantalla.height-alto)/2, ancho, alto);
	}
	
	private void paintSeccion(Graphics g) {
		Image seccionAct =seccion.get(Juego.getInstance().getNroSeccion());
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
		InfoGraficable<EstadoRalph> info = Juego.getInstance().getInfoGraficableRalph();
		Posicion pos = info.getListaPosiciones().get(0);
		EstadoRalph estado = info.getListaEstados().get(0);
		Image imagen = ralph.get(estado);
		g.drawImage(imagen, pos.getPosX(), pos.getPosY(), imagen.getWidth(null), imagen.getHeight(null), null);
	}
	
	private void paintFelix(Graphics g) {
		
		InfoGraficable<EstadoFelix> info= Felix.getInstance().getInfoGraficable();
		Posicion pos= info.getListaPosiciones().get(0);
		EstadoFelix estado= info.getListaEstados().get(0);
		Image imagen= felix.get(estado);
		g.drawImage(imagen, pos.getPosX(), pos.getPosY(), imagen.getWidth(null), imagen.getHeight(null), null);
	}
	
	public void paintInfo(Graphics g) {
		int vidas = Felix.getInstance().getVidas();
		String tiempo =((Integer) Juego.getInstance().getTime()).toString();
		String puntaje =  String.format("%06d", Juego.getInstance().getPuntaje());
		lblPuntaje.setText(puntaje);
		lblTiempo.setText("TIME " + tiempo);
		for (int i = 0; i <vidas; i++) {
			g.drawImage(corazon, 0+30*i, 80, 40, 40, null);
			
		}
	}

//	flip horizontal (espejo)
	public Image rotarImagen(Image image) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter((BufferedImage) image, null);
	}
	
	public static Font crearFuente(String ruta, int escala){
        Font fuente = null; 
        InputStream myStream = null;
       try {
			myStream = new BufferedInputStream(new FileInputStream(ruta));
			fuente = Font.createFont(Font.TRUETYPE_FONT, myStream);
			fuente = fuente.deriveFont(Font.PLAIN, escala);
		} catch (FontFormatException ex) {
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
		}
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(fuente);
	    return fuente;
}
	public void mostrarPausa() {
		contentPane.getGraphics().drawImage(pausa, Edificio.getLimiteIzquierdaEdificio(), (Juego.LIMITE_INFERIOR_MAPA-pausa.getHeight(null))/2, 315, 90, null);
	}
	
	public void pintar() {
		repaint();
	}
	
	public void cerrarVentana() {
		dispose();
	}
	
	public void hacerVisible(boolean b) {
		setVisible(b);
	}
}
