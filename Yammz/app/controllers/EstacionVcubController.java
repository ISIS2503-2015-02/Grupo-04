package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.EstacionVcub;
import models.Reporte;
import models.Usuario;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class EstacionVcubController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = request().body().asJson();
        EstacionVcub estacionVcub = Json.fromJson(j, EstacionVcub.class);
        estacionVcub.setVcubs(estacionVcub.getCapacidad());
        estacionVcub.save();
        return ok(Json.toJson(estacionVcub));
    }

    public Result read() {
        List<EstacionVcub> estaciones = new Model.Finder(Long.class, EstacionVcub.class).all();
        return ok(Json.toJson(estaciones));
    }

    public Result get(Long id) {
        EstacionVcub estacionVcub = (EstacionVcub) new Model.Finder(Long.class, EstacionVcub.class).byId(id);
        ObjectNode result = Json.newObject();
        if (estacionVcub == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(estacionVcub));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result retirarVcub() {
        JsonNode j = request().body().asJson();
        Long idEstacion=j.findPath("idEstacion").asLong();
        Long idUsuario=j.findPath("idUsuario").asLong();
        EstacionVcub estacion = (EstacionVcub)new Model.Finder(Long.class, EstacionVcub.class).byId(idEstacion);
        Usuario usuario = (Usuario)new Model.Finder(Long.class, Usuario.class).byId(idUsuario);
        estacion.prestarVcub();
        usuario.agregarVcubEnUso();
        if(estacion.solicitarVcbus()){
            Reporte reporte = new Reporte(Reporte.PEDIDO_BICICLETAS, "Se necesitan "+(estacion.getCapacidad()-estacion.getVcubs())+" Vcubs en la estacion "+estacion.getNombre(),Reporte.MAGNITUD_BAJA);
            reporte.save();
        }
        usuario.save();
        estacion.save();
        return ok(Json.toJson(usuario));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result devolverVcub() {
        JsonNode j = request().body().asJson();
        Long idEstacion=j.findPath("idEstacion").asLong();
        Long idUsuario=j.findPath("idUsuario").asLong();
        EstacionVcub estacion = (EstacionVcub)new Model.Finder(Long.class, EstacionVcub.class).byId(idEstacion);
        Usuario usuario = (Usuario)new Model.Finder(Long.class, Usuario.class).byId(idUsuario);
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