/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.persistencia.mock;

import co.edu.uniandes.csw.yammz.tbc.dto.Conductor;
import co.edu.uniandes.csw.yammz.tbc.dto.EstacionVcub;
import co.edu.uniandes.csw.yammz.tbc.dto.Movibus;
import co.edu.uniandes.csw.yammz.tbc.dto.PedidoMovibus;
import co.edu.uniandes.csw.yammz.tbc.dto.Reporte;
import co.edu.uniandes.csw.yammz.tbc.dto.Tranvia;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioPersistenciaMockLocal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cfagu
 */
public class ServicioPersistenciaMock implements IServicioPersistenciaMockLocal {
    
    private static ArrayList<EstacionVcub> estacionesVcub;
    
    private static ArrayList<Movibus> movibuses;
    
    private static ArrayList<Conductor> conductores;
    
    private static ArrayList<PedidoMovibus> pedidosMovibus;
    
    private static ArrayList<Reporte> reportes;
    
    private static ArrayList<Tranvia> tranvias;
    
    /**
     * Constructor de la clase. Inicializa los atributos.
     */
    public ServicioPersistenciaMock() {
        estacionesVcub=new ArrayList();
        movibuses=new ArrayList();
        conductores=new ArrayList();
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
    public void create(Object obj) {
        if (obj instanceof Tranvia) {
            Tranvia t = (Tranvia) obj;
            t.setId(tranvias.size()+1);
            tranvias.add(t);
        }
        else if(obj instanceof Movibus) {
            Movibus movibus = (Movibus) obj;
            movibus.setId(movibuses.size()+1);
            movibuses.add(movibus);
        }
        else if(obj instanceof PedidoMovibus) {
            PedidoMovibus pedidoMovibus = (PedidoMovibus) obj;
            pedidoMovibus.setId(pedidosMovibus.size()+1);
            pedidosMovibus.add(pedidoMovibus);
        }
        else if(obj instanceof Conductor) {
            Conductor conductor = (Conductor) obj;
            conductor.setId(conductores.size()+1);
            conductores.add(conductor);
        }
    }

     /** Retorna la lista de todos los elementos de una clase dada que se encuentran en el sistema.
     * @param c Clase cuyos objetos quieren encontrarse en el sistema.
     * @return list Listado de todos los objetos de una clase dada que se encuentran en el sistema.
     */
    @Override
    public List findAll(Class c) {
        if (c.equals(Tranvia.class)) {
            return tranvias;
        } 
        else if(c.equals(Movibus.class)) {
            return movibuses;
        }
        else if(c.equals(PedidoMovibus.class)) {
            return pedidosMovibus;
        }
        else if(c.equals(Conductor.class)) {
            return conductores;
        }
        return null;
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
        else if(c.equals(PedidoMovibus.class)) {
            for (Object v : findAll(c)) {
                PedidoMovibus pedidoMovibus = (PedidoMovibus) v;
                if (pedidoMovibus.getId()==id) {
                    return pedidoMovibus;
                }
            }
        }
        else if(c.equals(Conductor.class)) {
            for (Object v : findAll(c)) {
                Conductor conductor = (Conductor) v;
                if (conductor.getId()==id) {
                    return conductor;
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
        else if(obj instanceof Conductor) {
            Conductor editar = (Conductor) obj;
            Conductor conductor;
            for (int i = 0; i < conductores.size(); i++) {
                conductor = conductores.get(i);
                if (conductor.getId()==editar.getId()) {
                    conductores.set(i, editar);
                    break;
                }
            }
        }
    }

    @Override
    public void delete(Object obj) {
        if (obj instanceof Conductor) {
            Conductor conductorABorrar = (Conductor) obj;
            for (int e = 0; e < conductores.size(); e++) {
                Conductor conductor = (Conductor) conductores.get(e);
                if (conductor.getId()== conductorABorrar.getId()) {
                    conductores.remove(e);
                    break;
                }
            }
        }
    }
}
