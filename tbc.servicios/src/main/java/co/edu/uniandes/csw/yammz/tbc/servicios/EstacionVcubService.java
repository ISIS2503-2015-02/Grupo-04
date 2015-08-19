/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.servicios;

import co.edu.uniandes.csw.yammz.tbc.dto.EstacionVcub;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioEstacionVcubMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author s.baquero10
 */

@Path("/estacionvcub")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstacionVcubService {
    @EJB
    private IServicioEstacionVcubMockLocal estacionVcubEjb;
    
    @POST
    public EstacionVcub agregarEstacionVcub(EstacionVcub estacion) {
        estacionVcubEjb.agregarEstacionVcub(estacion);   
        return estacion;
    }
    
    @GET
    @Path("{id}")
    public EstacionVcub darMovibus(@PathParam("id") long id) {
        return estacionVcubEjb.darEstacionVcub(id);
    }
    
    @PUT
    @Path("{id}")
    public void llenarEstacion(@PathParam("id") long id){
        estacionVcubEjb.llenarEstacion(id);
    }
    
    @PUT
    @Path("{ide}/usuario/{idu}")
    public void retiroVcub(@PathParam("ide") long ide, @PathParam("idu") long idu){
        estacionVcubEjb.retiroVcub(ide, idu);
    }
    
    @PUT
    @Path("{ide}/usuario/{idu}/devolucionVcub")
    public void devolucionVcub(@PathParam("ide") long ide, @PathParam("idu") long idu){
        estacionVcubEjb.devolucionVcub(ide, idu);
    }
}
