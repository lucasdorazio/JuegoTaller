package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PruebasRen {

	public static void main(String[] args) {
//		boolean nombreCorrecto=false;
//		while (!nombreCorrecto) {
//			try {
//				Juego.getInstance().pedirNombre();
//				nombreCorrecto = true;
//			} catch (ImproperNameException e) {
//				System.out.println("ERROR: " + e.toString());
//			}
//		}
//		System.out.println("Salio del while");
//		System.out.println("Salio del while x2");
		
//		TopJugadores ranking = new TopJugadores();
//		ranking.setVisible(true);
//		ranking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Marco marco= new Marco();
		marco.setVisible(false);
		
	}
	
	public static String PedirNombre () {
		String nya= JOptionPane.showInputDialog("Ingrese su nombre");
		return nya;
	}

	
}
@SuppressWarnings("serial")
class Marco extends JFrame {
	JPanel panel1,panel2,contentPane;
	JButton boton;
	Image fondoPanel1, fondoPanel2;
	
	public Marco() {
		setBounds(500, 100, 600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		contentPane= new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		try {
			fondoPanel1= ImageIO.read(new File("src/grafica/Fondos/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		panel1= new JPanel() {
			public void paintComponent(Graphics g) {
//				super.paintComponent(g);
				g.drawImage(fondoPanel1, 0, 0, fondoPanel1.getWidth(null), fondoPanel1.getHeight(null), null);
			}
		};
		panel1.setBackground(Color.RED);
		panel1.setBounds(0, 0, 300, 400);
		panel1.setVisible(true);
		contentPane.add(panel1);
		
		panel2= new JPanel();
		panel2.setBackground(Color.blue);
		panel2.setBounds(300, 400, 300, 400);
		panel2.setVisible(true);
		contentPane.add(panel2);
	}
}

/*
private void paintPastel(Graphics g) {
Posicion pos = Juego.getInstance().getPosPastel();
if ( pos != null) {
	g.drawImage(pastel, pos.getPosX(), pos.getPosY(), pastel.getWidth(null), pastel.getHeight(null), null);
}
}

public Posicion getPosPastel() {
return controladores[3].getListaPosEntidades().get(0);
}


@Override
public List<Posicion> getListaPosEntidades() {
	List<Posicion> lista = new LinkedList<Posicion>();
	lista.add(getPosPastel());
	return lista;
}

*/

//definir archivo, pw y j
/*		try {
			archivo= new FileWriter("dondeEstoy.txt",false);
			pw= new PrintWriter(archivo);
			for (int i=0;i<3;i++) {
				j= new Jugador("Nombre", (i+1)*50);
				pw.println(j.getNick()+ " "+ j.getPuntaje());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
