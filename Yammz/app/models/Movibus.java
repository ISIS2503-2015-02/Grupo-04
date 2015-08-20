/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import com.avaje.ebean.Model;
import com.avaje.ebeaninternal.server.lib.util.Str;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Clase que representa un movibus en el sistema
 * @author cf.agudelo12
 */
@Entity
public class Movibus extends Model {

    //-----------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------

    /**
     * Constante que representa el estado disponible
     */
    public final static int DISPONIBLE=0;

    /**
     * Constante que representa el estado ocupado
     */
    public final static int OCUPADO=1;

    /**
     * Constante que representa el estado problema
     */
    public final static int PROBLEMA=2;

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public static Finder<Integer, Movibus> find
            = new Model.Finder<>(Integer.class, Movibus.class);

    /**
     * Posicion del vehiculo
     */
    private String posicion;

    /**
     * Estado del vehiculo
     */
    private int estado;

    /**
     * Kilometraje del vehiculo transcurrido desde revision
     */
    private int kilometraje;
    
    public Movibus(String posicion) {
        this.posicion=posicion;
        estado=DISPONIBLE;
        kilometraje=0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    /**
     * Metodo encargado de obtener la posicion del vehiculo
     * @return posicion
     */
    public String getPosicion() {
        return posicion;
    }

    /**
     * Metodo encargado de cambiar la posicion del vehiculo
     * @param posicion
     */
    public void setPosicion(String posicion) {
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

    public void setKilometraje(int kilometraje) {this.kilometraje=kilometraje;}

    /**
     * Metodo encargado de registrar la revision de un vehiculo
     */
    public void revisarVehiculo() {
        kilometraje=0;
    }

    public void reservarMovibus(PedidoMovibus pedidoMovibus) {
        this.estado=OCUPADO;
    }

    public static Movibus bind(JsonNode j) {
        String posicion = j.findPath("posicion").asText();
        return new Movibus(posicion);
    }

    public void update(Movibus movibus) {
        this.setEstado(movibus.getEstado());
        this.setPosicion(movibus.getPosicion());
        this.setKilometraje(movibus.getKilometraje());
    }
}
