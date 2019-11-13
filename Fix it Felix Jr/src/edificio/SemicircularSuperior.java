package edificio;

import entidades.Direcciones;

/**
 * Ventana con 8 paneles que aparece en la primer sección de cada nivel
 * @author Lucas Dorazio & Renzo Quaggia
 */
public class SemicircularSuperior extends Semicircular {
	
	private Panel[] paneles = new Panel[8];
	
	/**
	 * Inicializa atributos
	 * @param nroFila atributo general de todas las ventanas
	 * @param nroCol atributo general de todas las ventanas
	 * @param tieneMacetero atributo general de todas las ventanas
	 * @param tieneMoldura atributo general de todas las ventanas
	 * @param panel0-7 indican el estado de cada uno de sus 8 paneles
	 */
	public SemicircularSuperior(int nroFila, int nroCol, boolean tieneMacetero, boolean tieneMoldura,
			EstadoPanel panel0, EstadoPanel panel1, EstadoPanel panel2, EstadoPanel panel3,
			EstadoPanel panel4, EstadoPanel panel5, EstadoPanel panel6, EstadoPanel panel7){
		super(nroFila, nroCol, tieneMacetero, tieneMoldura);
		paneles[0]= new Panel(panel0);
		paneles[1]= new Panel(panel1);
		paneles[2]= new Panel(panel2);
		paneles[3]= new Panel(panel3);
		paneles[4]= new Panel(panel4);
		paneles[5]= new Panel(panel5);
		paneles[6]= new Panel(panel6);
		paneles[7]= new Panel(panel7);		
	}
	
	/**
	 * Indica la forma en que se reparar sus paneles
	 */
	@Override
	public void repararse() {
		boolean seReparo = false;
		int i = 0;
		while (!seReparo && i<8) {
			if (paneles[i].getEstado() != EstadoPanel.SANO) {
				paneles[i].repararse();
				seReparo = true;
				// los paneles de 0 a 3 abajo, 4 a 7 arriba
			}
			i++;
		}

	}
	
	/**
	 * Determina si es sana o no según si sus 8 paneles sean sanos
	 */
	@Override
	public boolean estoySana() {
		return (paneles[0].getEstado()==EstadoPanel.SANO &&
				paneles[1].getEstado()==EstadoPanel.SANO &&
				paneles[2].getEstado()==EstadoPanel.SANO &&
				paneles[3].getEstado()==EstadoPanel.SANO &&
				paneles[4].getEstado()==EstadoPanel.SANO &&
				paneles[5].getEstado()==EstadoPanel.SANO &&
				paneles[6].getEstado()==EstadoPanel.SANO &&
				paneles[7].getEstado()==EstadoPanel.SANO);
	}

	@Override
	public boolean puedePasar(Direcciones dir) {
		boolean puede=false;
		switch (dir) {
		case DERECHA:
			if (getNroColumna() != 4) puede=true;
			break;
		case IZQUIERDA:
			if (getNroColumna() != 0) puede=true;
			break;
		case ARRIBA:
			if ((getNroFila() != 0) && (!tieneMoldura())) puede=true;
			break;
		case ABAJO:
			if ((getNroFila() != 2) && (!tieneMacetero())) puede=true;
			break;
		}
		return puede;
	}

	@Override
	public EstadoPanel[] getEstadoPaneles() {
		EstadoPanel[] estados = new EstadoPanel[7];
		estados[0]=this.paneles[0].getEstado();
		estados[1]=this.paneles[1].getEstado();
		estados[2]=this.paneles[2].getEstado();
		estados[3]=this.paneles[3].getEstado();
		estados[4]=this.paneles[4].getEstado();
		estados[5]=this.paneles[5].getEstado();
		estados[6]=this.paneles[6].getEstado();
		estados[7]=this.paneles[7].getEstado();
		return estados;
	}
}
