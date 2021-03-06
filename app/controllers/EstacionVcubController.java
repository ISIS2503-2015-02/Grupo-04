package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.EstacionVcub;
import models.Reporte;
import models.Usuario;
import models.Logger;
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
        estacionVcub.setVcubs(estacionVcub.getVcubs());
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

    public Result retiroVcub(Long idEstacion, Long ccUsuario){
        EstacionVcub estacion = (EstacionVcub)new Model.Finder(Long.class, EstacionVcub.class).byId(idEstacion);
        List<Usuario> users=new Model.Finder(Long.class, Usuario.class).all();
        Usuario usuario = null;
        for(Usuario u:users){
            if(u.getCedula()==ccUsuario){
                usuario = u;
            }
        }
        estacion.prestarVcub();
        if(estacion.solicitarVcbus()&&!estacion.haReportado()){
            Reporte reporte = (Reporte)new Reporte(Reporte.PEDIDO_BICICLETAS, "Se necesitan "+(estacion.getCapacidad()-estacion.getVcubs())+" Vcubs en la estacion "+estacion.getNombre(),"Baja");
            reporte.save();
            estacion.cambioReporte(true);
        }
        usuario.agregarVcubEnUso();
        usuario.save();
        estacion.save();
        return ok(Json.toJson(usuario));
    }

    public Result devolverVcub(Long idEstacion, Long ccUsuario){
        EstacionVcub estacion = (EstacionVcub)new Model.Finder(Long.class, EstacionVcub.class).byId(idEstacion);
        List<Usuario> users=new Model.Finder(Long.class, Usuario.class).all();
        Usuario usuario = null;
        for(Usuario u:users){
            if(u.getCedula()==ccUsuario){
                usuario = u;
            }
        }
        estacion.restituirVcub();
        try {
            usuario.devolverVcubEnUso();
        }catch(Exception e){
           Logger.info(e);
            return ok(Json.toJson("error:"+e.getMessage()));
        }
        usuario.save();
        estacion.save();
        return ok(Json.toJson(usuario));
    }

    public Result llenarEstacion(Long id){
        EstacionVcub estacion = (EstacionVcub)new Model.Finder(Long.class, EstacionVcub.class).byId(id);
        estacion.llenarEstacion();
        estacion.cambioReporte(false);
        estacion.save();
        return ok(Json.toJson(estacion));
    }
}