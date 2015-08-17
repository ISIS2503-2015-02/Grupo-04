/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.dto;

/**
 * Clase que representa un movibus en el sistema
 * @author cf.agudelo12
 */
public class Movibus {

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

    private long id;

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
    
    public Movibus() {
        
    }
    
    public Movibus(long id, Direccion posicion) {
        this.id=id;
        this.posicion=posicion;
        estado=DISPONIBLE;
        kilometraje=0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id=id;
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

    public void setKilometraje(int kilometraje) {
        this.kilometraje=kilometraje;
    }

    /**
     * Metodo encargado de registrar la revision de un vehiculo
     */
    public void revisarVehiculo() {
        kilometraje=0;
    }

    public void reservarMovibus(PedidoMovibus pedidoMovibus) {
        this.estado=OCUPADO;
    }
}
