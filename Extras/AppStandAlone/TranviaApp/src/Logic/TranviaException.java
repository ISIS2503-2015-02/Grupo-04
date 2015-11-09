package logic;

public class TranviaException extends Exception{

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

    public TranviaException( String pError) {
        super( pError );
        error=pError;
    }

    public String darError( )
    {
        return error;
    }
}
