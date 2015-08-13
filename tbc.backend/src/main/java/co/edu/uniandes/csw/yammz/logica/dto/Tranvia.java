/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.logica.dto;

/**
 * Clase que representa un tranvia electrico en el sistema
 * @author cf.agudelo12
 */
public class Tranvia {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Posicion del tranvia
     */
    private String posicion;
    
    /**
     * Estado del tranvia
     */
    private int estado;
    
    /**
     * Linea asignada al tranvia
     */
    private int linea;
    
    public Tranvia() {
 
    }
        
    public String getPosicion() {
        return posicion;
    }
    
    public void setPosicion(String posicion) {
        this.posicion=posicion;
    }
    
    public int getEstado() {
        return estado;
    }
    
    public void setEstado(int estado) {
        this.estado=estado;
    }
}
