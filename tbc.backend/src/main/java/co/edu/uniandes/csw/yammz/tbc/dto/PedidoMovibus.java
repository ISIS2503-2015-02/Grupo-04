/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.dto;

import java.util.Date;
import java.util.LinkedList;

/**
 * 
 * @author cfagu
 */
public class PedidoMovibus {
    
    private int estado;
    
    private Date fechaPedido;
    
    private Date fechaEjecucion;
    
    private LinkedList<Direccion> ruta;
}
