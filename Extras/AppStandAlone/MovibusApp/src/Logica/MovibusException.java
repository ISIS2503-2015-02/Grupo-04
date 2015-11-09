package logica;

public class MovibusException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    private String error;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    public MovibusException( String pError) {
        super( pError );
        error=pError;
    }

    public String darError( )
    {
        return error;
    }
}
