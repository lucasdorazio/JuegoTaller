package juego;

/**
 * Clase que modela los parámetros de la dificultad según el número de nivel en el que esté el jugador
 * @author Lucas Dorazio & Renzo Quaggia
 *
 */
public class Nivel {
	private static final double PROB_VENT_CERRADA=0.066;
	
	private int nroNivel;
	
	private int tiempoMax;
	
	public Nivel (int nroNivel) {
		this.nroNivel=nroNivel;
		this.tiempoMax=Dificultad.getTiempo(nroNivel);
	}
	
	/**
	 * Genera un nuevo edificio en donde se desplazarán Felix y las demás entidades
	 */
	public void generarEdificio() {
		Seccion[] secciones = new Seccion[3];
		secciones[0] = generarPlantaBaja(Dificultad.getProbPanelesRotos(nroNivel),
				Dificultad.getProbObstaculos(nroNivel), Dificultad.getProbVentAbierta(nroNivel));
		secciones[1] = generarSeccion(Dificultad.getProbPanelesRotos(nroNivel),
				Dificultad.getProbObstaculos(nroNivel), Dificultad.getProbVentAbierta(nroNivel));
		secciones[2] = generarSeccion(Dificultad.getProbPanelesRotos(nroNivel),
				Dificultad.getProbObstaculos(nroNivel), Dificultad.getProbVentAbierta(nroNivel));
		Edificio.getInstance().setSecciones(secciones);
		Edificio.getInstance().guardarCopiaSecciones();
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
				if (!esCerrada) {
					/* como se obtiene 4 veces una probabilidad por ventana, la probabilidad debe
					 *  dividirse en 4. Se obtiene 4 veces. Dos por cada panel (roto y medio roto)
					 */
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
					matriz[m][n]= new Comun(m, n, macetero, moldura, inferior, superior);
				}
			}
		}
		
		for (int i=0; i<3;i++) {
			for (int j=0;j<5;j++) {
				if (!matriz[i][j].estoySana()) ventanasRotas++;
			}
		}
		return new Seccion(ventanasRotas, matriz);
	}
	
	private Seccion generarPlantaBaja(double probPanelRoto, double probObstaculo,
			double probVentAbierta) {
		Ventana[][] matriz = new Ventana[3][5];
		EstadoPanel panel0, panel1;
		boolean moldura = false;
		boolean macetero = false;
		panel0 = EstadoPanel.SANO;
		panel1 = EstadoPanel.SANO;
		int ventanasRotas = 0;
		//se llevara una cantidad de ventanas rotas para no tener que recorrer todo el tiempo la matriz preguntando si ya estan todas sanas
		for (int m = 0; m < 3; m++) {
			for (int n = 0; n < 5; n++) {
				if (m == 1 && n == 2) {//semi superior 8
					generarSemiSup(matriz,m,n, probPanelRoto);
				} else if (m == 2 && n == 2) {//puerta
					generarPuerta(matriz,m,n, probPanelRoto);
				} else {// ventana de dos paneles
					if (Math.random() <= probObstaculo / 2) {
						macetero = true;
					}
					if (Math.random() <= probObstaculo / 2) {
						moldura = true;
					}
					if (Math.random() <= PROB_VENT_CERRADA) {
						matriz[m][n] = new ConHojas(m, n, macetero, moldura, panel0, panel1, false);
					} else { // ventana con paneles posiblemente rotos
						if (Math.random() <= probPanelRoto / 4) {
							panel0 = EstadoPanel.ROTO;
						} else if (Math.random() <= probPanelRoto / 4) {
							panel0 = EstadoPanel.MEDIO_ROTO;
						}
						if (Math.random() <= probPanelRoto / 4) {
							panel1 = EstadoPanel.ROTO;
						} else if (Math.random() <= probPanelRoto / 4) {
							panel1 = EstadoPanel.MEDIO_ROTO;
						}
						if (Math.random() <= probVentAbierta) {
							matriz[m][n] = new ConHojas(m, n, macetero, moldura, panel0, panel1, true);
						} else {
							matriz[m][n] = new Comun(m, n, macetero, moldura, panel0, panel1);
						}

					}
				}
			}
		}
		for (int i=0; i<3;i++) {
			for (int j=0;j<5;j++) {
				if (!matriz[i][j].estoySana()) ventanasRotas++;
			}
		}
		return new Seccion(ventanasRotas, matriz);
	}

	private void generarPuerta(Ventana[][] puerta, int m, int n, double probPanelRoto) {
		EstadoPanel panel0 = EstadoPanel.SANO;
		EstadoPanel panel1 = EstadoPanel.SANO;
		EstadoPanel panel2 = EstadoPanel.SANO;
		EstadoPanel panel3 = EstadoPanel.SANO;
		if (Math.random() <= probPanelRoto / 8) {
			panel0 = EstadoPanel.ROTO;
		} else if (Math.random() <= probPanelRoto / 8) {
			panel0 = EstadoPanel.MEDIO_ROTO;
		}
		if (Math.random() <= probPanelRoto / 8) {
			panel1 = EstadoPanel.ROTO;
		} else if (Math.random() <= probPanelRoto / 8) {
			panel1 = EstadoPanel.MEDIO_ROTO;
		}
		if (Math.random() <= probPanelRoto / 8) {
			panel2 = EstadoPanel.ROTO;
		} else if (Math.random() <= probPanelRoto / 8) {
			panel2 = EstadoPanel.MEDIO_ROTO;
		}
		if (Math.random() <= probPanelRoto / 8) {
			panel3 = EstadoPanel.ROTO;
		} else if (Math.random() <= probPanelRoto / 8) {
			panel3 = EstadoPanel.MEDIO_ROTO;
		}
		puerta [m][n]= new Puerta(2, 2, false, false, panel0, panel1, panel2, panel3);
	}
	
	private void generarSemiSup(Ventana[][] matriz, int m, int n, double probPanelRoto) {
		EstadoPanel panel0 = EstadoPanel.SANO;
		EstadoPanel panel1 = EstadoPanel.SANO;
		EstadoPanel panel2 = EstadoPanel.SANO;
		EstadoPanel panel3 = EstadoPanel.SANO;
		EstadoPanel panel4 = EstadoPanel.SANO;
		EstadoPanel panel5 = EstadoPanel.SANO;
		EstadoPanel panel6 = EstadoPanel.SANO;
		EstadoPanel panel7 = EstadoPanel.SANO;
		if (Math.random() <= probPanelRoto / 16) {
			panel0 = EstadoPanel.ROTO;
		} else if (Math.random() <= probPanelRoto / 16) {
			panel0 = EstadoPanel.MEDIO_ROTO;
		}
		if (Math.random() <= probPanelRoto / 16) {
			panel1 = EstadoPanel.ROTO;
		} else if (Math.random() <= probPanelRoto / 16) {
			panel1 = EstadoPanel.MEDIO_ROTO;
		}
		if (Math.random() <= probPanelRoto / 16) {
			panel2 = EstadoPanel.ROTO;
		} else if (Math.random() <= probPanelRoto / 16) {
			panel2 = EstadoPanel.MEDIO_ROTO;
		}
		if (Math.random() <= probPanelRoto / 16) {
			panel3 = EstadoPanel.ROTO;
		} else if (Math.random() <= probPanelRoto / 16) {
			panel3 = EstadoPanel.MEDIO_ROTO;
		}
		if (Math.random() <= probPanelRoto / 16) {
			panel4 = EstadoPanel.ROTO;
		} else if (Math.random() <= probPanelRoto / 16) {
			panel4 = EstadoPanel.MEDIO_ROTO;
		}
		if (Math.random() <= probPanelRoto / 16) {
			panel5 = EstadoPanel.ROTO;
		} else if (Math.random() <= probPanelRoto / 16) {
			panel5 = EstadoPanel.MEDIO_ROTO;
		}
		if (Math.random() <= probPanelRoto / 16) {
			panel6 = EstadoPanel.ROTO;
		} else if (Math.random() <= probPanelRoto / 16) {
			panel6 = EstadoPanel.MEDIO_ROTO;
		}
		if (Math.random() <= probPanelRoto / 16) {
			panel7 = EstadoPanel.ROTO;
		} else if (Math.random() <= probPanelRoto / 16) {
			panel7 = EstadoPanel.MEDIO_ROTO;
		}
		matriz[m][n] = new SemicircularSuperior(1, 2, false, false, panel0, panel1, panel2, panel3, panel4, panel5, panel6, panel7);
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
