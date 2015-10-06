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

@Entity
public class Conductor extends Model {

    public final static int DISPONIBLE=0;

    public final static int OCUPADO=1;

    public static Finder<Long, Conductor> find = new Model.Finder<>(Long.class, Conductor.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int cedula;

    private int celular;

    private String correo;

    private double desempenio;

    private int viajesTotales;

    private int estado;

    public Conductor(String nombre, int cedula, int celular, String correo) {
        this.nombre=nombre;
        this.cedula=cedula;
        this.celular=celular;
        this.correo=correo;
        this.viajesTotales=0;
        this.estado=DISPONIBLE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre=nombre;
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

    public double getDesempenio(){
        return desempenio;
    }

    public void setDesempenio(double desempenio){
        viajesTotales++;
        this.desempenio += desempenio/viajesTotales;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public static Conductor bind(JsonNode j) {
        String nombre=j.findPath("nombre").asText();
        int cedula=j.findPath("cedula").asInt();
        int celular=j.findPath("celular").asInt();
        String correo=j.findPath("correo").asText();
        return new Conductor(nombre,cedula,celular,correo);
    }

    public void update(Conductor conductor) {
        this.setNombre(conductor.getNombre());
        this.setCedula(conductor.getCedula());
        this.setCelular(conductor.getCelular());
        this.setCorreo(conductor.getCorreo());
    }
}