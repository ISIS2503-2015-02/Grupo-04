/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.logica.dto;

/**
 * 
 * @author cfagu
 */
public class Vehiculo {
    
    //-----------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------
    
    /**
     * Constante que representa el estado ocupado
     */
    public final static int OCUPADO=0;
    
    /**
     * Constante que representa el estado disponible
     */
    public final static int DISPONIBLE=1;
    
    /**
     * Constante que representa el estado problema
     */
    public final static int PROBLEMA=2;
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Posicion del vehiculo
     */
    private Direccion posicion;
    
    /**
     * Estado del vehiculo
     */
    private int estado;
    
    /**
     * Kilometraje del vehiculo transcurrido desde revision
     */
    private int kilometraje;
  
    //-----------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------
    
    /**
     * Metodo encargado de obtener la posicion del vehiculo
     * @return posicion
     */
    public Direccion getPosicion() {
        return posicion;
    }
    
    /**
     * Metodo encargado de cambiar la posicion del vehiculo
     */
    public void setPosicion(Direccion posicion) {
        this.posicion=posicion;
    }
    
    /**
     * Metodo encargado de obtener el estado del vehiculo 
     * @return estado
     */
    public int getEstado() {
        return estado;
    }
    
    /**
     * Metodo encargado de cambiar el estado del vehiculo 
     */
    public void setEstado(int estado) {
        this.estado=estado;
    }
    
    public int getKilometraje() {
        return kilometraje;
    }
    
    public void revisarVehiculo() {
        kilometraje=0;
    }
}
