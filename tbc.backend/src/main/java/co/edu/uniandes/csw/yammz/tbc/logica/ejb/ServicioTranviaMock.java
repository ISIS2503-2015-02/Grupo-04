/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.yammz.tbc.logica.ejb;

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

    public ServicioTranviaMock()
    {
        persistencia= new ServicioPersistenciaMock();
    }
 
    public void agregarTranvia(Tranvia tranvia)
    {
        persistencia.create(tranvia);
    }
   
    public List<Tranvia> darTranvias()
    {
        return persistencia.findAll(Tranvia.class);
    }

    
}
