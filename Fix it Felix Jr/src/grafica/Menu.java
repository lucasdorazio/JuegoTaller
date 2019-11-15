package grafica;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import juego.Ranking;
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
	private ControladorDeJuego gameController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setResizable(false);
		setTitle("Fix it Felix Jr!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
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
			// contentPane.getGraphics().drawImage(fondo, 0, 0, null);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		JLabel lblFixItFelix = new JLabel("FIX IT \n FELIX JR!");
		lblFixItFelix.setFont(CambiarFuente("src/grafica/Fuentes/ka1.ttf", 60));
		lblFixItFelix.setForeground(Color.RED);
		lblFixItFelix.setBounds(76, 132, 682, 172);
		contentPane.add(lblFixItFelix);
		
		JButton botonComoJugar = new JButton("COMO JUGAR");
		botonComoJugar.setFont(CambiarFuente("src/grafica/Fuentes/ARCADE_I.TTF", 11));
		botonComoJugar.setBounds(100, 400, 145, 73);
		ComoJugar frameComojugar = new ComoJugar(this);
		frameComojugar.setVisible(false);
		botonComoJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frameComojugar.setVisible(true);
			}
		});
		contentPane.add(botonComoJugar);
		
		
		JButton botonJugar = new JButton("JUGAR");
		botonJugar.setFont(CambiarFuente("src/grafica/Fuentes/ARCADE_I.TTF", 11));
		botonJugar.setBounds(338, 400, 145, 73);
		botonJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				crearControladorJuego();
				gameController.getFrameJuego().setVisible(true);
				gameController.jugar();
			}
		});
		contentPane.add(botonJugar);
		
		JButton botonRanking = new JButton("RANKING");
		TopJugadores framTop = new TopJugadores(Ranking.getMejoresCinco());
		botonRanking.setFont(CambiarFuente("src/grafica/Fuentes/ARCADE_I.TTF", 11));
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
		JLabel botonConfig= new JLabel("Options");
		botonConfig.setBounds(672, 24, 86, 79);
		botonConfig.setIcon(new ImageIcon(imagenBoton.getImage().getScaledInstance(botonConfig.getWidth(), botonConfig.getHeight(), Image.SCALE_SMOOTH)));
		Configuracion frameConfig= new Configuracion(this);
		frameConfig.setVisible(false);
		botonConfig.addMouseListener(new MouseAdapter() {
	
			@Override
			public void mouseClicked(MouseEvent e) {
				//setVisible(false);
				frameConfig.setVisible(true);
			}
		});
		contentPane.add(botonConfig);
		contentPane.add(botonConfig);
	}
		
	
	private void crearControladorJuego() {
		gameController= new ControladorDeJuego(this);
	}
	
	public Font CambiarFuente(String ruta, int escala){
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
}
