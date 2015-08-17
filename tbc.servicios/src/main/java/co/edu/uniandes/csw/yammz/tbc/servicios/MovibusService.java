/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.servicios;

import co.edu.uniandes.csw.yammz.tbc.dto.Movibus;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioMovibusMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author cfagu
 */
@Path("/Movibus")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovibusService {
    @EJB
    private IServicioMovibusMockLocal movibusEjb;
    
    @GET
    public List<Movibus> getMovibuses() {
        return movibusEjb.darMovibuses();
    }
    
    @POST
    public Movibus agregarMovibus(Movibus movibus) {
        movibusEjb.agregarMovibus(movibus);   
        return movibus;
    }
    
    @GET
    @Path("{id}")
    public Movibus getMovibus(@PathParam("id") long id) {
        return movibusEjb.darMovibus(id);
    }
}
