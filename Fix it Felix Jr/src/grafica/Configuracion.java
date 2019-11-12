package grafica;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Configuracion extends JFrame {

	private JPanel contentPane;
	private JComboBox<Integer> cmbNiveles;

	/**
	 * Create the frame.
	 */
	public Configuracion(Menu m) {
		
		//String[] niveles = {"Nivel 1","Nivel 2","Nivel 3","Nivel 4","Nivel 5",
		//		"Nivel 6","Nivel 7","Nivel 8","Nivel 9","Nivel 10"};
		Integer[] niveles= {1,2,3,4,5,6,6,7,9,10};
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				m.setVisible(true);
			}
		});
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNivel = new JLabel("NIVEL:");
		lblNivel.setBounds(75, 124, 46, 14);
		contentPane.add(lblNivel);
		
		JLabel lblConfiguracion = new JLabel("CONFIGURACION");
		lblConfiguracion.setBounds(131, 54, 141, 14);
		contentPane.add(lblConfiguracion);
		
		cmbNiveles = new JComboBox<Integer>();
		cmbNiveles.setBounds(131, 121, 109, 20);
		contentPane.add(cmbNiveles);
		cmbNiveles.setModel(new javax.swing.DefaultComboBoxModel<>(niveles));
	
	}
}
