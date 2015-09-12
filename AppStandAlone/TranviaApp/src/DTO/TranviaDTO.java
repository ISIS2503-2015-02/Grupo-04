package DTO;

public class TranviaDTO {
	/**
	 * Id del tranvia.
	 */
	private Long id;
	
	/**
	 * Linea asignada al tranvia.
	 */
	private int linea;
	
	/**
	 * Pos Latitud tranvia
	 */
	private Long lat;
	
	/**
	 * Pos Longitud tranvia
	 */
	private Long lon;
	
	/**
	 * Estado del tranvia.
	 */
	private int estado;
	
	/**
	 * Kilometraje actual del vehiculo
	 */
	private int kmAct;
	
	/**
	 * Kilometraje del vehiculo desde ultima revision.
	 */
	private int kmTotal;
	
	/**
	 * Reporte de emergencia.
	 */
	private ReporteDTO reporte;

	public ReporteDTO getReporte() {
		return reporte;
	}

	public void setReporte(ReporteDTO reporte) {
		this.reporte = reporte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public long getLat() {
		return lat;
	}

	public void setLat(Long lat) {
		this.lat = lat;
	}

	public Long getLon() {
		return lon;
	}

	public void setLon(Long lon) {
		this.lon = lon;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getKmAct() {
		return kmAct;
	}

	public void setKmAct(int kmAct) {
		this.kmAct = kmAct;
	}

	public int getKmTotal() {
		return kmTotal;
	}

	public void setKmTotal(int kmTotal) {
		this.kmTotal = kmTotal;
	}

}