package juego;

public class Nivel {
	
	private int nroNivel;
	
	private int multDificultad;
	
	private int tiempoMax;
	
	public Edificio generarEdificio() {
		Seccion seccion1= generarSeccion(Dificultad.getPanelesRotos(multDificultad),
				Dificultad.getCantObstaculos(multDificultad));
		//hacer metodo generarPlantaBaja
		
		
		Edificio ed = new Edificio();
		return ed;
	}
	
	private Seccion generarSeccion(int cantPanelesRotos, int cantObstaculos) {
		int posRoto;
		Ventana[][] ventanas= new Ventana[3][5];
		boolean [] rotos= new boolean [30]; 
		while (cantPanelesRotos>0) {
			posRoto= (int) (Math.random()*30);		//Esto debe dar entre 0 y 29
			if (!rotos[posRoto]) {
				rotos[posRoto]=true;
				cantPanelesRotos--;
			}
		}
		while (cantObstaculos)
	}
	/*3 impar=superior, par=inferior
	ventana [posRoto/10] [ (posRoto%10)/2 ]	
	
			primer fila 0 -9
		0	0-1 - 10-11 - 20-21
		1	2-3   12-13   22-23
		2	4-5
		3	6-7
		4	8-9
	*/
	public int getNroNivel() {
		return nroNivel;
	}
	public int getMultDificultad() {
		return multDificultad;
	}
	public int getTiempoMax() {
		return tiempoMax;
	}
	public void setNroNivel(int nroNivel) {
		this.nroNivel = nroNivel;
	}
	public void setMultDificultad(int multDificultad) {
		this.multDificultad = multDificultad;
	}
	public void setTiempoMax(int tiempoMax) {
		this.tiempoMax = tiempoMax;
	}
	

}
