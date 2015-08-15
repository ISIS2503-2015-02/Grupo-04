/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Clase que representa un movibus en el sistema
 * @author cf.agudelo12
 */
public class Movibus extends Vehiculo {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    private PedidoMovibus pedidoMovibus;
    
    public Movibus(Direccion posicion) {
        super(posicion);
        this.pedidoMovibus=null;
    }
    
    public PedidoMovibus getPedidoMovibus() {
        return pedidoMovibus;
    }
    
    public void liberarMovibus() {
        pedidoMovibus=null;
        this.setEstado(DISPONIBLE);
    }
    
    public void reservarMovibus(PedidoMovibus pedidoMovibus) {
        this.pedidoMovibus=pedidoMovibus;
        this.setEstado(OCUPADO);
    }
}
