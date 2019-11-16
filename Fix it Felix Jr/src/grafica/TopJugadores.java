package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import juego.Jugador;
import juego.Ranking;

import javax.swing.JLabel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class TopJugadores extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblTopJugadores;
	private Image fondo;

	/**
	 * Create the frame.
	 */
	public TopJugadores(Ranking ranking) {
		String[] columnas = {"Posicion", "Jugador", "Puntaje"};
		String[][] datos= new String[5][3];
		for (int i = 0; i < ranking.getCantJugadores(); i++) {
			datos[i][0]=String.valueOf(i+1);
			datos[i][1]=ranking.getMejoresCinco()[i].getNick();
			datos[i][2]=String.valueOf(ranking.getMejoresCinco()[i].getPuntaje());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		// la unica forma de visualizar bien un jtable por defecto es con un jscrollpane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(9, 30, 366, 181);
		this.getContentPane().add(scrollPane);
		
		
		table = new JTable(datos, columnas);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		lblTopJugadores = new JLabel("TOP 5 JUGADORES");
		contentPane.add(lblTopJugadores, BorderLayout.NORTH);
		//preguntar porque no imprime las columnas
	}
	
	public TopJugadores() {
		int modifX, modifY;
		Dimension tamañoPantalla= Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("TOP 5 JUGADORES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		int ancho=650;
		int alto=375;
		setBounds((tamañoPantalla.width-ancho)/2,(tamañoPantalla.height-alto)/2, ancho, alto);
		contentPane = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(fondo, 0, 0, this.getWidth(), this.getHeight(), null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		try {
			fondo = ImageIO.read(new File("src/grafica/Fondos/fondoMenu3.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		modifX= ancho/ 27;
		modifY= alto/6; 
		Font fuente= Menu.crearFuente("src/grafica/Fuentes/ka1.ttf", 20);
		
		JLabel lblJugadores= new JLabel("Jugadores");
		lblJugadores.setFont(fuente);
		lblJugadores.setForeground(Color.RED);
		lblJugadores.setBounds((modifX*15)/2,0, 300, 25);
		contentPane.add(lblJugadores);
		
		JLabel lblPuntaje = new JLabel("Puntaje");
		lblPuntaje.setFont(fuente);
		lblPuntaje.setForeground(Color.RED);
		lblPuntaje.setBounds(((modifX*6)/2) + 17 *modifX,0, 300, 25);
		contentPane.add(lblPuntaje);
		
//		for (int i=1;i<5; i++) {
//			JLabel lblFixItFelix = new JLabel("FIX IT \n FELIX JR!");
//			lblFixItFelix.setFont(Menu.crearFuente("src/grafica/Fuentes/ka1.ttf", 13));
//			lblFixItFelix.setForeground(Color.RED);
//			lblFixItFelix.setBounds(76, 132, 682, 172);
//			contentPane.add(lblFixItFelix);
//		}
	}
	
	 
}
