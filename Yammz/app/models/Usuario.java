/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author cf.agudelo12
 */
public class Usuario {

    //---------------------------------------------------------------------
    //Atributos
    //---------------------------------------------------------------------
    
    private final String nombre;
    
    private final String cedula;
    
    private String celular;
    
    private String correo;

    private int vCubsEnUso;
    
    private long tarjetaBancaria;

    //---------------------------------------------------------------------
    //Constructor
    //---------------------------------------------------------------------
    
    public Usuario(String nombre,String cedula,String celular,String correo,long tarjetaBancaria) {
        this.nombre=nombre;
        this.cedula=cedula;
        this.celular=celular;
        this.correo=correo;
        this.tarjetaBancaria=tarjetaBancaria;
        this.vCubsEnUso=0;
    }

    //---------------------------------------------------------------------
    //Metodos
    //---------------------------------------------------------------------

    public String getNombre() {
        return nombre;
    }
    
    public String getCedula() {
        return cedula;
    }

    public String getCelular() {
        return celular;
    }
    
    public void setCelular(String celular) {
        this.celular=celular;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo=correo;
    }

    public int getvCubsWnUso() {
        return vCubsEnUso;
    }

    public void setvCubsEnUso(int vcubs) {
        this.vCubsEnUso=vcubs;
    }

    /**
     * Metodo que se encarga de aumentar en valor de 1 el numero de vCubs en uso por el usuario
     */
    public void agregarvCubEnUso() {
        this.vCubsEnUso++;
    }

    /**
     * Metodo que se encarga de reducir en valor de 1 el numero de vCubs en uso por el usuario
     */
    public void devolvervCubEnUso() {
        this.vCubsEnUso--;
    }
    
    public long getTarjetaBancaria() {
        return tarjetaBancaria;
    }
    
    public void setTarjetaBancaria(long tarjetaBancaria) {
        this.tarjetaBancaria=tarjetaBancaria;
    }
}
