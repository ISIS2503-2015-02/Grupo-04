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

import java.util.List;

/**
 * Created by sergio on 8/16/15.
 */
public class TranviaController extends Controller{

    /**
     * Metodo encargado de crear un tranvia
     * @return si se logra crear el tranvia
     */
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
    public Result reportarAccidente(String magnitud,Integer tipoAccidente)
    {
        JsonNode j = request().body().asJson();
        String descripcion = j.findPath("descripcion").asText();
        Long id= j.findPath("id").asLong();
        Reporte reporte= new Reporte(Reporte.EMERGENCIA_TRANVIA, descripcion,id,magnitud,tipoAccidente);
        reporte.save();
        return ok("La emergencia fue registrada");
    }

    public Result getAccidenteMasComun(){
        int estrellarse=0;
        int vararse=0;
        int robo=0;

        List<Reporte> reportes = new Model.Finder(Long.class, Reporte.class).all();

        for (int i=0;i<reportes.size();i++){
            Reporte r=(Reporte)reportes.get(i);
            if(r.getTipoAccidente()==Reporte.ESTRELLARSE){
                estrellarse++;
            }
            else if(r.getTipoAccidente()==Reporte.VARARSE){
                vararse++;
            }
            else if(r.getTipoAccidente()==Reporte.ROBO){
                robo++;
            }
        }
        if(estrellarse>vararse && estrellarse>robo){
            return ok("Estrellarse");
        }
        else if(vararse>estrellarse && vararse>robo){
            return ok("Vararse");
        }
        else {
            return ok("Robo");
        }
    }
}
