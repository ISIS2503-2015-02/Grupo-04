/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.interfaces;

/**
 *
 * @author sy.velasquez10
 */
public interface IServicioPersistenciaMockLocal {
    /**
     * Crea un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere crear.
     */
    public void create(java.lang.Object obj);

    /**
     * Devuelve una lista con de todos los elementos de una clase dada que se encuentran en el sistema.
     * @param c Clase cuyos objetos quieren encontrarse en el sistema.
     * @return list Listado de todos los objetos de una clase.
     */
    public java.util.List findAll(java.lang.Class c);
    
    public Object findById(Class c, Object id);
    
    public void update(java.lang.Object obj);
    
    public void delete(java.lang.Object obj);
}
