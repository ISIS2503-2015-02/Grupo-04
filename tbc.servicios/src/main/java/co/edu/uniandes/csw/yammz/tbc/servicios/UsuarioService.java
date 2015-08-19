/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.servicios;

import co.edu.uniandes.csw.yammz.tbc.dto.Usuario;
import co.edu.uniandes.csw.yammz.tbc.dto.PedidoMovibus;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioUsuarioMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author sy.velasquez10
 */
@Path("/Usuario")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
/**
 *
 * @author g.martinez10
 */
public class UsuarioService {
    
    @EJB
    private IServicioUsuarioMockLocal usuarioEjb;
    
    @GET
    @Path("usuarios/")
    public List<Usuario> getTodosLosUsuarios() {
        return usuarioEjb.darUsuarios();
    }
    
    @POST
    public Usuario agregarUsuario(Usuario usuario) {
        usuarioEjb.agregarUsuario(usuario);   
        return usuario;
    }
    
    @GET
    @Path("{id}")
    public Usuario darUsuario(@PathParam("id") long id) {
        return usuarioEjb.darUsuario(id);
    }
    
    @POST
    public PedidoMovibus solicitarMovibus(PedidoMovibus pedido) {
        usuarioEjb.solicitarMovibus(pedido);   
        return pedido;
    }
}
