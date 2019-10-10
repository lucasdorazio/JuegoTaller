package juego;

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
	
	

}
