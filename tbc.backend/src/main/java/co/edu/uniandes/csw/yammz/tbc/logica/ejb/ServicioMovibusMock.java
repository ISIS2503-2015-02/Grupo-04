/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.ejb;

import co.edu.uniandes.csw.yammz.tbc.dto.Movibus;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioMovibusMockLocal;
import co.edu.uniandes.csw.yammz.tbc.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.yammz.tbc.persistencia.mock.ServicioPersistenciaMock;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author cfagu
 */
@Stateless
public class ServicioMovibusMock implements IServicioMovibusMockLocal {
    
    private IServicioPersistenciaMockLocal persistencia;

    public ServicioMovibusMock() {
        persistencia= new ServicioPersistenciaMock();
    }
    
    @Override
    public void agregarMovibus(Movibus movibus) {
        persistencia.create(movibus);
    }

    @Override
    public List<Movibus> darMovibuses() {
        return persistencia.findAll(Movibus.class);
    }

    @Override
    public Movibus darMovibus(long id) {
        return (Movibus)persistencia.findById(Movibus.class, id);
    }

    @Override
    public void actualizarMovibus(Movibus movibus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
