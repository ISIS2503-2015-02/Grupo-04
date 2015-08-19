/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.LinkedList;

import com.avaje.ebean.Model;
import javax.persistence.*;
/**
 *
 * @author cfagu
 */
@Entity
public class EstacionVcub extends Model{
    //------------------------------------------------------
    // Atributos
    //------------------------------------------------------

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
     * Envio reporte.
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
     */
    public EstacionVcub(int capacidad,String nombre) {
        this.capacidad=capacidad;
        this.vcubs=capacidad;
        this.envioReporte=false;
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

    public boolean isEnvioReporte() {
        return envioReporte;
    }

    public void setEnvioReporte(boolean envioReporte) {
        this.envioReporte = envioReporte;
    }

    /**
     * Restituye una vcub de la estacion.
     */
    public void restituirVcub() {
        vcubs++;
    }

    /**
     * Llena hasta la capacidad maxima de Vcubs, la estacion.
     */
    public void llenarEstacion(){
        vcubs = capacidad;
        envioReporte = false;
    }
}
