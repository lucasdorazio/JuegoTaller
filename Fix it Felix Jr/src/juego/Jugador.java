package juego;
/**
 *La clase juego implementa la coneccion entre todas las componentes
 * del juego, conociendo a la mayoria o siendo estas atributos del juego
 * @author Lucas Dorazio & Renzo Quaggia
 * @version 1.0
 * @since 10-10-2019
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
