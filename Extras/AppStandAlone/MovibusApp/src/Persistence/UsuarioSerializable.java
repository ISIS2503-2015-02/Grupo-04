package persistence;

public class UsuarioSerializable {

	//---------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------
	
	private Long id;

    /**
     * Nombre del usuario.
     */
    private String nombre;

	/**
     * Numbero de la cedula.
     */
    private int cedula;

    /**
     * Numero del celular.
     */
    private int celular;


    //---------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------

    public UsuarioSerializable(String nombre,int cedula,int celular) {
        this.nombre=nombre;
        this.cedula=cedula;
        this.celular=celular;
    }

    //---------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------

    public UsuarioSerializable() {
    	
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

    public void setCedula(int cedula) {
    this.cedula=cedula;
}

    public int getCelular() {
        return celular;
    }
    
    public void setCelular(int celular) {
        this.celular=celular;
    }

    public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
