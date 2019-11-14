package juego;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class PruebasRen {
	static HiloIndependiente  hiloMovimiento= new HiloIndependiente();
	static HiloDependiente hiloGolpeo= new HiloDependiente(hiloMovimiento);

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
		
		TimerTask movimiento= new TimerTask() {		
			@Override
			public void run() {
				if (!hiloMovimiento.isAlive() && !hiloGolpeo.isAlive()) {
					hiloMovimiento= new HiloIndependiente();
					hiloMovimiento.start();
				}
			}
		};
		TimerTask golpeo= new TimerTask() {			
			@Override
			public void run() {
				/*if (movimiento.)
=======
>>>>>>> branch 'master' of https://github.com/lucasdorazio/JuegoTaller.git
				if (!hiloGolpeo.isAlive() && !hiloMovimiento.isAlive()) {
					hiloGolpeo= new HiloDependiente(hiloMovimiento);
					hiloGolpeo.start();
				}*/
			}
		};
		timer.schedule(movimiento, 0, 1000);
		timer.schedule(golpeo, 0, 2000);
	}
	

	
}
@SuppressWarnings("serial")
class Marco extends JFrame {
	public Marco() {
		setBounds(100, 100, 200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class HiloDependiente extends Thread {
	Thread t;
	int i=0;
	public HiloDependiente (Thread t) {
		this.t=t;
	}
	
	public void run() {
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean termino=false;
		while (!termino) {
			System.out.println("dependiente="+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			if (i==10) termino=true;
		}
		System.out.println("termino exitosamente");
	}
}
class HiloIndependiente extends Thread{
	public void run() {
		int i=0;
		boolean termino=false;
		while (!termino) {
			System.out.println("solari="+i);
			try {
				Thread.sleep(200);	//(int) (1000/ralph.velocidad())
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			if (i==10) termino=true;
		}
		System.out.println("termino exitosamente");
		
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