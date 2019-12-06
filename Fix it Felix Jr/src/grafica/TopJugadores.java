package grafica;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import juego.ModeloTopJugadores;
import juego.Ranking;

import javax.swing.JLabel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class TopJugadores extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblTopJugadores;
	/**
	 * Create the frame.
	 */
	public TopJugadores(Ranking ranking) {
		String[] columnas = {"Posicion", "Jugador", "Puntaje"};
		Object[][] datos= new String[5][3];
		for (int i = 0; i < ranking.getCantJugadores(); i++) {
//			datos[i][0]=String.valueOf(i+1);
			datos[i][0]=i+1;
			datos[i][1]=ranking.getMejoresCinco()[i].getNick();
//			datos[i][2]=String.valueOf(ranking.getMejoresCinco()[i].getPuntaje());
			datos[i][2]=ranking.getMejoresCinco()[i].getPuntaje();
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
		
		
//		table = new JTable(datos, columnas);
		table= new JTable();
		table.setModel(new ModeloTopJugadores(datos, columnas));
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		lblTopJugadores = new JLabel("TOP 5 JUGADORES");
		contentPane.add(lblTopJugadores, BorderLayout.NORTH);
	}
}
