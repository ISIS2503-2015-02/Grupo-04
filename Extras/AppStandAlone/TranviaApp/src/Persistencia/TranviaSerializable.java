package persistencia;

import java.io.Serializable;

public class TranviaSerializable implements Serializable{
	
	private static final long serialVersionUID = 1L;

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Id del tranvia
     */
    private Long id;

    /**
     * Posicion del vehiculo
     */
    //Latitud
    private Double latitud;
    
    //Longitud
    private Double longitud;
    
    /**
     * Estado del vehiculo
     */
    private int estado;

    /**
     * Kilometraje del vehiculo transcurrido desde revision
     */
    private int kilometraje;

    /**
     * Linea asignada al tranvia
     */
    private int linea;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(int kilometraje) {
		this.kilometraje = kilometraje;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}
}
