/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.interfaces;

import co.edu.uniandes.csw.yammz.tbc.dto.Conductor;
import java.util.List;

/**
 *
 * @author cfagu
 */
public interface IServicioConductorMockLocal {
    
    public void agregarConductor(Conductor conductor);
    
    public List<Conductor> darConductores();
    
    public Conductor darConductor(long id);
    
    public void actualizarConductor(Conductor conductor);
    
    public void eliminarConductor(long id);
}
