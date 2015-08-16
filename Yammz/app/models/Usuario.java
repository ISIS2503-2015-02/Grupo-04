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
@Entity
public class Usuario extends Model{

    //---------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------

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
    private int vCubsEnUso;

    /**
     * Numero de su tarjeta bancaria.
     */
    private long tarjetaBancaria;

    //---------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------

    public Usuario(){

    }

    public Usuario(String nombre,int cedula,int celular,String correo,long tarjetaBancaria) {
        this.nombre=nombre;
        this.cedula=cedula;
        this.celular=celular;
        this.correo=correo;
        this.tarjetaBancaria=tarjetaBancaria;
        this.vCubsEnUso=0;
    }

    //---------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------

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

    public int getvCubsWnUso() {
        return vCubsEnUso;
    }

    public void setvCubsEnUso(int vcubs) {
        this.vCubsEnUso=vcubs;
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
    public void agregarvCubEnUso() {
        this.vCubsEnUso++;
    }

    /**
     * Metodo que se encarga de reducir en valor de 1 el numero de vCubs en uso por el usuario
     */
    public void devolvervCubEnUso() {
        this.vCubsEnUso--;
    }

}
