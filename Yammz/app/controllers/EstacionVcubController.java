package controllers;

import models.EstacionVcub;
import models.Usuario;
import models.Reporte;

import java.util.List;

/**
 * Created by s.baquero10 on 19/08/2015.
 */
public class EstacionVcubController {
    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = request().body().asJson();
        EstacionVcub estacionVcub = Json.fromJson(j, EstacionVcub.class);
        estacionVcub.save();
        return ok(Json.toJson(estacionVcub));
    }

    public Result read() {
        List<EstacionVcub> estaciones = new Model.Finder(String.class, EstacionVcub.class).all();
        return ok(Json.toJson(estaciones));
    }

    public Result retiroVcub(Long ide, Long idu){
        EstacionVcub estacion = (EstacionVcub)new Model.Finder(Long.class, EstacionVcub.class).byId(ide);
        Usuario usuario = (Usuario)new Model.Finder(Long.class, Usuario.class).byId(idu);
        estacion.prestarVcub();
        if(estacion.solicitarVcbus()&&estacion.isEnvioReporte()){
            Reporte reporte = (Reporte)new Model.Finder(Long.class, Reporte.class);
            reporte.setDescripcion("Se necesitan "+(estacion.getCapacidad()-estacion.getVcubs())+" Vcubs en la estacion "+estacion.getNombre());
            reporte.setTipoReporte(Reporte.PEDIDO_BICICLETAS);
            reporte.save();
        }
        usuario.agregarVcubEnUso();
        usuario.save();
        estacion.save();
        return ok(Json.toJson(usuario));
    }

    public Result devolucionVcub(Long ide, Long idu){
        EstacionVcub estacion = (EstacionVcub)new Model.Finder(Long.class, EstacionVcub.class).byId(ide);
        Usuario usuario = (Usuario)new Model.Finder(Long.class, Usuario.class).byId(idu);
        estacion.restituirVcub();
        usuario.devolverVcubEnUso();
        usuario.save();
        estacion.save();
        return ok(Json.toJson(usuario));
    }

    public Result llenarEstacion(Long id){
        EstacionVcub estacion = (EstacionVcub)new Model.Finder(Long.class, EstacionVcub.class).byId(id);
        estacion.llenarEstacion();
        estacion.save();
        return ok(Json.toJson(estacion));
    }
}

