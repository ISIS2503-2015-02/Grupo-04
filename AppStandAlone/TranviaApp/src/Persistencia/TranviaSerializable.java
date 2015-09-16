package Persistencia;

import java.io.Serializable;

public class TranviaSerializable implements Serializable{
	
	private static final long serialVersionUID = 1L;

    //-----------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------

    /**
     * Constante que representa el estado ocupado
     */
    public final static int OCUPADO=0;

    /**
     * Constante que representa el estado disponible
     */
    public final static int DISPONIBLE=1;

    /**
     * Constante que representa el estado problema
     */
    public final static int PROBLEMA=2;

    /**
     * Constante que representa la linea
     */
    public final static int LINEA_A=0;

    /**
     * Constante que representa la linea
     */
    public final static int LINEA_B=1;

    /**
     * Constante que representa la linea
     */
    public final static int LINEA_C=2;

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
    private Long latitud;
    //Longitud
    private Long longitud;
    
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

    public Long getLatitud() {
		return latitud;
	}

	public void setLatitud(Long latitud) {
		this.latitud = latitud;
	}

	public Long getLongitud() {
		return longitud;
	}

	public void setLongitud(Long longitud) {
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
