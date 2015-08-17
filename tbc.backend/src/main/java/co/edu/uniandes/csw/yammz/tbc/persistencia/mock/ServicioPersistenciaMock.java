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
    
    private static LinkedList<EstacionVcub> estacionesVcub;
    
    private static LinkedList<Movibus> movibuses;
    
    private static LinkedList<PedidoMovibus> pedidosMovibus;
    
    private static LinkedList<Reporte> reportes;
    
    private static ArrayList<Tranvia> tranvias;
    
    /**
     * Constructor de la clase. Inicializa los atributos.
     */
    public ServicioPersistenciaMock() {
        estacionesVcub=new LinkedList();
        movibuses=new LinkedList();
        pedidosMovibus=new LinkedList();
        reportes=new LinkedList();    
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
        if (obj instanceof Tranvia)
        {
            Tranvia t = (Tranvia) obj;
            t.setId(tranvias.size()+1);
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

    @Override
    public Object findById(Class c, Object id) {
        if (c.equals(Tranvia.class))
        {
            for (Object v : findAll(c))
            {
                Tranvia t = (Tranvia) v;
                if (t.getId() == Long.parseLong(id.toString()))
                {
                    return t;
                }
            }
        } 
        return null;
    }    

    @Override
    public void update(Object obj) {
        if (obj instanceof Tranvia)
        {
            Tranvia editar = (Tranvia) obj;
            Tranvia tranvia;
            for (int i = 0; i < tranvias.size(); i++)
            {
                tranvia = tranvias.get(i);
                if (tranvia.getId() == editar.getId())
                {
                    tranvias.set(i, editar);
                    break;
                }

            }

        }
    }

    @Override
    public void delete(Object obj) {
         if (obj instanceof Tranvia)
        {
            Tranvia tranviaABorrar = (Tranvia) obj;

            for (int e = 0; e < tranvias.size(); e++)
            {
                Tranvia ven = (Tranvia) tranvias.get(e);
                if (ven.getId() == tranviaABorrar.getId())
                {
                    tranvias.remove(e);
                    break;
                }
            }
        } 
    }
}
