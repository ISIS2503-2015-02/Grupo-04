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
    
    private Long id;
  
    //-----------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------
    
    public Vehiculo(Direccion posicion) {
        this.posicion=posicion;

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
       /**
     * Modifica el número de identificación delvehiculo
     * @param id Nuevo número de identificación
     */
    public void setIdentificacion(long id)
    {
        this.id = id;
    }
}
