/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.ejb;

import co.edu.uniandes.csw.yammz.tbc.dto.Conductor;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioConductorMockLocal;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.yammz.tbc.persistencia.mock.ServicioPersistenciaMock;
import java.util.List;

/**
 *
 * @author cfagu
 */
public class ServicioConductorMock implements IServicioConductorMockLocal {

    private IServicioPersistenciaMockLocal persistencia;

    public ServicioConductorMock() {
        persistencia=new ServicioPersistenciaMock();
    }
    
    @Override
    public void agregarConductor(Conductor conductor) {
        persistencia.create(conductor);
    }

    @Override
    public List<Conductor> darConductores() {
        return persistencia.findAll(Conductor.class);
    }

    @Override
    public Conductor darConductor(long id) {
        return (Conductor)persistencia.findById(Conductor.class, id);
    }

    @Override
    public void actualizarConductor(Conductor conductor) {
        persistencia.update(conductor);
    }

    @Override
    public void eliminarConductor(long id) {
        persistencia.delete(id);
    }
}
