/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.interfaces;

import co.edu.uniandes.csw.yammz.tbc.dto.PedidoMovibus;
import co.edu.uniandes.csw.yammz.tbc.dto.Usuario;
import java.util.List;
/**
 *
 * @author g.martinez10
 */
public interface IServicioUsuarioMockLocal {
    
    public void agregarUsuario(Usuario usuario);
    
    public List<Usuario> darUsuarios();
    
    public Usuario darUsuario(long id);
    
    public void solicitarMovibus(PedidoMovibus pedmovi);
    
    public void eliminarUsuario(long id);
}
