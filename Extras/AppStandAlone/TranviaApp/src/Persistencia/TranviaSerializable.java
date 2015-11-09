package persistencia;

import java.io.Serializable;

public class TranviaSerializable implements Serializable{
	/**
	 * Constante de serializacion del tranvia.
	 */
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
    
    /**
     * Devuelve el id del tranvia.
     * @return Long id del tranvia.
     */
    public Long getId() {
		return id;
	}

    /**
     * Actualiza el id del tranvia.
     * @param id Long nuevo id.
     */
	public void setId(Long id) {
		this.id = id;
	}

    /**
     * Devuelve la latitud del tranvia.
     * @return Double latitud del tranvia.
     */
    public Double getLatitud() {
		return latitud;
	}

    /**
     * Actualiza la latitud del tranvia.
     * @param latitud Double nueva latitud.
     */
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

    /**
     * Devuelve la posicion - Longitud del tranvia.
     * @return Double Longitud del tranvia.
     */
	public Double getLongitud() {
		return longitud;
	}

	/**
	 * Actualiza la posicion-longitud del tranvia
	 * @param longitud Double nueva Longitud.
	 */
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

    /**
     * Devuelve el estado del tranvia.
     * @return int constante de estado del tranvia.
     */
	public int getEstado() {
		return estado;
	}

	/**
	 * Actualiza el estado del tranvia.
	 * @param estado Int nueva cosntante de estado del tranvia.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

    /**
     * Devuelve el kilometraje del tranvia.
     * @return int kilometraje del tranvia.
     */
	public int getKilometraje() {
		return kilometraje;
	}

	/**
	 * Actualiza el kilometraje del tranvia.
	 * @param kilometraje int nuevo kilometraje.
	 */
	public void setKilometraje(int kilometraje) {
		this.kilometraje = kilometraje;
	}

    /**
     * Devuelve la linea del tranvia.
     * @return int linea del tranvia.
     */
	public int getLinea() {
		return linea;
	}

	/**
	 * Actualiza la linea del tranvia.
	 * @param linea int nueva linea del tranvia
	 */
	public void setLinea(int linea) {
		this.linea = linea;
	}
}
