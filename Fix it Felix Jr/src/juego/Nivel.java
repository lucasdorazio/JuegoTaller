package juego;

public class Nivel {
	
	private int nroNivel;
	
	private int tiempoMax;
	
	public Edificio generarEdificio() {
		Seccion seccion1= generarSeccion(Dificultad.getProbPanelesRotos(nroNivel),
				Dificultad.getProbObstaculos(nroNivel), Dificultad.getProbVemtAbierta(nroNivel));
		//hacer metodo generarPlantaBaja
		
		
		Edificio ed = new Edificio();
		return ed;
	}

	private Seccion generarSeccion(double probPanelRoto, double probObstaculo,
			double probVentAbierta) {
		Ventana v;
		EstadoPanel inferior, superior;
		boolean moldura= false;
		boolean macetero= false;
		inferior = EstadoPanel.SANO;
		superior = EstadoPanel.SANO;
		for (int m = 0; m < 3; m++) {
			for (int n = 0; n < 5; n++) {
				if (Math.random() <= probPanelRoto/ 4) {
					inferior = EstadoPanel.ROTO;
				} else if (Math.random() <= probPanelRoto / 4) {
					inferior = EstadoPanel.MEDIO_ROTO;
				}
				if (Math.random() <= probPanelRoto / 4) {
					superior = EstadoPanel.ROTO;
				} else if (Math.random() <= probPanelRoto/ 4) {
					superior = EstadoPanel.MEDIO_ROTO;
				}
				if (Math.random()<= probObstaculo /2) {
					macetero=true;
				}
				if (Math.random()<= probObstaculo /2) {
					moldura=true;
				}
				if (Math.random()<=probVentAbierta) {
					v= new ConHojas(m, n, macetero, moldura, inferior, superior, true);
				}
				v = new Comun(m, n, true, false, inferior, superior);

			}
		}
	}
		
		
		
		
		/*boolean [] rotos= new boolean [30]; 
		/*int [] cantObstaculosPorVentana = new int[15];
		while (cantObstaculos>0) {
			posObstaculo= ((int) (Math.random()*15));
			if (cantObstaculosPorVentana[posObstaculo]<3) {
				cantObstaculosPorVentana[posObstaculo]++;
				cantObstaculos--;
			}
		}
		*
		int cantMolduras=cantObstaculos/3;
		int cantMaceteros=cantMolduras;
		int cantVentAbiertas=cantObstaculos%3;
		boolean[] molduras= new boolean[15];
		boolean[] ventAbiertas= new boolean[15];
		boolean[] maceteros= new boolean[15];
		while (cantMolduras>0) {
			posicion= (int) (Math.random()*15);		//Esto debe dar entre 0 y 29
			if (!molduras[posicion]) {
				molduras[posicion]=true;
				cantMolduras--;
			}
		}
		
		while (cantMaceteros>0) {
			posicion= (int) (Math.random()*15);		//Esto debe dar entre 0 y 29
			if (!maceteros[posicion]) {
				maceteros[posicion]=true;
				cantMaceteros--;
			}
		}
		
		while (cantVentAbiertas>0) {
			posicion= (int) (Math.random()*15);		//Esto debe dar entre 0 y 29
			if (!ventAbiertas[posicion]) {
				ventAbiertas[posicion]=true;
				cantVentAbiertas--;
			}
		}
		
		while (cantPanelesRotos>0) {
			posRoto= (int) (Math.random()*30);		//Esto debe dar entre 0 y 29
			if (!rotos[posRoto]) {//preguntar cerradas
				rotos[posRoto]=true;
				cantPanelesRotos--;
			}
		}
		
	}
	/*3 impar=superior, par=inferior
	ventana [posRoto/10] [ (posRoto%10)/2 ]	
	
			primer fila 0 -9
		0	0-1 - 10-11 - 20-21
		1	2-3   12-13   22-23
		2	4-5
		3	6-7
		4	8-9
	/
	*/
	}
	public int getNroNivel() {
		boolean macetero=false;
		return nroNivel;
	}
	public int getTiempoMax() {
		return tiempoMax;
	}
	public void setNroNivel(int nroNivel) {
		this.nroNivel = nroNivel;
	}
	public void setTiempoMax(int tiempoMax) {
		this.tiempoMax = tiempoMax;
	}
	

}
