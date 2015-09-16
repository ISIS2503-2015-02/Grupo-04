package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Movibus;
import models.PedidoMovibus;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

/**
 * Created by cfagu on 16-Aug-15.
 */
public class MovibusController {

    /**
     * Metodo encargado de crear un movibus
     * @return si se logra crear el movibus
     */
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

    public Result reportarPosicion(Long idMovibus, String posicion){
        Movibus movibus = (Movibus)new Model.Finder(Long.class, Movibus.class).byId(idMovibus);
        movibus.setPosicion(posicion);
        movibus.save();
        return ok(Json.toJson(movibus));
    }
}
