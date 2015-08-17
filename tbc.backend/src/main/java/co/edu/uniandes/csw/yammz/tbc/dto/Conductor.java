/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.dto;

/**
 *
 * @author cfagu
 */
public class Conductor {
 
    private Long id;

    private final String nombre;
    
    private final int cedula;
    
    private int celular;
    
    private String correo;
    
    public Conductor(String nombre,int cedula,int celular,String correo) {
        this.nombre=nombre;
        this.cedula=cedula;
        this.celular=celular;
        this.correo=correo;
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
    
    public int getCedula() {
        return cedula;
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
}
