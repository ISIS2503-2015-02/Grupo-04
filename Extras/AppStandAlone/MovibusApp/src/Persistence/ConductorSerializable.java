package persistence;

public class ConductorSerializable {

    //----------------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------------

	/**
	 * id unico del conductor por el cual esta registrado en la base de datos del servidor
	 */
    private Long id;

    /**
     * Nombre del conductor actual
     */
    private String nombre;

    /**
     * Numero de la cedula del conductor actual
     */
	private int cedula;

	/**
	 * Numero del celular del constructor actual
	 */
    private int celular;

    //----------------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------------
    
    /**
     * Constructor con parametros de la clase
     * @param nombre nombre del conductor
     * @param cedula numero de la cedula del conductor
     * @param celular numero del celular del conductor
     */
    public ConductorSerializable(String nombre, int cedula, int celular) {
        this.nombre=nombre;
        this.cedula=cedula;
        this.celular=celular;
    }

    public ConductorSerializable() {
		
	}

    //----------------------------------------------------------------------------
    // Metodos  GETTERS - SETTERS
    //----------------------------------------------------------------------------

    public void setNombre(String nombre) {
        this.nombre = nombre;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getCedula() {
        return cedula;
    }

    public int getCelular() {
        return celular;
    }
    
    public void setCelular(int celular) {
        this.celular=celular;
    }

}
