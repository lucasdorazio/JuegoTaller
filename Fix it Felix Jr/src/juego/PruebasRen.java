package juego;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import controladores.ControladorDePajaro;
import controladores.ControladorDeRalph;
import entidades.Pajaro;

public class PruebasRen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Marco mimarco= new Marco();
		mimarco.setVisible(true);
		Timer timer= new Timer();
		/*ControladorDePajaro cont= new ControladorDePajaro();
		TimerTask task= new TimerTask() {
			
			@Override
			public void run() {
				Pajaro p;
				int i=0;
				Iterator<Pajaro> ite= cont.getListaPajaros().iterator();
				if (!ite.hasNext()) System.out.println("No hay nada en la lista de pajaros"); 
				while (ite.hasNext()) {
					p=ite.next();
					System.out.println("Pajaro "+ i+ " en posicion ("+p.getPos().getPosX()+", "+p.getPos().getPosY()+")");
					i++;
				}
				
			}
		};
		timer.schedule(task, 0, 500);*/
		Thread hilo = new Thread(new Hilo(), "prueba");
		hilo.start();
	}
	
}
@SuppressWarnings("serial")
class Marco extends JFrame {
	public Marco() {
		setBounds(100, 100, 200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Hilo implements Runnable{
	int k=4;
	@Override
	public void run() {
		Timer timer= new Timer();
		TimerTask reducirEnteros= new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("K=" +k);
				k--;
				if (k == 0) this.cancel();
			}
		};
		timer.schedule(reducirEnteros, 0, 1000);
	}
	
}