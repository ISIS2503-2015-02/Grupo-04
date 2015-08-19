/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.ejb;

import co.edu.uniandes.csw.yammz.tbc.dto.PedidoMovibus;
import co.edu.uniandes.csw.yammz.tbc.dto.Usuario;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioUsuarioMockLocal;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.yammz.tbc.persistencia.mock.ServicioPersistenciaMock;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author g.martinez10
 */
@Stateless
public class ServicioUsuarioMock implements IServicioUsuarioMockLocal{

    
    private IServicioPersistenciaMockLocal persistencia;
    
    public ServicioUsuarioMock(){
        persistencia= new ServicioPersistenciaMock();
    }
    

    @Override
    public void agregarUsuario(Usuario usuario) {
        persistencia.create(usuario);
    }

    @Override
    public List<Usuario> darUsuarios() {
        return persistencia.findAll(Usuario.class);
    }

    @Override
    public Usuario darUsuario(long id) {
        return (Usuario)persistencia.findById(Usuario.class, id);
    }

    @Override
    public void solicitarMovibus(PedidoMovibus pedmovi) {
        persistencia.create(pedmovi);
    }

    
}
