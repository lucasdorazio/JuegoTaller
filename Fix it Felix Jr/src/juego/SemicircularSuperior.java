package juego;

public class SemicircularSuperior extends Semicircular {
	
	private Panel[] paneles = new Panel[8];
	
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
	public boolean puedeAtravesarseLateralmente() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public char queSoy() {
		return 'S';
	}
	
	public String estadoPaneles() {
		String cad="Paneles: ";
		for (int i=0;i<this.paneles.length;i++) {
			switch (this.paneles[i].getEstado()) {
			case MEDIO_ROTO: cad=cad + "m, ";break;
			case ROTO: cad=cad + "r, ";break;
			case SANO: cad=cad + "s, ";break;
			}
		}
		return cad;
	}

}
