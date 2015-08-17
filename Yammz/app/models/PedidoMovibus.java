/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.LinkedList;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;

/**
 * 
 * @author cfagu
 */
@Entity
public class PedidoMovibus extends Model{
    
    private final Date fechaPedido;
    
    private final Date fechaEjecucion;
    
    private LinkedList<Direccion> ruta;
    
    private final int tiempoEstimado;
    
    private int tiempoReal;
    
    public Usuario usuario; 
    
    public Movibus movibus;
    
    public Conductor conductor;
    
    public PedidoMovibus() {
        ruta=new LinkedList<>();
        fechaPedido=new Date();
        this.fechaEjecucion=null;
        this.tiempoEstimado=0;
        this.usuario=null;
        this.movibus=null;
        this.conductor=null;
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

    public static PedidoMovibus bind(JsonNode j) {
        int tiempoEstimado=j.findPath("tiempoEstimado").asInt();
        return new PedidoMovibus();
    }
}
