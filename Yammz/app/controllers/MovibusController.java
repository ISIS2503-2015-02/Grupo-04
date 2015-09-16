package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Movibus;
import models.PedidoMovibus;
import models.Reporte;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

public class MovibusController {

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = request().body().asJson();
        Movibus movibus = Json.fromJson(j, Movibus.class);
        movibus.save();
        return ok(Json.toJson(movibus));
    }

    public Result read() {
        List<Movibus> movibuses = new Model.Finder(Long.class, Movibus.class).all();
        return ok(Json.toJson(movibuses));
    }

    public Result get(Long id) {
        Movibus movibus = (Movibus) new Model.Finder(Long.class, Movibus.class).byId(id);
        ObjectNode result = Json.newObject();
        if(movibus == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(movibus));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();
        Movibus movibusViejo = (Movibus) new Model.Finder(Long.class, Movibus.class).byId(id);
        ObjectNode result = Json.newObject();
        if(movibusViejo == null)
            return ok(Json.toJson(result));
        else {
            Movibus movibusNuevo = Movibus.bind(j);
            movibusViejo.update(movibusNuevo);
            movibusViejo.save();
            return ok(Json.toJson(movibusViejo));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result reportarAccidente(Long id) {
        JsonNode j = request().body().asJson();
        String descripcion = j.findPath("descripcion").asText();
        String magnitud=j.findPath("magnitud").asText();
        int tipoAccidente=j.findParent("tipoAccidente").asInt();
        Reporte reporte= new Reporte(Reporte.EMERGENCIA_MOVIBUS, descripcion,magnitud,tipoAccidente);
        reporte.save();
        return ok(Json.toJson(reporte));
    }

    public Result getAccidenteMasComun() {
        int choque=0;
        int danio=0;
        int robo=0;
        List<Reporte> reportes = new Model.Finder(Long.class, Reporte.class).all();
        for (int i=0;i<reportes.size();i++){
            Reporte r=(Reporte)reportes.get(i);
            if(r.getTipoReporte().equals(Reporte.EMERGENCIA_MOVIBUS)&&r.getTipoAccidente()==Reporte.CHOQUE){
                choque++;
            }
            else if(r.getTipoReporte().equals(Reporte.EMERGENCIA_MOVIBUS)&&r.getTipoAccidente()==Reporte.DANIO){
                danio++;
            }
            else if(r.getTipoReporte().equals(Reporte.EMERGENCIA_MOVIBUS)&&r.getTipoAccidente()==Reporte.ROBO){
                robo++;
            }
        }
        if(choque>danio && choque>robo){
            return ok("Accidente");
        }
        else if(danio>choque && danio>robo){
            return ok("Dano tecnico");
        }
        else {
            return ok("Robo");
        }
    }
}