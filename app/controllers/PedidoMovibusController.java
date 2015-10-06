package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Conductor;
import models.Movibus;
import models.PedidoMovibus;
import models.PedidoMovibusPendiente;
import play.libs.Json;
import play.mvc.Result;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

public class PedidoMovibusController {

    public Result read() {
        List<PedidoMovibus> pedidosMovibus = new Model.Finder(Long.class, PedidoMovibus.class).all();
        return ok(Json.toJson(pedidosMovibus));
    }

    public Result get(Long id) {
        PedidoMovibus pedidoMovibus = (PedidoMovibus) new Model.Finder(Long.class, PedidoMovibus.class).byId(id);
        ObjectNode result = Json.newObject();
        if (pedidoMovibus == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(pedidoMovibus));
        }
    }

    public Result readPendientes() {
        List<PedidoMovibusPendiente> pedidosMovibus = new Model.Finder(Long.class, PedidoMovibusPendiente.class).all();
        return ok(Json.toJson(pedidosMovibus));
    }

    public Result getPendiente(Long id) {
        PedidoMovibusPendiente pedidoMovibus = (PedidoMovibusPendiente) new Model.Finder(Long.class, PedidoMovibusPendiente.class).byId(id);
        ObjectNode result = Json.newObject();
        if (pedidoMovibus == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(pedidoMovibus));
        }
    }

    public Result reportarPedidoTerminado(Long id, int tmpr) {
        JsonNode j = request().body().asJson();
        int tiempoReal=j.findPath("tiempoReal").asInt();
        PedidoMovibus pedidoMovibus = (PedidoMovibus) new Model.Finder(Long.class, PedidoMovibus.class).byId(id);
        pedidoMovibus.setTiempoReal(tiempoReal);
        List<PedidoMovibusPendiente> pedidosMovibus = new Model.Finder(Long.class, PedidoMovibusPendiente.class).all();
        if(pedidosMovibus.isEmpty()) {
            Conductor conductor=pedidoMovibus.getConductor();
            conductor.setEstado(Conductor.DISPONIBLE);
            conductor.setDesempenio(tiempoReal / pedidoMovibus.getTiempoEstimado());
            conductor.save();
            Movibus movibus=pedidoMovibus.getMovibus();
            movibus.setEstado(Movibus.DISPONIBLE);
            movibus.setKilometraje((int) (Math.abs(pedidoMovibus.getLatitudUsuario() - pedidoMovibus.getLatitudDestino()) + Math.abs(pedidoMovibus.getLongitudDestino() - pedidoMovibus.getLongitudUsuario())));
            movibus.save();
            if(pedidoMovibus == null)
                return ok(Json.toJson(Json.newObject()));
            else {
                return ok(Json.toJson(pedidoMovibus));
            }
        }
        else {
            PedidoMovibusPendiente pendiente=pedidosMovibus.get(0);
            pedidoMovibus = null;
            boolean encontro=false;
            while(!encontro&&pendiente!=null) {
                if(pendiente.getFechaEjecucion().after(new Date())) {
                    pedidoMovibus = new PedidoMovibus();
                    Conductor conductor = pedidoMovibus.getConductor();
                    conductor.setDesempenio(tiempoReal / pedidoMovibus.getTiempoEstimado());
                    conductor.save();
                    Movibus movibus = pedidoMovibus.getMovibus();
                    movibus.setKilometraje((int) (Math.abs(pedidoMovibus.getLatitudUsuario() - pedidoMovibus.getLatitudDestino()) + Math.abs(pedidoMovibus.getLongitudDestino() - pedidoMovibus.getLongitudUsuario())));
                    movibus.save();
                    pedidoMovibus.setFechaEjecucion(pendiente.getFechaEjecucion());
                    pedidoMovibus.setFechaPedido(pendiente.getFechaPedido());
                    pedidoMovibus.setConductor(conductor);
                    pedidoMovibus.setLatitudDestino(pendiente.getLatitudDestino());
                    pedidoMovibus.setLatitudUsuario(pendiente.getLatitudUsuario());
                    pedidoMovibus.setLongitudDestino(pendiente.getLongitudDestino());
                    pedidoMovibus.setLongitudUsuario(pendiente.getLongitudUsuario());
                    pedidoMovibus.setMovibus(movibus);
                    pedidoMovibus.setUsuario(pendiente.getUsuario());
                    encontro=true;
                }
                pendiente.delete();
                pendiente=(PedidoMovibusPendiente)new Model.Finder(Long.class, PedidoMovibusPendiente.class).all().get(0);
            }
            if (pedidoMovibus == null)
                return ok(Json.toJson(Json.newObject()));
            else {
                return ok(Json.toJson(pedidoMovibus));
            }
        }
    }

    public Result getPedidoByMovibus(Long id) {
        List<PedidoMovibus> pedidosMovibus = new Model.Finder(Long.class, PedidoMovibus.class).all();
        Iterator<PedidoMovibus> pedidoMovibusIterator = pedidosMovibus.iterator();
        while(pedidoMovibusIterator.hasNext()) {
            PedidoMovibus pedidoMovibus=pedidoMovibusIterator.next();
            if(pedidoMovibus.getMovibus().getId().equals(id)) {
                return ok(Json.toJson(pedidoMovibus));
            }
        }
        return ok(Json.toJson(Json.newObject()));
    }
}
