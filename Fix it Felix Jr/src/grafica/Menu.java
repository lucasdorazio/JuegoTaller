package grafica;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import juego.Juego;
import controladores.ControladorDeJuego;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
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
		gameController= new ControladorDeJuego(this);
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

		URL urlFondo= getClass().getClassLoader().getResource("grafica/Fondos/fondoMenu3.jpg");
		if (urlFondo == null) {
			System.err.println("No se encuentra el archivo fondoMenu.jpg");
		} else {
			try {
				fondo = ImageIO.read(new File ("src/grafica/Fondos/fondoMenu3.jpg")); // carga imagen en img
				//contentPane.getGraphics().drawImage(fondo, 0, 0, null);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		JLabel lblFixItFelix = new JLabel("FIX IT \n FELIX JR!");
		lblFixItFelix.setFont(CambiarFuente("src/grafica/Fuentes/ka1.ttf", 60));
		lblFixItFelix.setBounds(76, 132, 682, 172);
		contentPane.add(lblFixItFelix);
		
		JButton botonComoJugar = new JButton("COMO JUGAR");
		botonComoJugar.setFont(CambiarFuente("src/grafica/Fuentes/ARCADE_I.TTF", 11));
		botonComoJugar.setBounds(114, 400, 115, 73);
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
		botonJugar.setBounds(342, 400, 115, 73);
		botonJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gameController.getFrameJuego().setVisible(true);
				//gameController.jugar();
			}
		});
		contentPane.add(botonJugar);
		
		JButton botonRanking = new JButton("RANKING");
		botonRanking.setFont(CambiarFuente("src/grafica/Fuentes/ARCADE_I.TTF", 11));
		botonRanking.setBounds(571, 400, 115, 73);
		botonRanking.addActionListener(new ActionListener() {
		//hacer invisible el menu
			@Override
			public void actionPerformed(ActionEvent e) {
				TopJugadores framTop = new TopJugadores(Juego.getInstance().mejoresCinco());
				framTop.setVisible(true);
			}
		});
		contentPane.add(botonRanking);
		
		/*JButton botonConfig = new JButton("New button");	
		botonConfig.setIcon(new ImageIcon(Menu.class.getResource("/grafica/Otros/icon.png")));
		botonConfig.setBounds(669, 24, 89, 23);	
		Configuracion frameConfig= new Configuracion(this);
		frameConfig.setVisible(false);
		botonConfig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frameConfig.setVisible(true);				}
			});
		contentPane.add(botonConfig);
		*/
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
