package logic;
/**
 * Excepcion de la aplicacion tranvia
 *
 */
public class TranviaException extends Exception{

	/**
	 * Constante de serializacion de la clase
	 */
	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	/**
	 * Error de la excepcion
	 */
    private String error;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepcion dado el mensaje de error
     * @param pError String mensaje de error
     */
    public TranviaException( String pError) {
        super( pError );
        error=pError;
    }

    /**
     * Devuelve el error dentro de la excepcion
     * @return String mensaje de error.
     */
    public String darError( )
    {
        return error;
    }
}
