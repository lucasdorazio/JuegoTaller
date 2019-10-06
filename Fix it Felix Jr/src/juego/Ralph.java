package juego;

public class Ralph implements Desplazable{

	private static final int LADRILLOS_POR_TIRADA = 3;
	private static final double TIEMPO_ENTRE_LADRILLOS=0.5;
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

	public Posicion getPosicion() {
		return this.pos;
	}

	public void setLadrillos(int ladrillos) {
		this.ladrillosTotales = ladrillos;
	}

	public void setPosicion(Posicion pos) {
		this.pos = pos;
	}

	public Ralph() {
		this.ladrillosTotales = 40;
		this.pos =new Posicion(30,100);// cambiar valor
		this.timer=0;
		//this.velocidad=?
	}

	public boolean golpearEdif() {							//Retorna true cuando no tiene que tirar mas ladrillos
		timer++;
		if (timer>TIEMPO_ENTRE_LADRILLOS*10000) {												// timer se incrementa 10000 veces por segundo
			ControladorDeLadrillos.generarLadrillo(new Posicion(pos.getPosX()+(ladrillosRestantes*15)-30,pos.getPosY()));			//Genero ladrillos a 15, 0 y -15 pixeles de Ralph
			ladrillosRestantes--;																//Revisar coordY de la posicion
			timer=0;
		}
		return (ladrillosRestantes==0);
	}
	
	public void comenzarGolpeo() {
		ladrillosRestantes=LADRILLOS_POR_TIRADA;
		golpearEdif();
	}

	public void comenzarMovimiento(int cantPasos, Direcciones direccion) {
		pasosRestantes = cantPasos * 15; // Un paso es igual a 15 pixeles
		dirActual = direccion;
		this.avanzar();
	}

	public boolean avanzar() { // Devuelve true cuando no tiene que avanzar mas
		timer++;
		if (timer > 10000/velocidad) { 
			if (dirActual == Direcciones.DERECHA) {
				if (pos.getPosX() + 1 > Juego.getLimiteDerechoEdificio()) {
					dirActual = Direcciones.IZQUIERDA;
					pos.disminuirPosX();
				} else
					pos.aumentarPosX();
			} else if (pos.getPosX()-1 < Juego.getLimiteIzquierdaEdificio()) {
				dirActual = Direcciones.DERECHA;
				pos.aumentarPosX();
			} else
				pos.disminuirPosX();
			pasosRestantes--;
			timer=0;
		}
		return (pasosRestantes == 0);
	}

	public Posicion getPos() {
		return this.pos;
	}

	public void setPos(Posicion pos) {
		this.pos=pos;
	}

	public Direcciones obtenerDireccion() {
		return this.dirActual;
	}

}
