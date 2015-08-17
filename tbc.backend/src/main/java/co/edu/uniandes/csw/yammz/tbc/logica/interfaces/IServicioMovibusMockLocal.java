/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.interfaces;

import co.edu.uniandes.csw.yammz.tbc.dto.Movibus;
import java.util.List;

/**
 *
 * @author cfagu
 */
public interface IServicioMovibusMockLocal {
    
    public void agregarMovibus(Movibus movibus);
    
    public List<Movibus> darMovibuses();
    
    public Movibus darMovibus(long id);
    
    public void actualizarMovibus(Movibus movibus);
}
