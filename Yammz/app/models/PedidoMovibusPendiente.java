package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;


@Entity
public class PedidoMovibusPendiente extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    private Date fechaPedido;

    private Date fechaEjecucion;

    private Long latitudUsuario;

    private Long longitudUsuario;

    private Long latitudDestino;

    private Long longitudDestino;

    private int tiempoEstimado;

    public PedidoMovibusPendiente(Usuario usuario,Date fechaPedido,Date fechaEjecucion,Long latitudUsuario,Long longitudUsuario,Long latitudDestino,Long longitudDestino,int tiempoEstimado) {
        this.usuario=usuario;
        this.fechaPedido=fechaPedido;
        this.fechaEjecucion=fechaEjecucion;
        this.latitudUsuario=latitudUsuario;
        this.longitudUsuario=longitudUsuario;
        this.latitudDestino=latitudDestino;
        this.longitudDestino=longitudDestino;
        this.tiempoEstimado=tiempoEstimado;
    }

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

    public Long getLatitudUsuario() {
        return latitudUsuario;
    }

    public void setLatitudUsuario(Long latitudUsuario) {
        this.latitudUsuario=latitudUsuario;
    }

    public Long getLongitudUsuario() {
        return longitudUsuario;
    }

    public void setLongitudUsuario(Long longitudUsuario) {
        this.longitudUsuario=longitudUsuario;
    }

    public Long getLatitudDestino() {
        return latitudDestino;
    }

    public void setLatitudDestino(Long latitudDestino) {
        this.latitudDestino=latitudDestino;
    }

    public Long getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(Long longitudDestino) {
        this.longitudDestino=longitudDestino;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado=tiempoEstimado;
    }
}