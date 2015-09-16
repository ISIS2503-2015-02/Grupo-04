package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Reporte;
import models.Tranvia;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TranviaController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = request().body().asJson();
        Tranvia tranvia = Json.fromJson(j, Tranvia.class);
        tranvia.save();
        return ok(Json.toJson(tranvia));
    }

    public Result read(){
        List<Tranvia> tranvias = new Model.Finder(Long.class, Tranvia.class).all();
        return ok(Json.toJson(tranvias));
    }

    public Result get(Long id) {
        Tranvia tranvia = (Tranvia) new Model.Finder(Long.class, Tranvia.class).byId(id);
        ObjectNode result = Json.newObject();
        if(tranvia == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(tranvia));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();
        Tranvia tranvia = (Tranvia) new Model.Finder(Long.class, Tranvia.class).byId(id);
        ObjectNode result = Json.newObject();
        if(tranvia == null)
            return ok(Json.toJson(result));
        else {
            Tranvia tranviaNuevo;
            tranviaNuevo = Tranvia.bind(j);
            tranvia.update(tranviaNuevo);
            tranvia.save();
            return ok(Json.toJson(tranvia));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result reportarAccidente(Long id) {
        JsonNode j = request().body().asJson();
        String descripcion = j.findPath("descripcion").asText();
        String magnitud=j.findPath("magnitud").asText();
        int tipoAccidente=j.findParent("tipoAccidente").asInt();
        Reporte reporte= new Reporte(Reporte.EMERGENCIA_TRANVIA, descripcion,magnitud,tipoAccidente);
        reporte.save();
        return ok(Json.toJson(reporte));
    }

    public Result getAccidenteMasComun() {
        int choque=0;
        int danio=0;
        int robo=0;
        List<Reporte> reportes = new Model.Finder(Long.class, Reporte.class).all();
        for (int i=0;i<reportes.size();i++){
            Reporte r=reportes.get(i);
            if(r.getTipoReporte().equals(Reporte.EMERGENCIA_TRANVIA)&&r.getTipoAccidente()==Reporte.CHOQUE){
                choque++;
            }
            else if(r.getTipoReporte().equals(Reporte.EMERGENCIA_TRANVIA)&&r.getTipoAccidente()==Reporte.DANIO){
                danio++;
            }
            else if(r.getTipoReporte().equals(Reporte.EMERGENCIA_TRANVIA)&&r.getTipoAccidente()==Reporte.ROBO){
                robo++;
            }
        }
        if(choque>danio && choque>robo){
            return ok("Choque");
        }
        else if(danio>choque && danio>robo){
            return ok("Dano tecnico");
        }
        else {
            return ok("Robo");
        }
    }
}
