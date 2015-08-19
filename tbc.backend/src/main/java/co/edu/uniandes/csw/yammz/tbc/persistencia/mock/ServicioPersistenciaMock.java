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
import co.edu.uniandes.csw.yammz.tbc.dto.Usuario;
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
    
    private static ArrayList<Usuario> usuarios;
    
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
        usuarios=new ArrayList();        
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
        else if(obj instanceof Usuario) {
            Usuario usuario = (Usuario) obj;
            usuario.setId(usuarios.size()+1);
            usuarios.add(usuario);
        }
        else if(obj instanceof EstacionVcub) {
            EstacionVcub estacion = (EstacionVcub)obj;
            estacion.setId(estacionesVcub.size()+1);
            estacionesVcub.add(estacion);
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
        else if(c.equals(Usuario.class)) {
            return usuarios;
        }
        else if(c.equals(EstacionVcub.class)) {
            return estacionesVcub;
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
        else if (obj instanceof Movibus) {
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
        else if(obj instanceof Usuario) {
            Usuario editar = (Usuario) obj;
            Usuario usuario;
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                if (usuario.getId()==editar.getId()) {
                    usuarios.set(i, editar);
                    break;
                }
            }
        }
        else if(obj instanceof EstacionVcub) {
            EstacionVcub editar = (EstacionVcub) obj;
            EstacionVcub estacion;
            for (int i = 0; i < estacionesVcub.size(); i++) {
                estacion = estacionesVcub.get(i);
                if (estacion.getId()==editar.getId()) {
                    estacionesVcub.set(i, editar);
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
                }
            }
        }
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
        if (obj instanceof Usuario) {
            Usuario usuarioABorrar = (Usuario) obj;
            for (int e = 0; e < usuarios.size(); e++) {
                Usuario usuario = (Usuario) usuarios.get(e);
                if (usuario.getId()== usuarioABorrar.getId()) {
                    usuarios.remove(e);
                    break;
                }
            }
        }
        if (obj instanceof EstacionVcub) {
            EstacionVcub estacionABorrar = (EstacionVcub) obj;
            for (int e = 0; e < estacionesVcub.size(); e++) {
                EstacionVcub estacion = (EstacionVcub) estacionesVcub.get(e);
                if (estacion.getId() == estacionABorrar.getId()) {
                    estacionesVcub.remove(e);
                    break;
    }
            }
        }
    }

    @Override
    public Object findById(Class c, long id) {
        if (c.equals(Tranvia.class)){
            for (Object v : findAll(c)) {
                Tranvia t = (Tranvia) v;
                if(t.getId()==id){
                    return t;
                }
            }
        }
        else if (c.equals(Movibus.class)) {
            for (Object v : findAll(c)) {
                Movibus movibus = (Movibus) v;
                if (movibus.getId()==id) {
                    return movibus;
                }
            }
        }
        else if (c.equals(EstacionVcub.class)) {
            for (Object v : findAll(c)){
                EstacionVcub estacion = (EstacionVcub)v;
                if(estacion.getId()==id){
                    return estacion;
                }
            }
        }
        else if (c.equals(Usuario.class)) {
            for (Object v : findAll(c)){
                Usuario usuario = (Usuario)v;
                if(usuario.getId()==id){
                    return usuario;
                }
            }
        }
        return null;
    }
}
