package juego;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		PedirNombre();
		System.out.println("Que raro es esto che");
		
	}
	
	public static String PedirNombre () {
		String nya= JOptionPane.showInputDialog("Ingrese su nombre");
		return nya;
	}

	
}
@SuppressWarnings("serial")
class Marco extends JFrame {
	public Marco() {
		setBounds(100, 100, 200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
