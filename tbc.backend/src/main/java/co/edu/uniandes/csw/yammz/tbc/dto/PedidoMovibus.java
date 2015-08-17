/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.dto;

import java.util.Date;
import java.util.LinkedList;

/**
 * 
 * @author cfagu
 */
public class PedidoMovibus {

    private long id;

    private final Date fechaPedido;
    
    private final Date fechaEjecucion;
    
    private LinkedList<Direccion> ruta;
    
    private int tiempoEstimado;
    
    private int tiempoReal;

    private Usuario usuario;

    private Movibus movibus;

    private Conductor conductor;

    private Direccion direccionUsuario;

    private Direccion direccionDestino;

    public PedidoMovibus() {
        ruta=new LinkedList<>();
        fechaPedido=new Date();
        fechaEjecucion=null;
        tiempoEstimado=0;
        usuario=null;
        movibus=null;
        conductor=null;
        direccionUsuario=null;
        direccionDestino=null;
    }
    
    public PedidoMovibus(Date fechaEjecucion, Direccion direccionUsuario, Direccion direccionDestino) {
        ruta=new LinkedList<>();
        fechaPedido=new Date();
        this.fechaEjecucion=fechaEjecucion;
        tiempoEstimado=0;
        usuario=null;
        movibus=null;
        conductor=null;
        this.direccionUsuario=direccionUsuario;
        this.direccionDestino=direccionDestino;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id=id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }
    
    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }
    
    public LinkedList<Direccion> getRuta() {
        return ruta;
    }
    
    public int getTiempoEstimado() {
        return tiempoEstimado;
    }
    
    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado=tiempoEstimado;
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

    public Direccion getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(Direccion direccionUsuario) {
        this.direccionUsuario=direccionUsuario;
    }

    public Direccion getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(Direccion direccionDestino) {
        this.direccionDestino=direccionDestino;
    }
}
