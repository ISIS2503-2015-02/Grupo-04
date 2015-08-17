/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.persistencia.mock;

import co.edu.uniandes.csw.yammz.tbc.dto.EstacionVcub;
import co.edu.uniandes.csw.yammz.tbc.dto.Movibus;
import co.edu.uniandes.csw.yammz.tbc.dto.PedidoMovibus;
import co.edu.uniandes.csw.yammz.tbc.dto.Reporte;
import co.edu.uniandes.csw.yammz.tbc.dto.Tranvia;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioPersistenciaMockLocal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cfagu
 */
public class ServicioPersistenciaMock implements IServicioPersistenciaMockLocal {
    
    private static ArrayList<EstacionVcub> estacionesVcub;
    
    private static ArrayList<Movibus> movibuses;
    
    private static ArrayList<PedidoMovibus> pedidosMovibus;
    
    private static ArrayList<Reporte> reportes;
    
    private static ArrayList<Tranvia> tranvias;
    
    /**
     * Constructor de la clase. Inicializa los atributos.
     */
    public ServicioPersistenciaMock() {
        estacionesVcub=new ArrayList();
        movibuses=new ArrayList();
        pedidosMovibus=new ArrayList();
        reportes=new ArrayList();    
        tranvias=new ArrayList();        
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
        if (obj instanceof Tranvia) {
            Tranvia t = (Tranvia) obj;
            t.setId(tranvias.size()+1);
            tranvias.add(t);
        }
        else if(obj instanceof Movibus) {
            Movibus movibus = (Movibus) obj;
            movibus.setId(movibuses.size());
            movibuses.add(movibus);
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
        else if(c.equals(Movibus.class)) {
            return movibuses;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Object findById(Class c, long id) {
        if (c.equals(Movibus.class)) {
            for (Object v : findAll(c)) {
                Movibus movibus = (Movibus) v;
                if (movibus.getId()==id) {
                    return movibus;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Object obj) {
        if (obj instanceof Movibus) {
            Movibus editar = (Movibus) obj;
            Movibus movibus;
            for (int i = 0; i < movibuses.size(); i++) {
                movibus = movibuses.get(i);
                if (movibus.getId()==editar.getId()) {
                    movibuses.set(i, editar);
                    break;
                }
            }
        }
    }

    @Override
    public void delete(Object obj) {
        if (obj instanceof Movibus) {
            Movibus movibusABorrar = (Movibus) obj;
            for (int e = 0; e < movibuses.size(); e++) {
                Movibus movibus = (Movibus) movibuses.get(e);
                if (movibus.getId()== movibusABorrar.getId()) {
                    movibuses.remove(e);
                    break;
                }
            }
        }
    }
}
