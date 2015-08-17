/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.servicios;

import co.edu.uniandes.csw.yammz.tbc.dto.Tranvia;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioTranviaMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author sy.velasquez10
 */
@Path("/Tranvia")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TranviaService {
    @EJB
    private IServicioTranviaMockLocal tranviaEjb;
    
    @GET
    @Path("tranvias/")
    public List<Tranvia> getTodosLosTranvias() {
        return tranviaEjb.darTranvias();
 
    }
    
    @POST
    @Path("agregar/") 
    public Tranvia agregarTranvia(Tranvia tranvia) {
        tranviaEjb.agregarTranvia(tranvia);   
        return tranvia;
    }
 
}
