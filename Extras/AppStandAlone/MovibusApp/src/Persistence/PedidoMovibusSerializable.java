package persistence;

import java.io.Serializable;
import java.util.Date;
public class PedidoMovibusSerializable implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date fechaPedido;
    
    private Date fechaEjecucion;
    
    private Double[] latitud;
    
    private Double[] longitud;
    
    private int tiempoEstimado;
    
    private int tiempoReal;

    private UsuarioSerializable usuario;
    
    private MovibusSerializable movibus;

    private ConductorSerializable conductor;

    private Double direccionDestinoLO;
    
    private Double direccionDestinoLA;

	public PedidoMovibusSerializable(Long id ,Date fechaPed, Date fechEj, Double[] lat, Double[] lon, int tempE, int temR, UsuarioSerializable usu, MovibusSerializable mov, ConductorSerializable cond, Double destLo, Double destLa)
    {
		this.id=id;
		this.fechaEjecucion=fechEj;
       	this.fechaPedido=fechaPed;
       	this.latitud=lat;
       	this.longitud=lon;
       	this.tiempoEstimado=tempE;
       	this.tiempoReal=temR;
       	this.usuario=usu;
       	this.movibus=mov;
       	this.conductor=cond;
       	this.direccionDestinoLA=destLa;
       	this.direccionDestinoLO=destLo;
           	
    }

	public PedidoMovibusSerializable()
	{
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public Double[] getLatitud() {
		return latitud;
	}

	public void setLatitud(Double[] latitud) {
		this.latitud = latitud;
	}

	public Double[] getLongitud() {
		return longitud;
	}

	public void setLongitud(Double[] longitud) {
		this.longitud = longitud;
	}

	public int getTiempoEstimado() {
		return tiempoEstimado;
	}

	public void setTiempoEstimado(int tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}

	public int getTiempoReal() {
		return tiempoReal;
	}

	public void setTiempoReal(int tiempoReal) {
		this.tiempoReal = tiempoReal;
	}

	public UsuarioSerializable getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSerializable usuario) {
		this.usuario = usuario;
	}

	public MovibusSerializable getMovibus() {
		return movibus;
	}

	public void setMovibus(MovibusSerializable movibus) {
		this.movibus = movibus;
	}

	public ConductorSerializable getConductor() {
		return conductor;
	}

	public void setConductor(ConductorSerializable conductor) {
		this.conductor = conductor;
	}
	
	public Double getDireccionDestinoLO() {
		return direccionDestinoLO;
	}

	public void setDireccionDestinoLO(Double direccionDestinoLO) {
		this.direccionDestinoLO = direccionDestinoLO;
	}

	public Double getDireccionDestinoLA() {
		return direccionDestinoLA;
	}

	public void setDireccionDestinoLA(Double direccionDestinoLA) {
		this.direccionDestinoLA = direccionDestinoLA;
	}
    
}
