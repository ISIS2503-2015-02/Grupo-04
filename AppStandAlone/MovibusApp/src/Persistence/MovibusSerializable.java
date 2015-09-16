package Persistence;
import java.io.Serializable;

public class MovibusSerializable implements Serializable{

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

	private static final long serialVersionUID = 1L;
	private Long id;
	private int estad;
	private Double posicionLat;
	private Double posicionLong;
	private int kilometraje;

	public MovibusSerializable()
	{
		this.estad=DISPONIBLE;
	}
	
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
