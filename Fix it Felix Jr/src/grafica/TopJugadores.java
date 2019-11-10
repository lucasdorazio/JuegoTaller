package grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import juego.Jugador;

import javax.swing.JLabel;
import javax.swing.JTable;

public class TopJugadores extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public TopJugadores(Jugador[] jugadores) {
		String[] columnas = {"Posicion", "Jugador", "Puntaje"};
		String[][] datos= new String[5][3];
		for (int i = 0; i < 5; i++) {
			datos[i][0]=String.valueOf(i+1);
			datos[i][1]=jugadores[i].getNick();
			datos[i][2]=String.valueOf(jugadores[i].getPuntaje());
		
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		//preguntar porque no imprime las columnas
	}
	
	 
}
