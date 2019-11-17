package juego;

public enum EstadoJuego {
	SIN_INICIAR,
	NORMAL,
	PAUSA,
	AVANCE_SECCION,
	AVANCE_NIVEL,
	PERDER,
	GANAR,
	REINICIO_SECCION,
	REINICIO_NIVEL		//Puede ser que reinicioSeccion y nivel sean iguales
}
