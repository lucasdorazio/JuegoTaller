package juego;

/**
 * Clase que modela a la persona que jugará este juego a través de un nickname y los puntos que logre
 * @author Lucas Dorazio & Renzo Quaggia
 */
public class Jugador {
	
	private String nick;
	
	private int puntaje;

	public Jugador(String nick) {
		this.nick=nick;
	}
	public String getNick() {
		return nick;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	public void sumarPuntaje(int puntos) {
		this.puntaje+=puntos;
	}
		

}
