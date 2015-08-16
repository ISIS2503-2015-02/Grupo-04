/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.LinkedList;

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
    
    public PedidoMovibus(Date fechaEjecucion,int tiempoEstimado,Usuario usuario,Movibus movibus,Conductor conductor) {
        fechaPedido=new Date();
        this.fechaEjecucion=fechaEjecucion;
        this.tiempoEstimado=tiempoEstimado;
        this.usuario=usuario;
        this.movibus=movibus;
        this.conductor=conductor;
        movibus.reservarMovibus(this);
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
}
