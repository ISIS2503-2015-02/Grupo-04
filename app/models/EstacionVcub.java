/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EstacionVcub extends Model{

    //------------------------------------------------------
    // Atributos
    //------------------------------------------------------

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Capacidad de vcubs.
     */
    private int capacidad;

    /**
     * Cantidad actual de vcubs en la estacion.
     */
    private int vcubs;

    /**
     * Nombre estacion.
     */
    private String nombre;

    /**
     * Variable para saber si ya ha enviado un reporte solicitando restitucion de vcubs
     */
    private boolean reporte;

    //------------------------------------------------------
    // Constructores
    //------------------------------------------------------

    public EstacionVcub() {}

    public EstacionVcub(int capacidad, String nombre) {
        this.capacidad=capacidad;
        this.vcubs=capacidad;
        this.nombre=nombre;
        this.reporte=false;
    }

    //------------------------------------------------------
    // Metodos
    //------------------------------------------------------

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad=capacidad;
    }

    public int getVcubs() {
        return vcubs;
    }

    public void setVcubs(int vcubs) {
        this.vcubs=vcubs;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo que nos dice si es el numero de vcubs en la estacion esta por debajo del 10%
     * @return true en caso de ser menor de 10% y false de lo contrario
     */
    public boolean solicitarVcbus() {
        return vcubs/capacidad<=0.1;
    }

    /**
     * Metodo que reduce en 1 el numero de vcubs en la estacion
     */
    public void prestarVcub() {
        vcubs--;
    }

    /**
     * Metodo que aumenta en 1 el numero de vcubs en la estacion
     */
    public void restituirVcub() {
        vcubs++;
    }

    /**
     * Metodo que aumenta el numero de vcubs de la estacion al maximo de su capacidad
     */
    public void llenarEstacion(){
        vcubs = capacidad;
    }

    /**
     * Metodo que nos dice si ha reportado su falta de vcubs
     * @return false en caso de no haberlo hecho y true en caso de que si
     */
    public boolean haReportado() {
        return reporte;
    }

    /**
     * Metodo que cambia el valor boolean de reporte que representa si ya se realizo antes un reporte
     * @param nReporte
     */
    public void cambioReporte(boolean nReporte) {
        this.reporte = nReporte;
    }

}
