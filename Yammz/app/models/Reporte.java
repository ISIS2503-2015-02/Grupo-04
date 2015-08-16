package models;

import com.avaje.ebean.Model;
import javax.persistence.*;

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
    private final String PEDIDO_BICICLETAS="Pedidoo bicicletas";

    /**
     * Constante que define que si el reporte es para reportar una emergencia de un tranvia
     */
    private final String EMERGENCIA_TRANVIA="Emergencia Tranvia";

    //---------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------

    /**
     * El tipo de reporte en especifico que pude tomar el valor de una de las constantes de la clase.
     */
    private String tipoReporte;

    /**
     * Descripci�n del reporte.
     */
    private String descripcion;

    /**
     * Identificador de la estacion o tranvia que creo el reporte.
     */
    private int id;

    //---------------------------------------------------------------------
    // Constructores
    //---------------------------------------------------------------------

    public Reporte()
    {
    }

    public Reporte(String tipoRep, String descrip, int identificacion)
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
