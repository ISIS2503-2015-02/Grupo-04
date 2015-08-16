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
public class Direccion extends Model{
    
    private int calle;
    
    private int carrera;
    
    private int numero;
    
    private String detalles;
    
    public Direccion(int calle, int carrera, int numero, String detalles) {
        this.calle=calle;
        this.carrera=carrera;
        this.numero=numero;
        this.detalles=detalles;
    }
    
    public int getCalle() {
        return calle;
    }
    
    public void setCalle(int calle) {
        this.calle=calle;
    }
    
    public int getCarrera() {
        return carrera;
    }
    
    public void setCarrera(int carrera) {
        this.carrera=carrera;
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
}
