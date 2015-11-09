package logic;

/**
 * Clase de manejo del log de la aplicacion de tranvia.
 *
 */
public class Logger {

	/**
	 * Constructor vacio del Logger.
	 */
	private Logger() {
	}

	/**
	 * Imprime excepciones en la consola de eclipse
	 * @param e excepcion a imprimer en la consola
	 */
	public static void info(Exception e){
		System.out.println(e);
	}
}
