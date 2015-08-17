/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.ejb;

import co.edu.uniandes.csw.yammz.tbc.dto.Reporte;
import co.edu.uniandes.csw.yammz.tbc.dto.Tranvia;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioTranviaMockLocal;
import co.edu.uniandes.csw.yammz.tbc.persistencia.mock.ServicioPersistenciaMock;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author sy.velasquez10
 */
@Stateless
public class ServicioTranviaMock implements IServicioTranviaMockLocal{
    
    private IServicioPersistenciaMockLocal persistencia;

    public ServicioTranviaMock(){
        persistencia= new ServicioPersistenciaMock();
    }
 
    public void agregarTranvia(Tranvia tranvia){
        persistencia.create(tranvia);
    }
   
    public List<Tranvia> darTranvias(){
        return persistencia.findAll(Tranvia.class);
    }

    @Override
    public void agregarReporte(Reporte reporte) {
       persistencia.create(reporte);
    }

    @Override
    public void actualizarTranvia(Tranvia tranvia) {
        persistencia.update(tranvia);
    }

    @Override
    public void eliminarTranvia(long id){
        Tranvia t=(Tranvia) persistencia.findById(Tranvia.class, id);
        persistencia.delete(t);
    }
    
}
