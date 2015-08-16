/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import com.avaje.ebean.Model;
/**
 * Clase que representa un tranvia electrico en el sistema
 * @author cf.agudelo12
 */
@Entity
public class Tranvia extends Vehiculo extends Model{
    
    //-----------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------
    
    /**
     * Constante que representa la linea 
     */
    public final static int LINEA_A=0;
    
    /**
     * Constante que representa la linea 
     */
    public final static int LINEA_B=1;
    
    /**
     * Constante que representa la linea 
     */
    public final static int LINEA_C=2;
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Linea asignada al tranvia
     */
    private int linea;
    
    public Tranvia(Direccion posicion,int linea) {
        super(posicion);
        this.linea=linea;
    }
    
    public int getLinea() {
        return linea;
    }
    
    public void setLinea(int linea) {
        this.linea=linea;
    }
}
