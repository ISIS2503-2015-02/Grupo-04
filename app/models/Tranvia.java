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
 * Clase que representa un tranvia electrico en el sistema
 * @author cf.agudelo12
 */
@Entity
public class Tranvia extends Model {

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

    /**
     * id universal del tranvia
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Latitud en la que se encuentra el tranvia
     */
    private Double latitud;

    /**
     * Longitud en la que se encuentra el tranvia
     */
    private Double longitud;

    /**
     * Estado del vehiculo
     */
    private int estado;

    /**
     * Kilometraje del vehiculo transcurrido desde revision
     */
    private int kilometraje;

    /**
     * Linea asignada al tranvia
     */
    private int linea;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    public Tranvia(Double latitud, Double longitud, int linea) {
        this.linea=linea;
        this.latitud=latitud;
        this.longitud=longitud;
        estado=DISPONIBLE;
        kilometraje=0;
    }

    //-----------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------

    /**
     * Metodo encargado de obtener el id del vehiculo
     * @return id
     */
    public Long getId(){ return id;}

    /**
     * Metodo encargado de cambiar el id del tranvia
     * @param id
     */
    public void setId(Long id){this.id=id;}

    /**
     * Metodo encargado de obtener el identificador de la linea del tranvia
     * @return linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * Metodo encargado de cambiar el identificador de linea del tranvia
     * @param linea
     */
    public void setLinea(int linea) {
        this.linea=linea;
    }

    /**
     * Metodo encargado de obtener la latitud actual del tranvia
     * @return latitud
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * Metodo encargado de cambiar la latitud del tranvia
     * @param latitud
     */
    public void setLatitud(Double latitud) {
        this.latitud=latitud;
    }

    /**
     * Metodo encargado de obtener la longitud actual del tranvia
     * @return longitud
     */
    public Double getLongitud() { return longitud; }

    /**
     * Metodo encargado de cambiar el longitud del tranvia
     * @param longitud
     */
    public void setLongitud(Double longitud) {
        this.longitud=longitud;
    }

    /**
     * Metodo encargado de obtener el estado del tranvia
     * @return estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Metodo encargado de cambiar el estado del tranvia
     * @param estado
     */
    public void setEstado(int estado) {
        this.estado=estado;
    }

    /**
     * Metodo encargado de cambiar el kilometraje del tranvia
     * @param kilometraje
     */
    public void setKilometraje(int kilometraje) {
        this.kilometraje=kilometraje;
    }

    /**
     * Metodo encargado de obtener el kilometraje del tranvia
     * @return kilometraje
     */
    public int getKilometraje() {
        return kilometraje;
    }

    /**
     * Metodo encargado de registrar la revision de un tranvia
     */
    public void revisarVehiculo() {
        kilometraje=0;
    }

    /**
     * Crea un objeto Tranvia apartir de un nodo Json
     * @param j Nodo Json con atributos y valores de un objeto Tranvia
     */
    public static Tranvia bind(JsonNode j) {
        Double latitud = j.findPath("latitud").asDouble();
        Double longitud = j.findPath("longitud").asDouble();
        int linea = j.findPath("linea").asInt();
        Tranvia tranvia = new Tranvia(latitud, longitud, linea);
        return tranvia;
    }

    public void update(Tranvia tranvia) {
        this.setLatitud(tranvia.getLatitud());
        this.setLongitud(tranvia.getLongitud());
        this.setEstado(tranvia.getEstado());
        this.setKilometraje(tranvia.getKilometraje());
        this.setLinea(tranvia.getLinea());
    }
}
