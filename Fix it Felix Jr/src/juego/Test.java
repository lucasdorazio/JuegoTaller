package juego;

import javax.swing.JFrame;

public class Test {

	public static void main(String[] args) {
		/*Ventana[] v = new Ventanas[2];
		v[0]=new Comun(2, 2, true, true, EstadoPanel.SANO, EstadoPanel.SANO);
		v[1]=new Comun(2, 3, true, false, EstadoPanel.MEDIO_ROTO, EstadoPanel.SANO);
		*/
		Juego j = new Juego();
		JFrame f= new JFrame();
		f.setSize(700, 560);
		f.setVisible(true);
		f.show();
	}

}
