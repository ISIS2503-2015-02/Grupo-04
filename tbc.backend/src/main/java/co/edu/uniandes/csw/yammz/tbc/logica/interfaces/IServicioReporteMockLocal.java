/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.interfaces;

import co.edu.uniandes.csw.yammz.tbc.dto.Reporte;
import co.edu.uniandes.csw.yammz.tbc.dto.Tranvia;
import java.util.List;

/**
 *
 * @author sy.velasquez10
 */
public interface IServicioReporteMockLocal {
    public void actualizarReporte(Reporte reporte);
    
    public void eliminarReporte(long id);
    
    public List<Reporte> darReportes();
}
