package juego;

public class Ralph implements Desplazable{

	private static final int LADRILLOS_POR_TIRADA = 3;
	private static final double TIEMPO_ENTRE_LADRILLOS=0.5;
	private static final int CONST_TIEMPO=10000;	//Cantidad de llamadas al método por segundo
	private int ladrillosTotales;
	private int ladrillosRestantes;
	private Posicion pos;
	private int velocidad;	//velocidad= x pixeles/seg
	private int timer;
	private int pasosRestantes;
	private Direcciones dirActual;

	public int getLadrillos() {
		return ladrillosTotales;
	}

	public void setLadrillos(int ladrillos) {
		this.ladrillosTotales = ladrillos;
	}

	public void setPosicion(Posicion pos) {
		this.pos = pos;
	}

	public Ralph() {
		this.ladrillosTotales = 40;
		this.pos =new Posicion(350,390);	//Parte superior de la sección, en el centro
		this.velocidad=150;
	}
	
	public void comenzarGolpeo() {
		ladrillosRestantes=LADRILLOS_POR_TIRADA;
		this.timer=0;
		golpearEdif();
	}

	public void comenzarMovimiento(int cantPasos, Direcciones direccion) {
		pasosRestantes = cantPasos * 46; // Un paso es igual a 46 pixeles
		dirActual = direccion;
		this.timer=0;
		this.avanzar();
	}
	
	public boolean golpearEdif() {							//Retorna true cuando no tiene que tirar mas ladrillos
		timer++;
		if (timer>TIEMPO_ENTRE_LADRILLOS*CONST_TIEMPO) {			
			ControladorDeLadrillos.generarLadrillo(new Posicion(pos.getPosX()+(ladrillosRestantes*15)-30,340));			//Genero ladrillos a 15, 0 y -15 pixeles de Ralph
			ladrillosRestantes--;			
			timer=0;
		}
		return (ladrillosRestantes==0);
	}

	@Override
	public boolean avanzar() { // Devuelve true cuando no tiene que avanzar mas
		timer++;
		if (timer > CONST_TIEMPO/velocidad) { 
			if (dirActual == Direcciones.DERECHA) {
				if (pos.getPosX() + 1 > Edificio.getLimiteDerechoEdificio()) {
					dirActual = Direcciones.IZQUIERDA;
					pos.disminuirPosX();
				} else
					pos.aumentarPosX();
			} else if (pos.getPosX()-1 < Edificio.getLimiteIzquierdaEdificio()) {
				dirActual = Direcciones.DERECHA;
				pos.aumentarPosX();
			} else
				pos.disminuirPosX();
			pasosRestantes--;
			timer=0;
		}
		return (pasosRestantes == 0);
	}

	@Override
	public Posicion getPos() {
		return this.pos;
	}

	@Override
	public void setPos(Posicion pos) {
		this.pos=pos;
	}

	@Override
	public Direcciones obtenerDireccion() {
		return this.dirActual;
	}

}
