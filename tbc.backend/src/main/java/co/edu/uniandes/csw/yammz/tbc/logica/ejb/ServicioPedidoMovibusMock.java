/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.ejb;

import co.edu.uniandes.csw.yammz.tbc.dto.PedidoMovibus;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioPedidoMovibusMockLocal;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.yammz.tbc.persistencia.mock.ServicioPersistenciaMock;
import java.util.List;

/**
 *
 * @author cfagu
 */
public class ServicioPedidoMovibusMock implements IServicioPedidoMovibusMockLocal{

    private IServicioPersistenciaMockLocal persistencia;
    
    public ServicioPedidoMovibusMock() {
        persistencia=new ServicioPersistenciaMock();
    }
    
    @Override
    public void agregarPedidoMovibus(PedidoMovibus pedidoMovibus) {
        persistencia.create(pedidoMovibus);
    }

    @Override
    public List<PedidoMovibus> darPedidosMovibus() {
        return persistencia.findAll(PedidoMovibus.class);
    }

    @Override
    public PedidoMovibus darPedidoMovibus(long id) {
        return (PedidoMovibus)persistencia.findById(PedidoMovibus.class, id);
    }
}
