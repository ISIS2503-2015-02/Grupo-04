package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

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
        List<Movibus> movibu=new Model.Finder(Long.class, Movibus.class).all();
        List<Movibus> movibuses=new ArrayList<Movibus>();
        for(Movibus m:movibu){
            if(m.getEstado()== Movibus.DISPONIBLE){
                movibuses.add(m);
            }
        }

        List<Conductor> conductors=new Model.Finder(Long.class, Conductor.class).all();
        List<Conductor> conductores = new ArrayList<Conductor>();
        for(Conductor c:conductors){
            if(c.getEstado()== Conductor.DISPONIBLE){
                conductores.add(c);
            }
        }

        JsonNode j = request().body().asJson();
        if(movibuses.isEmpty()||conductores.isEmpty()) {
            PedidoMovibusPendiente pedidoMovibusPendiente = null;
            try {
                pedidoMovibusPendiente = new PedidoMovibusPendiente(usuario,new Date(),new SimpleDateFormat("yyyy-mm-dd").parse(j.findPath("fechaEjecucion").asText()),j.findPath("latitudUsuario").asDouble(),j.findPath("longitudUsuario").asDouble(),j.findPath("latitudDestino").asDouble(),j.findPath("longitudDestino").asDouble(),j.findPath("tiempoEstimado").asInt());
                pedidoMovibusPendiente.save();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(pedidoMovibusPendiente==null){
                return ok(Json.toJson(usuario));
            }
            else {
                return ok(Json.toJson(pedidoMovibusPendiente));
            }
        }
        else {
            PedidoMovibus pedidoMovibus = new PedidoMovibus();
            Movibus movibus=null;
            int menorDistancia=PedidoMovibus.DISTANCIA_MAXIMA;
            Iterator<Movibus> i = movibuses.iterator();
            while(i.hasNext()) {
                Movibus actual = i.next();
                if(actual.getLatitud().intValue()*100+actual.getLongitud().intValue()*100<menorDistancia) {
                    menorDistancia=actual.getLatitud().intValue()*100+actual.getLongitud().intValue()*100;
                    movibus=actual;
                }
            }
            movibus.reservarMovibus(pedidoMovibus);
            Conductor conductor = conductores.get(0);
            conductor.setEstado(Conductor.OCUPADO);
            pedidoMovibus.setConductor(conductor);
            pedidoMovibus.setMovibus(movibus);
            pedidoMovibus.setUsuario(usuario);
            pedidoMovibus.setFechaPedido(new Date());
            try {
                pedidoMovibus.setFechaEjecucion(new SimpleDateFormat("yyyy-mm-dd").parse(j.findPath("fechaEjecucion").asText()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pedidoMovibus.setLatitudUsuario(j.findPath("latitudUsuario").asDouble());
            pedidoMovibus.setLongitudUsuario(j.findPath("longitudUsuario").asDouble());
            pedidoMovibus.setLatitudDestino(j.findPath("latitudDestino").asDouble());
            pedidoMovibus.setLongitudDestino(j.findPath("longitudDestino").asDouble());
            pedidoMovibus.setTiempoEstimado(j.findPath("tiempoEstimado").asInt());
            pedidoMovibus.save();
            movibus.save();
            conductor.save();
            return ok(Json.toJson(pedidoMovibus));
        }
    }
}