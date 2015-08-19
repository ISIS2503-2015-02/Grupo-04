/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.dto;

import java.util.LinkedList;

/**
 *
 * @author cfagu
 */
public class EstacionVcub {
    //------------------------------------------------------
    // Atributos
    //------------------------------------------------------
    
    private long id;

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
     * Envio de reporte de falta de bicicletas.
     */
    private boolean envioReporte;
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
     * @param nombre Nombre de la estacion.
     * @param id Id autogenerado de la estacion.
     */
    public EstacionVcub(int capacidad,String nombre,long id) {
        this.capacidad=capacidad;
        this.vcubs=capacidad;
        this.nombre=nombre;
        this.envioReporte=false;
        this.id = id;
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

    public void setCapacidad(int capacidad){
        this.capacidad=capacidad;
    }
    /**
     * Devuelve la cantidad de vcubs en la estacion.
     * @return int cantidadVcubs
     */
    public int getVcubs() {
        return vcubs;
    }
    
    public void setVcubs(int vcubs){
        this.vcubs=vcubs;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public long getId(){
        return id;
    }
    
    public void setId(long id){
        this.id=id;
    }
}
