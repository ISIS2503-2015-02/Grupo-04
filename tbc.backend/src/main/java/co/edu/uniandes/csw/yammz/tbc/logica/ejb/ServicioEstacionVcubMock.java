package co.edu.uniandes.csw.yammz.tbc.logica.ejb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.edu.uniandes.csw.yammz.tbc.dto.EstacionVcub;
import co.edu.uniandes.csw.yammz.tbc.dto.Usuario;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioEstacionVcubMockLocal;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.yammz.tbc.persistencia.mock.ServicioPersistenciaMock;
import javax.ejb.Stateless;

/**
 *
 * @author s.baquero10
 */
@Stateless
public class ServicioEstacionVcubMock implements IServicioEstacionVcubMockLocal{
    
    private IServicioPersistenciaMockLocal persistencia;
    
    public ServicioEstacionVcubMock(){
        persistencia = new ServicioPersistenciaMock();
    }
    
    @Override
    public void agregarEstacionVcub(EstacionVcub estacion) {
        persistencia.create(estacion);
    }

    @Override
    public EstacionVcub darEstacionVcub(Long id) {
        return (EstacionVcub)persistencia.findById(EstacionVcub.class, id);
    }

    @Override
    public void llenarEstacion(Long id) {
        EstacionVcub estacion = (EstacionVcub)persistencia.findById(EstacionVcub.class, id);
        estacion.setVcubs(estacion.getCapacidad());
        persistencia.update(estacion);
    }

    @Override
    public void retiroVcub(Long ide, Long idu) {
        EstacionVcub estacion = (EstacionVcub)persistencia.findById(EstacionVcub.class, ide);
        estacion.setVcubs(estacion.getVcubs()-1);
        persistencia.update(estacion);
        Usuario usuario = (Usuario)persistencia.findById(Usuario.class, idu);
        usuario.setvCubsEnUso(usuario.getvCubsEnUso()+1);
        persistencia.update(usuario);
    }

    @Override
    public void devolucionVcub(Long ide, Long idu) {
        EstacionVcub estacion = (EstacionVcub)persistencia.findById(EstacionVcub.class, ide);
        estacion.setVcubs(estacion.getVcubs()+1);
        persistencia.update(estacion);
        Usuario usuario = (Usuario)persistencia.findById(Usuario.class, idu);
        usuario.setvCubsEnUso(usuario.getvCubsEnUso()-1);
        persistencia.update(usuario);
    }
    
}
