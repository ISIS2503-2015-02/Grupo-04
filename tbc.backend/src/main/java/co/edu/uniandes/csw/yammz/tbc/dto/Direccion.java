/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.dto;

/**
 * Clase que representa la información de una direccion en la ciudad
 * @author cf.agudelo12
 */
public class Direccion {
    
    private int calle;
    
    private int carrera;
    
    private int numero;
    
    public Direccion(int calle, int carrera, int numero) {
        this.calle=calle;
        this.carrera=carrera;
        this.numero=numero;
    }
    
    public int getCalle() {
        return calle;
    }
    
    public int getCarrera() {
        return carrera;
    }
    
    public int getNumero() {
        return numero;
    }
}
