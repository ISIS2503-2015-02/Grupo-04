/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Clase que representa un vcub en el sistema
 * @author cf.agudelo12
 */
public class Vcub extends Vehiculo {
    
    private Usuario usuario;
    
    public Vcub(Direccion posicion) {
        super(posicion);
        usuario=null;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario=usuario;
    }
}
