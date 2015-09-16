/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
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

    private Double latitud;

    private Double longitud;

    public static Finder<Long, Movibus> find = new Model.Finder<>(Long.class, Movibus.class);

    /**
     * Estado del vehiculo
     */
    private int estado;

    /**
     * Kilometraje del vehiculo transcurrido desde revision
     */
    private int kilometraje;
    
    public Movibus(Double latitud, Double longitud) {
        this.latitud=latitud;
        this.longitud=longitud;
        estado=DISPONIBLE;
        kilometraje=0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud=latitud;
    }

    public Double getLongitud() { return longitud; }

    public void setLongitud(Double longitud) {
        this.longitud=longitud;
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
        Double latitud = j.findPath("latitud").asDouble();
        Double longitud = j.findPath("longitud").asDouble();
        return new Movibus(latitud, longitud);
    }

    public void update(Movibus movibus) {
        this.setLatitud(movibus.getLatitud());
        this.setLongitud(movibus.getLongitud());
        this.setEstado(movibus.getEstado());
        this.setKilometraje(movibus.getKilometraje());
    }
}
