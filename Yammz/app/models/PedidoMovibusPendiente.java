package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by cfagu on 19-Aug-15.
 */
@Entity
public class PedidoMovibusPendiente extends Model {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Usuario usuario;

    private String direccionUsuario;

    private String direccionDestino;

    public PedidoMovibusPendiente(Usuario usuario,String direccionUsuario,String direccionDestino) {
        this.usuario=usuario;
        this.direccionDestino=direccionDestino;
        this.direccionUsuario=direccionUsuario;
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

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario)        {
        this.direccionUsuario=direccionUsuario;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino=direccionDestino;
    }
}

