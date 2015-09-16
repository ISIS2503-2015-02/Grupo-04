package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.Long;

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

    public final static String EMERGENCIA_MOVIBUS="Emergencia Movibus";

    /**
     * Constante que representa el nivel bajo de emergencia
     */
    public final static String MAGNITUD_BAJA="Baja";

    /**
     * Constante que representa el nivel medio de emergencia
     */
    public final static String MAGNITUD_MEDIA="Media";

    /**
     * Constante que representa el nivel alto de emergencia
     */
    public final static String MAGNITUD_ALTA="Alta";

    /**
     * Constante que representa estrellarse
     */
    public final static int CHOQUE= 0;

    /**
     * Constante que representa vararse
     */
    public final static int DANIO= 1;

    /**
     * Constante que representa robo
     */
    public final static int ROBO= 2;


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
     * Describe el tipo de accidente
     */
    private int tipoAccidente;

    /**
     * Describe la magnitud de la emergencia
     */
    private String magnitud;

    /**
     * Identificador de la estacion o tranvia que creo el reporte.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //---------------------------------------------------------------------
    // Constructores
    //---------------------------------------------------------------------

    public Reporte() {}

    public Reporte(String tipoReporte, String descripcion, String magnitud, int tipoAccidente) {
        this.tipoReporte=tipoReporte;
        this.descripcion=descripcion;
        this.magnitud=magnitud;
        this.tipoAccidente=tipoAccidente;
    }

    public Reporte(String tipoReporte, String descripcion, String magnitud) {
        this.tipoReporte=tipoReporte;
        this.descripcion=descripcion;
        this.magnitud=magnitud;
    }

    //---------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------

    public int getTipoAccidente() {
        return tipoAccidente;
    }

    public void setTipoAccidente(int tipoAccidente) {
        this.tipoAccidente = tipoAccidente;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }

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
