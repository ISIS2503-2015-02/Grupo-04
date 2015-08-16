/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
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

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    @Id
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

    private int id;
    //-----------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------
    
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
     * Linea asignada al tranvia
     */
    private int linea;
    
    public Tranvia(Direccion posicion,int linea) {
        this.linea=linea;
        this.posicion=posicion;
        estado=DISPONIBLE;
        kilometraje=0;
    }
    
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
    public Direccion getPosicion() {
        return posicion;
    }

    public int getId(){ return id;}
    public void setId(int id){this.id=id;}
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
        Direccion posicion = null;
        int estado = j.findPath("estado").asInt();
        int kilometraje = j.findPath("kilometraje").asInt();
        int linea = j.findPath("linea").asInt();
        Tranvia tranvia = new Tranvia(posicion, linea);
        return tranvia;
    }

    public void update(Tranvia nuevoTranvia) {
        this.setPosicion((nuevoTranvia.getPosicion()));
        this.setEstado(nuevoTranvia.getEstado());
        this.setKilometraje(nuevoTranvia.getKilometraje());
        this.setLinea(nuevoTranvia.getLinea());
    }

}
