/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.LinkedList;

/**
 * 
 * @author cfagu
 */
public class EstacionVcub {
    
    private final int capacidad;
        
    private final LinkedList<Vcub> vcubs;
    
    public EstacionVcub(int capacidad) {
        this.capacidad=capacidad;
        vcubs=new LinkedList<>();
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    
    public LinkedList<Vcub> getVcubs() {
        return vcubs;
    }
    
    public boolean solicitarVcbus() {
        return vcubs.size()/capacidad<=0.1;
    }
    
    public void agregarVcub(Vcub vcub) {
        vcubs.add(vcub);
    }
    
    public Vcub prestarVcub(Usuario usuario) {
        Vcub vcub = vcubs.remove();
        return vcub;
    }
}
