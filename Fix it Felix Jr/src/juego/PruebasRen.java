package juego;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import controladores.ControladorDePajaro;
import entidades.Pajaro;

public class PruebasRen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Marco mimarco= new Marco();
		ControladorDePajaro cont= new ControladorDePajaro();
		TimerTask task= new TimerTask() {
			
			@Override
			public void run() {
				Pajaro p;
				int i=0;
				Iterator<Pajaro> ite= cont.getListaPajaros().iterator();
				if (!ite.hasNext()) System.out.println("No hay nada en la lista de pajaros"); 
				while (ite.hasNext()) {
					p=ite.next();
					System.out.println("Pajaro "+ i+ "en posicion ("+p.getPos().getPosX()+", "+p.getPos().getPosY()+")");
					i++;
				}
				
			}
		};
		Timer timer= new Timer();
		timer.schedule(task, 0, 500);
	}
	
}
class Marco extends JFrame {
	public Marco() {
		setBounds(100, 100, 200, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}