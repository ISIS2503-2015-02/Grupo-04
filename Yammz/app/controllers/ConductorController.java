package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Conductor;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

public class ConductorController {

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = request().body().asJson();
        Conductor conductor = Json.fromJson(j, Conductor.class);
        conductor.save();
        return ok(Json.toJson(conductor));
    }

    public Result read() {
        List<Conductor> conductores = new Model.Finder(Long.class, Conductor.class).all();
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

    public Result getDesempenio(Long id){
        Conductor conductor = (Conductor) new Model.Finder(Long.class, Conductor.class).byId(id);
        return ok(Json.toJson(conductor.getDesempenio()));
    }

    public Result getMejorDesempenio(){
        List<Conductor> conductores = new Model.Finder(Long.class, Conductor.class).all();
        Conductor conductor = null;
        double mejorDesempenio=-1;
        for(Conductor c : conductores){
            if(c.getDesempenio()>mejorDesempenio){
                conductor=c;
                mejorDesempenio=c.getDesempenio();
            }
        }
        return ok(Json.toJson(conductor));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();
        Conductor conductorViejo = (Conductor) new Model.Finder(Long.class, Conductor.class).byId(id);
        ObjectNode result = Json.newObject();
        if(conductorViejo == null)
            return ok(Json.toJson(result));
        else {
            Conductor conductorNuevo = Conductor.bind(j);
            conductorViejo.update(conductorNuevo);
            conductorViejo.save();
            return ok(Json.toJson(conductorViejo));
        }
    }
}
