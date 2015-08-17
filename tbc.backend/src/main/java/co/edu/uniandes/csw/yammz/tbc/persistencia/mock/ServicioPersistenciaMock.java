/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.persistencia.mock;

import co.edu.uniandes.csw.yammz.tbc.dto.Tranvia;
import co.edu.uniandes.csw.yammz.tbc.interfaces.IServicioPersistenciaMockLocal;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cfagu
 */
public class ServicioPersistenciaMock implements IServicioPersistenciaMockLocal {
    
    private static LinkedList estacionesVcub;
    
    private static LinkedList movibuses;
    
    private static LinkedList pedidosMovibus;
    
    private static LinkedList reportes;
    
    private static LinkedList tranvias;
    
    /**
     * Constructor de la clase. Inicializa los atributos.
     */
    public ServicioPersistenciaMock()
    {
        
    }

  
    
     //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    
    /**
     * Permite crear un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere crear.
     */
    @Override
    public void create(Object obj)
    {
        if (obj instanceof Tranvia)
        {
            Tranvia t = (Tranvia) obj;
            t.setIdentificacion(tranvias.size() + 1);
            tranvias.add(t);
        }
    }

     /** Retorna la lista de todos los elementos de una clase dada que se encuentran en el sistema.
     * @param c Clase cuyos objetos quieren encontrarse en el sistema.
     * @return list Listado de todos los objetos de una clase dada que se encuentran en el sistema.
     */
    @Override
    public List findAll(Class c)
    {
        if (c.equals(Tranvia.class))
        {
            return tranvias;
        } 
        else
        {
            return null;
        }
    }
   
        

}
