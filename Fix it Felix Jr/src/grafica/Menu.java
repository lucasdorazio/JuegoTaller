package grafica;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controladores.ControladorDeJuego;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class Menu extends JFrame {
	private Image fondo;
	private JPanel contentPane;
	private JLabel lblFixItFelix, botonConfig;
	private JButton botonRanking, botonJugar, botonComoJugar;
	private ControladorDeJuego gameController;

	/**
	 * Create the frame.
	 */
	public Menu() {
		setResizable(false);
		setTitle("Fix it Felix Jr!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension tamañoPantalla= Toolkit.getDefaultToolkit().getScreenSize();
		int ancho= 800;
		int alto=600;
		setBounds((tamañoPantalla.width-ancho)/2,(tamañoPantalla.height-alto)/2, ancho, alto);
		contentPane = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(fondo, 0, 0, this.getWidth(), this.getHeight(), null);
			};
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		try {
			fondo = ImageIO.read(new File("src/grafica/Fondos/fondoMenu3.jpg")); // carga imagen en img
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		lblFixItFelix = new JLabel("FIX IT \n FELIX JR!");
		lblFixItFelix.setFont(crearFuente("src/grafica/Fuentes/ka1.ttf ", 60));
		lblFixItFelix.setForeground(Color.RED);
		lblFixItFelix.setBounds(76, 132, 682, 172);
		contentPane.add(lblFixItFelix);
		
		gameController= new ControladorDeJuego(this);
		botonRanking = new JButton("RANKING");
		TopJugadores framTop = new TopJugadores(gameController.getMejoresJugadores());
		botonRanking.setFont(crearFuente("src/grafica/Fuentes/ARCADE_I.TTF", 11));
		botonRanking.setBounds(570, 400, 145, 73);
		botonRanking.addActionListener(new ActionListener() {
		//hacer invisible el menu
			@Override
			public void actionPerformed(ActionEvent e) {
				framTop.setVisible(true);
			}
		});
		contentPane.add(botonRanking);
		
		
		ImageIcon imagenBoton = new ImageIcon("src/grafica/Otros/icon.png");
		botonConfig= new JLabel("Options");
		botonConfig.setBounds(672, 24, 86, 79);
		botonConfig.setIcon(new ImageIcon(imagenBoton.getImage().getScaledInstance(botonConfig.getWidth(), botonConfig.getHeight(), Image.SCALE_SMOOTH)));
		Configuracion frameConfig= new Configuracion(this, gameController);
		frameConfig.setVisible(false);
		botonConfig.addMouseListener(new MouseAdapter() {
	
			@Override
			public void mouseClicked(MouseEvent e) {
				//setVisible(false);
				frameConfig.setVisible(true);
			}
		});
		contentPane.add(botonConfig);
		
		botonJugar = new JButton("JUGAR");
		botonJugar.setFont(crearFuente("src/grafica/Fuentes/ARCADE_I.TTF", 11));
		botonJugar.setBounds(338, 400, 145, 73);
		botonJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				framTop.setVisible(false);
				frameConfig.setVisible(false);
				gameController.jugar();
			}
		});
		contentPane.add(botonJugar);
		botonComoJugar = new JButton("COMO JUGAR");
		botonComoJugar.setFont(crearFuente("src/grafica/Fuentes/ARCADE_I.TTF", 11));
		botonComoJugar.setBounds(100, 400, 145, 73);
		ComoJugar panelComoJugar = new ComoJugar(this);
		panelComoJugar.setVisible(false);
		contentPane.add(panelComoJugar);
		botonComoJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				botonComoJugar.setVisible(false);
				botonRanking.setVisible(false);
				botonConfig.setVisible(false);
				botonJugar.setVisible(false);
				lblFixItFelix.setVisible(false);
				panelComoJugar.setVisible(true);
			}
		});
		contentPane.add(botonComoJugar);
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
	
	public void visibilizarBotones() {
		botonComoJugar.setVisible(true);
		botonRanking.setVisible(true);
		botonConfig.setVisible(true);
		botonJugar.setVisible(true);
		lblFixItFelix.setVisible(true);
	}
	
	public void juegoCerrado() {
		this.setVisible(true);
		gameController.terminarJuego();
	}
	
	public void pausarJuego() {
		gameController.pausarJuego();
	}
	
	public void reanudarJuego() {
		gameController.reanudarJuego();
	}
}
