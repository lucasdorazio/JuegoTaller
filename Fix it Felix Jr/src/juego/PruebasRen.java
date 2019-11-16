package juego;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import excepciones.ImproperNameException;

public class PruebasRen {
	static HiloIndependiente  hiloMovimiento= new HiloIndependiente();
	static HiloDependiente hiloGolpeo= new HiloDependiente(hiloMovimiento);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Marco mimarco= new Marco();
//		mimarco.setVisible(true);
		boolean nombreCorrecto=false;
		while (!nombreCorrecto) {
			try {
				Juego.getInstance().pedirNombre();
				nombreCorrecto = true;
			} catch (ImproperNameException e) {
				System.out.println("ERROR: " + e.toString());
			}
		}
		System.out.println("Salio del while");
		System.out.println("Salio del while 2");
		
	}
	
	public static int obtenerNumero() {
		double valor=100;
		double multiplicador= 1 - (double) 9/10;
		System.out.println(multiplicador);
		System.out.println(multiplicador);
		return (int) (valor * multiplicador);
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