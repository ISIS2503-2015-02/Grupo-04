/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.servicios;

import co.edu.uniandes.csw.yammz.tbc.dto.Movibus;
import co.edu.uniandes.csw.yammz.tbc.dto.PedidoMovibus;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioPedidoMovibusMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author cfagu
 */
@Path("/PedidoMovibus")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoMovibusService {
    
    @EJB
    private IServicioPedidoMovibusMockLocal pedidoMovibusEjb;
    
    @GET
    public List<PedidoMovibus> getPedidosMovibus() {
        return pedidoMovibusEjb.darPedidosMovibus();
    }
    
    @GET
    @Path("{id}")
    public PedidoMovibus getPedidoMovibus(@PathParam("id") long id) {
        return pedidoMovibusEjb.darPedidoMovibus(id);
    }
}
