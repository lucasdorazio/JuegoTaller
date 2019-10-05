package juego;

public class Nivel {
	
	private static final double PROB_VENT_CERRADA=0.066;
	
	private int nroNivel;
	
	private int tiempoMax;
	
	public Edificio generarEdificio() {
		Seccion seccion1 = generarSeccion(Dificultad.getProbPanelesRotos(nroNivel),
				Dificultad.getProbObstaculos(nroNivel), Dificultad.getProbVemtAbierta(nroNivel));
		Seccion plantaBaja= generarPlantaBaja(Dificultad.getProbPanelesRotos(nroNivel),
				Dificultad.getProbObstaculos(nroNivel), Dificultad.getProbVemtAbierta(nroNivel));
		
		// hacer metodo generarPlantaBaja

		Edificio ed = new Edificio();
		return ed;
	}

	private Seccion generarSeccion(double probPanelRoto, double probObstaculo,
			double probVentAbierta) {
		Ventana[][] matriz = new Ventana[3][5];
		EstadoPanel inferior, superior;
		boolean moldura= false;
		boolean macetero= false;
		boolean esCerrada=false;
		inferior = EstadoPanel.SANO;
		superior = EstadoPanel.SANO;
		int ventanasRotas=0;
		for (int m = 0; m < 3; m++) {
			for (int n = 0; n < 5; n++) {
				esCerrada=Math.random()<=PROB_VENT_CERRADA;
				if (esCerrada=true) {
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
					if (superior!=EstadoPanel.SANO || inferior!=EstadoPanel.SANO) {
						ventanasRotas++;
					}
				}
				
				if (Math.random()<= probObstaculo /2) {
					macetero=true;
				}
				if (Math.random()<= probObstaculo /2) {
					moldura=true;
				}
	
				if (esCerrada) {
					matriz[m][n]= new ConHojas(m, n, macetero, moldura, inferior, superior, false);
				} else if (Math.random()<=probVentAbierta) {
					matriz[m][n]= new ConHojas(m, n, macetero, moldura, inferior, superior, true);
				} else {
					matriz[m][n]= new Comun(m, n, true, false, inferior, superior);
				}
			}
		}
		return new Seccion(ventanasRotas, matriz);
	}
	
	private Seccion generarPlantaBaja(double probPanelRoto, double probObstaculo,
			double probVentAbierta) {
		Ventana[][] matriz = new Ventana[3][5];
		EstadoPanel inferior, superior;
		boolean moldura= false;
		boolean macetero= false;
		boolean esCerrada=false;
		inferior = EstadoPanel.SANO;
		superior = EstadoPanel.SANO;
		int ventanasRotas=0;
		
		for (int m = 0; m < 3; m++) {
			for (int n = 0; n < 5; n++) {
				if (m==1 && n==2) {
					ventana
				}else if (m==2 && n==2) {
					puerta
				}
				
				esCerrada=Math.random()<=PROB_VENT_CERRADA;
				if (esCerrada=true) {
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
					if (superior!=EstadoPanel.SANO || inferior!=EstadoPanel.SANO) {
						ventanasRotas++;
					}
				}
				
				if (Math.random()<= probObstaculo /2) {
					macetero=true;
				}
				if (Math.random()<= probObstaculo /2) {
					moldura=true;
				}
	
				if (esCerrada) {
					matriz[m][n]= new ConHojas(m, n, macetero, moldura, inferior, superior, false);
				} else if (Math.random()<=probVentAbierta) {
					matriz[m][n]= new ConHojas(m, n, macetero, moldura, inferior, superior, true);
				} else {
					matriz[m][n]= new Comun(m, n, true, false, inferior, superior);
				}
			}
		}
		
		return new Seccion(ventanasRotas, matriz);
	}
		
	public int getNroNivel() {
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
