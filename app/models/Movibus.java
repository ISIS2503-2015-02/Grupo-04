/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import org.owasp.StringEnvelope;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Clase que representa un movibus en el sistema
 * @author cf.agudelo12
 */
@Entity
public class Movibus extends Model {

    //-----------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------

    /**
     * Constante que representa el estado disponible
     */
    public final static int DISPONIBLE=0;

    /**
     * Constante que representa el estado ocupado
     */
    public final static int OCUPADO=1;

    /**
     * Constante que representa el estado problema
     */
    public final static int PROBLEMA=2;

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Id unico del movibus
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Latitud en la que se encuentra el movibus
     */
    private Double latitud;

    /**
     * Longitud en la que se encuentra el movibus
     */
    private Double longitud;

    /**
     * Estado del vehiculo
     */
    private int estado;

    /**
     * Kilometraje del vehiculo transcurrido desde revision
     */
    private int kilometraje;

    //-------------------------------------------------------
    // WTF
    //-------------------------------------------------------

    public static Finder<Long, Movibus> find = new Model.Finder<>(Long.class, Movibus.class);

    //-------------------------------------------------------
    // Constructor
    //-------------------------------------------------------

    public Movibus(Double latitud, Double longitud) {
        this.latitud=latitud;
        this.longitud=longitud;
        estado=DISPONIBLE;
        kilometraje=0;
    }

    //-------------------------------------------------------
    // Metodos
    //-------------------------------------------------------

    /**
     * Metodo encargado de obtener el id unico del vehiculo
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo encargado de cambiar el id del vehiculo
     * @param id
     */
    public void setId(Long id) {
        this.id=id;
    }

    /**
     * Metodo encargado de obtener la latitud del vehiculo
     * @return latitud
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * Metodo encargado de cambiar la latitud del vehiculo
     * @param latitud
     */
    public void setLatitud(Double latitud) {
        this.latitud=latitud;
    }

    /**
     * Metodo encargado de obtener la longitud del vehiculo
     * @return longitud
     */
    public Double getLongitud() { return longitud; }

    /**
     * Metodo encargado de cambiar la longitud del vehiculo
     * @param longitud
     */
    public void setLongitud(Double longitud) {
        this.longitud=longitud;
    }

    /**
     * Metodo encargado de obtener el estado del vehiculo
     * @return estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Metodo encargado de cambiar el estado del vehiculo
     * @param estado
     */
    public void setEstado(int estado) {
        this.estado=estado;
    }

    /**
     * Metodo encargado de obtener el kilometraje del vehiculo
     * @return kilometraje
     */
    public int getKilometraje() {
        return kilometraje;
    }

    /**
     * Metodo encargado de cambiar el valor de kilometraje del vehiculo
     * @param kilometraje
     */
    public void setKilometraje(int kilometraje) {this.kilometraje=kilometraje;}

    /**
     * Metodo encargado de registrar la revision de un vehiculo
     */
    public void revisarVehiculo() {
        kilometraje=0;
    }

    public String deseEnvolver(String crypted)
    {
        String plaintext="";
        StringEnvelope env = new StringEnvelope();
        try {
            plaintext = env.unwrap(crypted, "aa09cee77e1d606d5ab06500ac95729c");
        }
        catch(IllegalArgumentException e){
            System.out.println("Decryption failed: " + e);} catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return plaintext;
    }

    /**
     * Metodo encargado de cambiar el estado del movibus despues de crearse una reserva del vehiculo
     */
    public void reservarMovibus(PedidoMovibus pedidoMovibus) {
        this.estado=OCUPADO;
    }

    public static Movibus bind(JsonNode j) {
        Double latitud = j.findPath("latitud").asDouble();
        Double longitud = j.findPath("longitud").asDouble();
        return new Movibus(latitud, longitud);
    }

    public void update(Movibus movibus) {
        this.setLatitud(movibus.getLatitud());
        this.setLongitud(movibus.getLongitud());
        this.setEstado(movibus.getEstado());
        this.setKilometraje(movibus.getKilometraje());
    }
}
