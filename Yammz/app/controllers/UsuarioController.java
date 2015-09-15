package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.util.Iterator;
import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
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

    /**
     * Devuelve un usuario dada la cedula del mismo.
     * @param ccu cedula del usuario.
     * @return JSon con loa informacion del usuario.
     */
    public Result getByCC(Long ccu){
        List<Usuario> users=new Model.Finder(Long.class, Usuario.class).all();
        Usuario usuario = null;
        for(Usuario u:users){
            if(u.getCedula()==ccu){
                usuario = u;
            }
        }
        if(usuario == null){
            return ok(Json.toJson("error:Usuario no registrado."));
        }else {
            return ok(Json.toJson(usuario));
        }
    }

    public Result solicitarMovibus(Long id) {
        Usuario usuario = (Usuario) new Model.Finder(Long.class, Usuario.class).byId(id);
        if(new Model.Finder(Long.class, Movibus.class).all().isEmpty()) {
            PedidoMovibusPendiente pedidoMovibusPendiente=new PedidoMovibusPendiente(usuario,request().body().asJson().findPath("direccionUsuario").asText(),request().body().asJson().findPath("direccionDestino").asText());
            pedidoMovibusPendiente.save();
            return badRequest();
        }
        else {
            PedidoMovibus pedidoMovibus = new PedidoMovibus();
            List movibuses = Movibus.find.where().like("estado",""+Movibus.DISPONIBLE).findList();
            Movibus movibus=null;
            int menorDistancia=PedidoMovibus.DISTANCIA_MAXIMA;
            Iterator i = movibuses.iterator();
            while (i.hasNext()) {
                Movibus movibusT=(Movibus)i.next();
                Direccion d1=new Direccion(movibusT.getPosicion());
                Direccion d2=new Direccion(request().body().asJson().findPath("direccionUsuario").asText());
                if(d1.darDistancia(d2)<menorDistancia) {
                    movibus=movibusT;
                }
            }
            movibus.reservarMovibus(pedidoMovibus);
            Conductor conductor = (Conductor) new Model.Finder(Long.class, Conductor.class).all().get(0);
            pedidoMovibus.setConductor(conductor);
            pedidoMovibus.setMovibus(movibus);
            pedidoMovibus.setUsuario(usuario);
            pedidoMovibus.setDireccionDestino(request().body().asJson().findPath("direccionDestino").asText());
            pedidoMovibus.setDireccionUsuario(request().body().asJson().findPath("direccionUsuario").asText());
            pedidoMovibus.save();
            return ok(Json.toJson(pedidoMovibus));
        }
    }
}
