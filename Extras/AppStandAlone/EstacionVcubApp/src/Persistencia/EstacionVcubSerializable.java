package persistencia;

import java.io.Serializable;

public class EstacionVcubSerializable implements Serializable{
	
	/**
	 * Constante de serializacion para archivos java.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Id de la estacion de vcubs
	 */
	private Long id;
	/**
	 * Capacidad maxima de la estacion de vcubs.
	 */
	private int capacidad;
	/**
	 * Cantidad actual de vcubs en la estacion.
	 */
	private int vcubs;
	/**
	 * Nombre de la estacion de vcubs.
	 */
	private String nombre;
	/**
	 * Devuelve el id de la estacion
	 * @return Long id de la estacion.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Actualiza el id de la estacion.
	 * @param id Long nuevo id.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Devuelve la capacidad actual de vcubs en la estacion
	 * @return int Capacidad actual de vcubs en la estacion
	 */
	public int getCapacidad() {
		return capacidad;
	}
	/**
	 * Actualiza la capacidad de vcubs de la estacin estacion.
	 * @param id Long nuevo id.
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	/**
	 * Devuelve la capacidad de vcubs de la estacion
	 * @return int cantidad actual de vcubs
	 */
	public int getVcubs() {
		return vcubs;
	}
	/**
	 * Actualiza el id de la estacion.
	 * @param id Long nuevo id.
	 */
	public void setVcubs(int vcubs) {
		this.vcubs = vcubs;
	}
	/**
	 * Devuelve el nombre de la estacion.
	 * @return String nombre de la estacion
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Actualiza el nombre de la estacion.
	 * @param nombre String nuevo nombre.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
