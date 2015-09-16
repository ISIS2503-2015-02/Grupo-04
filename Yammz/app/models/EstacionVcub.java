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

    public boolean solicitarVcbus() {
        return vcubs/capacidad<=0.1;
    }

    public void prestarVcub() {
        vcubs--;
    }

    public void restituirVcub() {
        vcubs++;
    }

    public void llenarEstacion(){
        vcubs = capacidad;
    }

    public boolean haReportado() {
        return reporte;
    }

    public void cambioReporte(boolean nReporte) {
        this.reporte = nReporte;
    }

}
