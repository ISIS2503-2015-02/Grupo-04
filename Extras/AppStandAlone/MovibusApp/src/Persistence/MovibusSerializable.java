package persistence;
import java.io.Serializable;

public class MovibusSerializable implements Serializable{

    //----------------------------------------------------------------------------
    // Constantes
    //----------------------------------------------------------------------------

	/**
     * Constante que representa el estado disponible
     */
    public final static int DISPONIBLE=0;

    /**
     * Constante que representa el estado ocupado
     */
    public final static int OCUPADO=1;

    /**
     * Constante que representa el estado problema
     */
    public final static int PROBLEMA=2;

    //----------------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------------

	private static final long serialVersionUID = 1L;
	/**
	 * Id unico del movibus en la base de datos del servior
	 */
	private Long id;
	/*
	 * Estado del movibus actual
	 */
	private int estad;
	/**
	 * Double que representa la posicion latitudinal del movibus actual en Google Maps
	 */
	private Double posicionLat;
	/**
	 * Double que representa la posicion longitudinal del movibus actual en Google Maps
	 */
	private Double posicionLong;
	/**
	 * Valor que representa el kilometraje que recorrio el movibus en total desde el ultimo recorrido registrado por los pedidos
	 */
	private int kilometraje;

    //----------------------------------------------------------------------------
    // Constructor
    //----------------------------------------------------------------------------

	/**
	 * Constructor del movibus sin atributos, este solo inicialisa el movibus sin datos, pero con estado disponible
	 */
	public MovibusSerializable()
	{
		this.estad=DISPONIBLE;
	}

    //----------------------------------------------------------------------------
    // Metodos  GETTERS - SETTERS
    //----------------------------------------------------------------------------

	public Double getPosicionLat() {
		return posicionLat;
	}
	public void setPosicionLat(Double posicionLat) {
		this.posicionLat = posicionLat;
	}
	public Double getPosicionLong() {
		return posicionLong;
	}
	public void setPosicionLong(Double posicionLong) {
		this.posicionLong = posicionLong;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getEstad() {
		return estad;
	}
	public void setEstad(int estad) {
		this.estad = estad;
	}
	public int getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(int kilometraje) {
		this.kilometraje = kilometraje;
	}
	
}
