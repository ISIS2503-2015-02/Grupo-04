/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.interfaces;

import co.edu.uniandes.csw.yammz.tbc.dto.EstacionVcub;
import java.util.List;

/**
 *
 * @author s.baquero10
 */
public interface IServicioEstacionVcubMockLocal {
    
    public void agregarEstacionVcub(EstacionVcub estacion);
    
    public EstacionVcub darEstacionVcub(Long id);
    
    public void llenarEstacion(Long id);

    public void retiroVcub(Long ide, Long idu);

    public void devolucionVcub(Long ide, Long idu);
}
