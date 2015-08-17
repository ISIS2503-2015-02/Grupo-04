/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.servicios;

import co.edu.uniandes.csw.yammz.tbc.dto.Reporte;
import co.edu.uniandes.csw.yammz.tbc.dto.Tranvia;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioReporteMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author sy.velasquez10
 */
@Path("/Reporte")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReporteService {
     @EJB
    private IServicioReporteMockLocal reporteEjb;
    
    @GET
    @Path("reportes/")
    public List<Reporte> getTodosLosReportes() {
        return reporteEjb.darReportes();
 
    }
    
    @DELETE
    @Path("reporte/") 
    public Reporte eliminarReporte(Reporte reporte) {
        reporteEjb.eliminarReporte(reporte.getId());   
        return reporte;
    }
    
    @PUT
    @Path("actualizarReporte/") 
    public Reporte actualizarReporte(Reporte reporte) {
        reporteEjb.actualizarReporte(reporte);   
        return reporte;
    }
}
