/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.ejb;

import co.edu.uniandes.csw.yammz.tbc.dto.Reporte;
import co.edu.uniandes.csw.yammz.tbc.dto.Tranvia;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioReporteMockLocal;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author sy.velasquez10
 */
@Stateless
public class ServicioReporteMock implements IServicioReporteMockLocal{
    
    private IServicioPersistenciaMockLocal persistencia;

    @Override
    public void actualizarReporte(Reporte reporte) {
        persistencia.update(reporte);
    }

    @Override
    public void eliminarReporte(long id) {
        Reporte r=(Reporte) persistencia.findById(Reporte.class, id);
        persistencia.delete(r);
    }

    @Override
    public List<Reporte> darReportes() {
        return persistencia.findAll(Reporte.class);
    }
    
}
