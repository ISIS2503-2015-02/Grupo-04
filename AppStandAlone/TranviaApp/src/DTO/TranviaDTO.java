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
	private int lat;
	
	/**
	 * Pos Longitud tranvia
	 */
	private int lon;
	
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

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
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
