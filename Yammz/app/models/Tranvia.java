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

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

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

    /**
     * Linea asignada al tranvia
     */
    private int linea;

    
    public Tranvia(String posicion,int linea) {
        this.linea=linea;
        this.posicion=posicion;
        estado=DISPONIBLE;
        kilometraje=0;
    }

    public Long getId(){ return id;}

    public void setId(Long id){this.id=id;}

    public int getLinea() {
        return linea;
    }
    
    public void setLinea(int linea) {
        this.linea=linea;
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
     * Metodo encargado de cambiar el kilometraje del vehiculo
     * @param kilometraje
     */
    public void setKilometraje(int kilometraje) {
        this.kilometraje=kilometraje;
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
     * Crea un objeto Tranvia apartir de un nodo Json
     * @param j Nodo Json con atributos y valores de un objeto Tranvia
     */
    public static Tranvia bind(JsonNode j) {
        String posicion = j.findPath("posicion").asText();
        int estado = j.findPath("estado").asInt();
        int kilometraje = j.findPath("kilometraje").asInt();
        int linea = j.findPath("linea").asInt();
        Tranvia tranvia = new Tranvia(posicion, linea);
        return tranvia;
    }

    public void update(Tranvia tranvia) {
        this.setPosicion((tranvia.getPosicion()));
        this.setEstado(tranvia.getEstado());
        this.setKilometraje(tranvia.getKilometraje());
        this.setLinea(tranvia.getLinea());
    }

}
