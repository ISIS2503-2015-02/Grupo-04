package persistencia;

import java.io.Serializable;

public class EstacionVcubSerializable implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private int capacidad;
	private int vcubs;
	private String nombre;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getVcubs() {
		return vcubs;
	}
	public void setVcubs(int vcubs) {
		this.vcubs = vcubs;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
