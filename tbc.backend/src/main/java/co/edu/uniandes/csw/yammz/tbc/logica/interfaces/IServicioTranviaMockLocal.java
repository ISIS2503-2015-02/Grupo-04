/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.interfaces;
import co.edu.uniandes.csw.yammz.tbc.dto.Reporte;
import co.edu.uniandes.csw.yammz.tbc.dto.Tranvia;
import java.util.List;
import javax.ejb.Local;
/**
 *
 * @author sy.velasquez10
 */
@Local
public interface IServicioTranviaMockLocal {
    public void agregarTranvia(Tranvia tranvia);
    
    public void agregarReporte(Reporte reporte);
    
    public void actualizarTranvia(Tranvia tranvia);
    
    public void eliminarTranvia(long id);
    
    public List<Tranvia> darTranvias();
}
