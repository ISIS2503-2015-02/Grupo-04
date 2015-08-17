/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.interfaces;

import co.edu.uniandes.csw.yammz.tbc.dto.PedidoMovibus;
import java.util.List;

/**
 *
 * @author cfagu
 */
public interface IServicioPedidoMovibusMockLocal {
    
    public void agregarPedidoMovibus(PedidoMovibus movibus);
    
    public List<PedidoMovibus> darPedidosMovibus();
    
    public PedidoMovibus darPedidoMovibus(long id);
}
