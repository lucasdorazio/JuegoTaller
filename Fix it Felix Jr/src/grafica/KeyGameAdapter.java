package grafica;

import java.applet.AudioClip;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import entidades.Direcciones;
import entidades.Felix;
import juego.Juego;

public class KeyGameAdapter extends KeyAdapter {
	
	private AudioClip sonidoP, direccionInvalida, golpe, felixGolpeado1, felixGolpeado2, ventanaReparada;
	
	public KeyGameAdapter() {
		sonidoP= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/notaA.wav"));
		direccionInvalida= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/direccionNoPermitida.wav"));
		golpe= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/golpe.wav"));
		felixGolpeado1= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/felixGolpeado1.wav"));
		felixGolpeado2= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/felixGolpeado2.wav"));
		ventanaReparada= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/ventanaReparada1.wav"));
	}
	
	public void keyPressed(KeyEvent tecla) {
		switch (tecla.getKeyCode()) {
		case 37:
			Felix.getInstance().mover(Direcciones.IZQUIERDA);
			direccionInvalida.play();
			break;
		case 38:
			Felix.getInstance().mover(Direcciones.ARRIBA);
			golpe.play();
			break;
		case 39:
			Felix.getInstance().mover(Direcciones.DERECHA);
			felixGolpeado1.play();
			break;
		case 40:
			Felix.getInstance().mover(Direcciones.ABAJO);
			felixGolpeado2.play();
			break;
		case 80:
			System.out.println("Se apreto la p");
			sonidoP.play();
			break;
		case 32:
			Felix.getInstance().reparar();
			ventanaReparada.play();
			break;
		case 72: //H= hack para pasar de seccion si no hay ventanas rotas
			Juego.getInstance().comprobarSeccionLimpia(Felix.getInstance().getSeccionActual());
			break;
		case 87: //w voy arriba aunque haya obstaculo (tira error)
			Felix.getInstance().setVentanaActual(Felix.getInstance().getSeccionActual().getVentanas()[Felix.getInstance().getVentanaActual().getNroFila()-1][Felix.getInstance().getVentanaActual().getNroColumna()]);
			break;
		case 65: //A voy a la izquierda aunque haya obstaculo (tira error)
			Felix.getInstance().setVentanaActual(Felix.getInstance().getSeccionActual().getVentanas()[Felix.getInstance().getVentanaActual().getNroFila()][Felix.getInstance().getVentanaActual().getNroColumna()-1]);
			break;
		case 83: //S voy abajo aunque haya obstaculo (tira error)
			Felix.getInstance().setVentanaActual(Felix.getInstance().getSeccionActual().getVentanas()[Felix.getInstance().getVentanaActual().getNroFila()+1][Felix.getInstance().getVentanaActual().getNroColumna()]);
			break;
		case 68: //D voy a la derecha aunque haya obstaculo (tira error)
			Felix.getInstance().setVentanaActual(Felix.getInstance().getSeccionActual().getVentanas()[Felix.getInstance().getVentanaActual().getNroFila()][Felix.getInstance().getVentanaActual().getNroColumna()+1]);
			break;
		case 46:
			Felix.getInstance().reparar();
			break;
		default:
			System.out.println("otra tecla: " + tecla.getKeyCode());
			break;
		}
	}
}


//AudioClip sonido;
//sonido= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/ventanaReparada1.wav"));
//sonido.play();