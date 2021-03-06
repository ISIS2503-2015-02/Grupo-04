/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
import java.util.Date;


@Entity
public class PedidoMovibus extends Model{

    //-----------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------
    public static final int DISTANCIA_MAXIMA=10000;

    //-----------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Date fechaPedido;
    
    private Date fechaEjecucion;
    
    private int tiempoEstimado;
    
    private int tiempoReal;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Movibus movibus;

    @ManyToOne
    private Conductor conductor;

    private Double latitudUsuario;

    private Double longitudUsuario;

    private Double latitudDestino;

    private Double longitudDestino;

    //-----------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------

    public PedidoMovibus() {
        fechaPedido=new Date();
        fechaEjecucion=null;
        tiempoEstimado=0;
        usuario=null;
        movibus=null;
        conductor=null;
        latitudUsuario=null;
        longitudUsuario=null;
        latitudDestino=null;
        longitudDestino=null;
    }

    //-----------------------------------------------------------
    // Metodos (todos son getters and setters excepto el ultimo que es una especie de constructor de nuevos pedidos)
    //-----------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
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
        this.tiempoReal=tiempoReal;
    }
    
    public int getDiferenciaTiempos() {
        return tiempoEstimado-tiempoReal;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario=usuario;
    }

    public Movibus getMovibus() {
        return movibus;
    }
    
    public  void setMovibus(Movibus movibus) {
        this.movibus=movibus;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor=conductor;
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

    public static PedidoMovibus bind() {
        return new PedidoMovibus();
    }
}
