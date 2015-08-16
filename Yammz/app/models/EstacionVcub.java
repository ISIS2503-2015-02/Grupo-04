/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.LinkedList;

/**
 *
 * @author cfagu
 */
@Entity
public class EstacionVcub extends Model{
    //------------------------------------------------------
    // Atributos
    //------------------------------------------------------
    /**
     * Capacidad de vcubs.
     */
    private int capacidad;

    /**
     * Cantidad actual de vcbus en la estacion.
     */
    private int vcubs;
    /**
     * Nombre estacion.
     */
    private String nombre;
    /**
     * Identificador de la esta estacion.
     */
    private int id;

    //------------------------------------------------------
    // Constructores
    //------------------------------------------------------
    /**
     * Constructor sin parametros.
     */
    public EstacionVcub() {
    }

    /**
     * Constructor con parametros.
     * @param capacidad Capacidad de la estacion.
     */
    public EstacionVcub(int capacidad,String nombre,int id) {
        this.capacidad=capacidad;
        this.vcubs=capacidad;
    }


    //------------------------------------------------------
    // Metodos
    //------------------------------------------------------
    /**
     * Devuelve la capacidad de la estacion.
     * @return int capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Devuelve la cantidad de vcubs en la estacion.
     * @return int cantidadVcubs
     */
    public int getVcubs() {
        return vcubs;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Verifica si la cantidad de vcubs baja del 10% de la capacidad.
     * @return true si necesita solicitar vcubs, false de lo contrario.
     */
    public boolean solicitarVcbus() {
        return vcubs/capacidad<=0.1;
    }

    /**
     * Agrega la cantidad de vcubs a la estacion.
     * @param vcub Capacidad de vcubs que llegan a la estacion.
     */
    public void agregarVcub(int vcub) {
        vcubs+=vcub;
    }

    /**
     * Presta una vcub de la estacion.
     */
    public void prestarVcub() {
        vcubs--;
    }

    /**
     * Restituye una vcub de la estacion.
     */
    public void restituirVcub() {
        vcubs++;
    }
}
