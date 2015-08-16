package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.PedidoMovibus;
import models.Usuario;
import play.libs.Json;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Results.ok;

/**
 * Created by cfagu on 16-Aug-15.
 */
public class PedidoMovibusController {

    public Result read() {
        List<PedidoMovibus> pedidosMovibus = new Model.Finder(String.class, PedidoMovibus.class).all();
        return ok(Json.toJson(pedidosMovibus));
    }

    public Result get(Long id) {
        PedidoMovibus pedidoMovibus = (PedidoMovibus) new Model.Finder(Long.class, PedidoMovibus.class).byId(id);
        ObjectNode result = Json.newObject();
        if(pedidoMovibus == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(pedidoMovibus));
        }
    }


}
