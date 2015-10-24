package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;


@Entity
public class PedidoMovibusPendiente extends Model {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    /**
     * Fecha en la que se genero el pedido
     */
    private Date fechaPedido;

    /**
     * Fecha en la que se quiere que se recoja al usuario del pedido
     */
    private Date fechaEjecucion;

    /**
     * Latitud en la que se quiere recoger al usuario
     */
    private Double latitudUsuario;

    /**
     * Longitud en la que se quiere recoger al usuario
     */
    private Double longitudUsuario;

    /**
     * Latitud a la que se quiere llevar al usuario
     */
    private Double latitudDestino;

    /**
     * Longitud a la que se quiere  llevar al usuario
     */
    private Double longitudDestino;

    /**
     * Tiempo estimado que va a tomar en realizarse el viaje del pedido
     */
    private int tiempoEstimado;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    public PedidoMovibusPendiente(Usuario usuario,Date fechaPedido,Date fechaEjecucion,Double latitudUsuario,Double longitudUsuario,Double latitudDestino,Double longitudDestino,int tiempoEstimado) {
        this.usuario=usuario;
        this.fechaPedido=fechaPedido;
        this.fechaEjecucion=fechaEjecucion;
        this.latitudUsuario=latitudUsuario;
        this.longitudUsuario=longitudUsuario;
        this.latitudDestino=latitudDestino;
        this.longitudDestino=longitudDestino;
        this.tiempoEstimado=tiempoEstimado;
    }

    //-----------------------------------------------------------
    // Metodos (todos son getters and setters)
    //-----------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario=usuario;
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

    public Double getLatitudUsuario() {
        return latitudUsuario;
    }

    public void setLatitudUsuario(Double latitudUsuario) {
        this.latitudUsuario=latitudUsuario;
    }

    public Double getLongitudUsuario() {
        return longitudUsuario;
    }

    public void setLongitudUsuario(Double longitudUsuario) {
        this.longitudUsuario=longitudUsuario;
    }

    public Double getLatitudDestino() {
        return latitudDestino;
    }

    public void setLatitudDestino(Double latitudDestino) {
        this.latitudDestino=latitudDestino;
    }

    public Double getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(Double longitudDestino) {
        this.longitudDestino=longitudDestino;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado=tiempoEstimado;
    }
}