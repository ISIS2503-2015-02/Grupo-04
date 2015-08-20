package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.Long;

/**
 * Created by g.martinez10 on 15/08/2015.
 */
@Entity
public class Reporte extends Model{


    //---------------------------------------------------------------------
    // Constantes
    //---------------------------------------------------------------------

    /**
     * Constante que define que si el reporte es para realizar un pedido de biciletas a una estacion
     */
    public final static String PEDIDO_BICICLETAS="Pedido bicicletas";

    /**
     * Constante que define que si el reporte es para reportar una emergencia de un tranvia
     */
    public final static String EMERGENCIA_TRANVIA="Emergencia Tranvia";

    //---------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------

    /**
     * El tipo de reporte en especifico que pude tomar el valor de una de las constantes de la clase.
     */
    private String tipoReporte;

    /**
     * Descripcion del reporte.
     */
    private String descripcion;

    /**
     * Identificador de la estacion o tranvia que creo el reporte.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    //---------------------------------------------------------------------
    // Constructores
    //---------------------------------------------------------------------

    public Reporte()
    {
    }

    public Reporte(String tipoRep, String descrip, Long identificacion)
    {
        if(tipoRep.equals(PEDIDO_BICICLETAS))
        {
            this.tipoReporte=PEDIDO_BICICLETAS;
        }
        else
        {
            this.tipoReporte=EMERGENCIA_TRANVIA;
        }
        this.descripcion=descrip;
        this.id=identificacion;
    }

    //---------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
