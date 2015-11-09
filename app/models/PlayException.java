package models;

public class PlayException extends Exception{

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

    public PlayException( String pError) {
        super( pError );
        error=pError;
    }

    public String darError( )
    {
        return error;
    }
}
