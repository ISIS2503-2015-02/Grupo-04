package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Conductor;
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
        List<Usuario> usuarios = new Model.Finder(Long.class, Usuario.class).all();
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

    public Result solicitarMovibus(Long id) {
        Usuario usuario = (Usuario) new Model.Finder(Long.class, Usuario.class).byId(id);
        if(new Model.Finder(Long.class, Movibus.class).all().isEmpty()) {
            /*PedidoMovibus.PedidoMovibusPendiente pedidoMovibusPendiente= new PedidoMovibus.PedidoMovibusPendiente(usuario,request().body().asJson().findPath("direccionUsuario").asText());*/
        }
        else {
            PedidoMovibus pedidoMovibus = new PedidoMovibus();
            Movibus movibus = (Movibus) new Model.Finder(Long.class, Movibus.class).all().get(0);
            movibus.delete();
            movibus.reservarMovibus(pedidoMovibus);
            Conductor conductor = (Conductor) new Model.Finder(Long.class, Conductor.class).all().get(0);
            conductor.delete();
            pedidoMovibus.save();
            pedidoMovibus.setConductor(conductor);
            pedidoMovibus.setMovibus(movibus);
            pedidoMovibus.setUsuario(usuario);
            return ok(Json.toJson(pedidoMovibus));
        }

        PedidoMovibus.PedidoMovibusPendiente pedidoMovibusPendiente= (PedidoMovibus.PedidoMovibusPendiente) new Model.Finder(Long.class, PedidoMovibus.PedidoMovibusPendiente.class).all().remove(0);
        return ok(Json.toJson(null));
    }
}
