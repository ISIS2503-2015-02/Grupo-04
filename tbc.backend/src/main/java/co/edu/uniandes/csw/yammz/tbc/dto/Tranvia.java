/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.dto;

/**
 * Clase que representa un tranvia electrico en el sistema
 * @author cf.agudelo12
 */
public class Tranvia {
    
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
    
    /**
     * Constante que representa la linea 
     */
    public final static int LINEA_A=0;
    
    /**
     * Constante que representa la linea 
     */
    public final static int LINEA_B=1;
    
    /**
     * Constante que representa la linea 
     */
    public final static int LINEA_C=2;
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    private long id;
    
    /**
     * Linea asignada al tranvia
     */
    private int linea;
    
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
    
    public Tranvia() {
        
    }
    
    public Tranvia(long id,Direccion posicion, int linea) {
        this.id=id;
        this.posicion=posicion;
        estado=DISPONIBLE;
        kilometraje=0;
        this.linea=linea;
    }
        
    public long getId() {
        return id;
    }
    
    public void setId(long id)
    {
        this.id = id;
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
    public void setKilometraje(int kilometraje) {
        this.kilometraje=kilometraje;
    }
    
    public int getLinea() {
        return linea;
    }
    
    public void setLinea(int linea) {
        this.linea=linea;
    }
}
