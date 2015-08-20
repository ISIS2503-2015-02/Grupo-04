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
 *
 * @author cf.agudelo12
 */
@Entity
public class Usuario extends Model{

    //---------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Numbero de la cedula.
     */
    private int cedula;

    /**
     * Numero del celular.
     */
    private int celular;

    /**
     * Correo del usuario.
     */
    private String correo;

    /**
     * Numero de biciletas vCubs que tiene prestadas a su nombre.
     */
    private int vcubsEnUso;

    /**
     * Numero de su tarjeta bancaria.
     */
    private long tarjetaBancaria;

    //---------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------

    public Usuario(String nombre,int cedula,int celular,String correo,long tarjetaBancaria) {
        this.nombre=nombre;
        this.cedula=cedula;
        this.celular=celular;
        this.correo=correo;
        this.tarjetaBancaria=tarjetaBancaria;
        this.vcubsEnUso=0;
    }

    //---------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
    this.cedula=cedula;
}

    public int getCelular() {
        return celular;
    }
    
    public void setCelular(int celular) {
        this.celular=celular;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo=correo;
    }

    public int getVcubsEnUso() {
        return vcubsEnUso;
    }

    public void setVcubsEnUso(int vcubs) {
        this.vcubsEnUso=vcubs;
    }

    public long getTarjetaBancaria() {
        return tarjetaBancaria;
    }

    public void setTarjetaBancaria(long tarjetaBancaria) {
        this.tarjetaBancaria=tarjetaBancaria;
    }
    /**
     * Metodo que se encarga de aumentar en valor de 1 el numero de vCubs en uso por el usuario
     */
    public void agregarVcubEnUso() {
        this.vcubsEnUso++;
    }

    /**
     * Metodo que se encarga de reducir en valor de 1 el numero de vCubs en uso por el usuario
     */
    public void devolverVcubEnUso() {
        this.vcubsEnUso--;
    }

    public static Usuario bind(JsonNode j) {
        String nombre=j.findPath("nombre").asText();
        int cedula=j.findPath("cedula").asInt();
        int celular=j.findPath("celular").asInt();
        String correo=j.findPath("correo").asText();
        long tarjetaBancaria=j.findPath("tarjetaBancaria").asLong();
        return new Usuario(nombre,cedula,celular,correo,tarjetaBancaria);
    }

    public void update(Usuario usuario) {
        this.setCedula(usuario.getCedula());
        this.setCelular(usuario.getCelular());
        this.setCorreo(usuario.getCorreo());
        this.setTarjetaBancaria(usuario.getTarjetaBancaria());
        this.setVcubsEnUso(usuario.getVcubsEnUso());
    }
}
