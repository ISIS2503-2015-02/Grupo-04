package logic;

public class Logger {

	/**
	 * Constructor vacio para el logger de la aplicacion
	 */
	private Logger() {
	}

	/**
	 * Imprime el log de la aplicacion en la consola de java.
	 * @param e Excepcion a imprimir en consola.
	 */
	public static void info(Exception e){
		System.out.println(e);
	}
}
