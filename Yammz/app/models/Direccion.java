/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
import javax.persistence.*;
/**
 * Clase que representa la información de una direccion en la ciudad
 * @author cf.agudelo12
 */
@Entity
public class Direccion extends Model {
    
    private int principal;
    
    private int numero;
    
    private String detalles;
    
    public Direccion(int principal, int numero, String detalles) {
        this.principal=principal;
        this.numero=numero;
        this.detalles=detalles;
    }
    
    public int getPrincipal() {
        return principal;
    }
    
    public void setPrincipal(int principal) {
        this.principal=principal;
    }

    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero=numero;
    }
    
    public String getDetalles() {
        return detalles;
    }
    
    public void setDetalles(String detalles) {
        this.detalles=detalles;
    }

    public boolean equals(Object direccion) {
        return principal==((Direccion)direccion).principal&&numero==((Direccion)direccion).numero;
    }
}
