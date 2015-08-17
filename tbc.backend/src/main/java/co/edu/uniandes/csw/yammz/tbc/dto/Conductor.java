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
    
    private final String nombre;
    
    private final String cedula;
    
    private String celular;
    
    private String correo;
    
    public Conductor(String nombre,String cedula,String celular,String correo,int tipo) {
        this.nombre=nombre;
        this.cedula=cedula;
        this.celular=celular;
        this.correo=correo;
    }
    
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
}