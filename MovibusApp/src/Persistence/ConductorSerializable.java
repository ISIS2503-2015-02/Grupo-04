package Persistence;

public class ConductorSerializable {

    private Long id;

    private String nombre;

	private int cedula;

    private int celular;

    public ConductorSerializable(String nombre, int cedula, int celular) {
        this.nombre=nombre;
        this.cedula=cedula;
        this.celular=celular;
    }

    public ConductorSerializable() {
		// TODO Auto-generated constructor stub
	}


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
