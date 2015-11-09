package logic;

public class EstacionVcubException extends Exception{

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

    public EstacionVcubException( String pError) {
        super( pError );
        error=pError;
    }

    public String darError( )
    {
        return error;
    }
}
