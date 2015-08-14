/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.dto;

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
    
    public Vehiculo(Direccion posicion) {
        this.posicion=posicion;
        estado=DISPONIBLE;
        kilometraje=0;
    }
    
    /**
     * Metodo encargado de obtener la posicion del vehiculo
     * @return posicion
     */
    public Direccion getPosicion() {
        return posicion;
    }
    
    /**
     * Metodo encargado de cambiar la posicion del vehiculo
     * @param posicion
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
     * @param estado
     */
    public void setEstado(int estado) {
        this.estado=estado;
    }
    
    /**
     * Metodo encargado de obtener el kilometraje del vehiculo
     * @return kilometraje
     */
    public int getKilometraje() {
        return kilometraje;
    }
    
    /**
     * Metodo encargado de registrar la revision de un vehiculo
     */
    public void revisarVehiculo() {
        kilometraje=0;
    }
}
