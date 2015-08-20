package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Conductor;
import models.Movibus;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

/**
 * Created by cfagu on 16-Aug-15.
 */
public class ConductorController {
    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = request().body().asJson();
        Conductor conductor = Json.fromJson(j, Conductor.class);
        conductor.save();
        return ok(Json.toJson(conductor));
    }

    public Result read() {
        List<Conductor> conductores = new Model.Finder(String.class, Conductor.class).all();
        return ok(Json.toJson(conductores));
    }

    public Result get(Long id) {
        Conductor conductor = (Conductor) new Model.Finder(Long.class, Conductor.class).byId(id);
        ObjectNode result = Json.newObject();
        if (conductor == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(conductor));
        }
    }

    /**
     * Obtiene el desempenio del conductor.
     * @param id, id del conductor.
     * @return el desempenio del conductor como un porcentaje estimado entre sus tiempos reales y tiempos esperados sobre cantidad de viajes.
     */
    public Result getDesempenio(Long id){
        Conductor conductor = (Conductor) new Model.Finder(Long.class,  Conductor.class).byId(id);
        return ok(Json.toJson(conductor.getDesempenio()));
    }

    /**
     * Obtiene el conductor con el mejor desempenio.
     */
    public Result getMejorDesempenio(){
        List<Conductor> conductores = new Model.Finder(String.class, Conductor.class).all();
        Conductor conductor = null;
        for(Conductor c : conductores){
            conductor=(c.getDesempenio()>conductor.getDesempenio())?c:conductor;
        }
        return ok(Json.toJson(conductor));
    }
}
