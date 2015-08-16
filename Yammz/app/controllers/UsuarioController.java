package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Movibus;
import models.PedidoMovibus;
import models.Usuario;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

/**
 * Created by cfagu on 16-Aug-15.
 */
public class UsuarioController {
    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = request().body().asJson();
        Usuario usuario = Json.fromJson(j, Usuario.class);
        usuario.save();
        return ok(Json.toJson(usuario));
    }

    public Result read() {
        List<Usuario> usuarios = new Model.Finder(String.class, Usuario.class).all();
        return ok(Json.toJson(usuarios));
    }

    public Result get(Long id) {
        Usuario usuario = (Usuario) new Model.Finder(Long.class, Usuario.class).byId(id);
        ObjectNode result = Json.newObject();
        if(usuario == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(usuario));
        }
    }

    public Result solicitarMovibus() {
        JsonNode j = request().body().asJson();
        PedidoMovibus pedidoMovibus = Json.fromJson(j, PedidoMovibus.class);
        Movibus movibus = (Movibus) new Model.Finder(String.class, Movibus.class).all().remove(0);
        movibus.reservarMovibus(pedidoMovibus);
        return ok(Json.toJson(movibus));
    }
}
