package juego;

import javax.swing.table.DefaultTableModel;

public class ModeloTopJugadores extends DefaultTableModel {
	
	public ModeloTopJugadores (final Object[][] datos, final String[] titulos) {
		super (datos,titulos);
	}
	
	@Override
	public Class getColumnClass (final int column) {
		return this.getValueAt(0, column).getClass();
	}
}
