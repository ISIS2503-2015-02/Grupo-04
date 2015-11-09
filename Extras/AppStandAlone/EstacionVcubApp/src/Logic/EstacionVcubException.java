package logic;

public class EstacionVcubException extends Exception{

	/**
	 * Constante de serializacion.
	 */
	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	/**
	 * El error que se guarda en la excepcion.
	 */
    private String error;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor de la Excepcion.
     * @param pError Error a guardar en la excepcion.
     */
    public EstacionVcubException( String pError) {
        super( pError );
        error=pError;
    }

    /**
     * Devuelve el error guardado en la excepcion.
     * @return String con el error de la excepcion
     */
    public String darError( )
    {
        return error;
    }
}
