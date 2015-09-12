package DTO;

public class ReporteDTO {
	
	/**
	 * El tipo de reporte.
	 */
	private String tipoReporte;
	
	/**
	 * Una breve descripcion del reporte
	 */
	private String descripcion;

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
