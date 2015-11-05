package controllers;

import org.owasp.StringEnvelope;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Conductor;
import models.Movibus;
import models.PedidoMovibus;
import models.PedidoMovibusPendiente;
import play.libs.Json;
import play.mvc.Result;
import org.json.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.*;

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

    public Result reportarPedidoTerminado() {
        try {
            JsonNode j = request().body().asJson();
            String coded = j.findPath("envelop").asText();
            StringEnvelope env = new StringEnvelope();
            JSONObject jsonObj = new JSONObject(env.unwrap(coded, "aa09cee77e1d606d5ab06500ac95729c"));
            Long id = new Long(jsonObj.getInt("id"));
            int tiempoReal = jsonObj.getInt("tiempo");

            PedidoMovibus pedidoMovibus = (PedidoMovibus) new Model.Finder(Long.class, PedidoMovibus.class).byId(id);
            pedidoMovibus.setTiempoReal(tiempoReal);
            List<PedidoMovibusPendiente> pedidosMovibus = new Model.Finder(Long.class, PedidoMovibusPendiente.class).all();
            if (pedidosMovibus.isEmpty()) {
                Conductor conductor = pedidoMovibus.getConductor();
                conductor.setEstado(Conductor.DISPONIBLE);
                conductor.setDesempenio(tiempoReal / pedidoMovibus.getTiempoEstimado());
                conductor.save();
                Movibus movibus = pedidoMovibus.getMovibus();
                movibus.setEstado(Movibus.DISPONIBLE);
                movibus.setKilometraje((int) (Math.abs(pedidoMovibus.getLatitudUsuario() - pedidoMovibus.getLatitudDestino()) + Math.abs(pedidoMovibus.getLongitudDestino() - pedidoMovibus.getLongitudUsuario())));
                movibus.save();
                if (pedidoMovibus == null)
                    return ok(Json.toJson(Json.newObject()));
                else {
                    return ok(Json.toJson(pedidoMovibus));
                }
            } else {
                PedidoMovibusPendiente pendiente = pedidosMovibus.get(0);
                pedidoMovibus = null;
                boolean encontro = false;
                while (!encontro && pendiente != null) {
                    if (pendiente.getFechaEjecucion().after(new Date())) {
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
                        encontro = true;
                    }
                    pendiente.delete();
                    pendiente = (PedidoMovibusPendiente) new Model.Finder(Long.class, PedidoMovibusPendiente.class).all().get(0);
                }
                if (pedidoMovibus == null)
                    return ok(Json.toJson(Json.newObject()));
                else {
                    return ok(Json.toJson(pedidoMovibus));
                }
            }
        }catch(Exception e){
            LOGGER.info(e);
            return internalServerError(e.getMessage());
        }
    }

    public Result getPedidoByMovibus() {
        JsonNode j = request().body().asJson();
        String coded=j.findPath("envelop").asText();
        JSONObject fin = desEnvolver(coded);
        Long id = null;
        try{
            id = fin.getLong("id");
        } catch (JSONException e) {
            LOGGER.info(e);
        }
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
    public JSONObject desEnvolver(String crypted)
    {
        JSONObject plaintext = null;
        StringEnvelope env = new StringEnvelope();
        try {
            plaintext = new JSONObject(env.unwrap(crypted, "aa09cee77e1d606d5ab06500ac95729c"));
        }
        catch(IllegalArgumentException e){
            System.out.println("Decryption failed: " + e);
            LOGGER.info(e);
        } catch (NoSuchPaddingException e) {
            System.out.println("Decryption failed: " + e);
            LOGGER.info(e);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Decryption failed: " + e);
            LOGGER.info(e);
        } catch (InvalidAlgorithmParameterException e) {
            System.out.println("Decryption failed: " + e);
            LOGGER.info(e);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Decryption failed: " + e);
            LOGGER.info(e);
        } catch (IllegalBlockSizeException e) {
            System.out.println("Decryption failed: " + e);
            LOGGER.info(e);
        } catch (BadPaddingException e) {
            System.out.println("Decryption failed: " + e);
            LOGGER.info(e);
        } catch (InvalidKeyException e) {
            System.out.println("Decryption failed: " + e);
            LOGGER.info(e);
        } catch (JSONException e) {
            LOGGER.info(e);
        }
        return plaintext;
    }
}
