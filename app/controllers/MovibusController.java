package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Movibus;
import models.Reporte;
import org.owasp.StringEnvelope;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import models.Logger;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;
import org.json.*;

public class MovibusController {
    
    public static final String DECRYPTION = "Decryption failed: ";

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

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();
        Movibus movibusViejo = (Movibus) new Model.Finder(Long.class, Movibus.class).byId(id);
        ObjectNode result = Json.newObject();
        if(movibusViejo == null)
            return ok(Json.toJson(result));
        else {
            Movibus movibusNuevo = Movibus.bind(j);
            movibusViejo.update(movibusNuevo);
            movibusViejo.save();
            return ok(Json.toJson(movibusViejo));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result posicion() {
         Long id = null;
        Long lat = null;
        Long log = null;


        JsonNode j = Controller.request().body().asJson();
        String coded=j.findPath("envelop").asText();
        JSONObject decoded = desEnvolver(coded);
        try {
             id = decoded.getLong("id");
             lat = decoded.getLong("latitud");
             log = decoded.getLong("longitud");
        }
        catch(Exception e){
            Logger.info(e);
        }
        Double lat2 = new Double(lat);
        Double log2 = new Double(log);
        Movibus movibusViejo = (Movibus) new Model.Finder(Long.class, Movibus.class).byId(id);
        ObjectNode result = Json.newObject();
        if(movibusViejo == null)
            return ok(Json.toJson(result));
        else {
            Movibus movibusNuevo = movibusViejo;
            movibusNuevo.setLatitud(lat2);
            movibusNuevo.setLongitud(log2);
            movibusViejo.update(movibusNuevo);
            movibusViejo.save();
            return ok(Json.toJson(movibusViejo));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result reportarAccidente() {
        JsonNode j = request().body().asJson();
        String descripcion = j.findPath("descripcion").asText();
        String magnitud=j.findPath("magnitud").asText();
        int tipoAccidente=j.findParent("tipoAccidente").asInt();
        Reporte reporte= new Reporte(Reporte.EMERGENCIA_MOVIBUS, descripcion,magnitud,tipoAccidente);
        reporte.save();
        return ok(Json.toJson(reporte));
    }

    public Result getAccidenteMasComun() {
        int choque=0;
        int danio=0;
        int robo=0;
        List<Reporte> reportes = new Model.Finder(Long.class, Reporte.class).all();
        for (int i=0;i<reportes.size();i++){
            Reporte r=(Reporte)reportes.get(i);
            if(r.getTipoReporte().equals(Reporte.EMERGENCIA_MOVIBUS)&&r.getTipoAccidente()==Reporte.CHOQUE){
                choque++;
            }
            else if(r.getTipoReporte().equals(Reporte.EMERGENCIA_MOVIBUS)&&r.getTipoAccidente()==Reporte.DANIO){
                danio++;
            }
            else if(r.getTipoReporte().equals(Reporte.EMERGENCIA_MOVIBUS)&&r.getTipoAccidente()==Reporte.ROBO){
                robo++;
            }
        }
        if(choque>danio && choque>robo){
            return ok("Accidente");
        }
        else if(danio>choque && danio>robo){
            return ok("Dano tecnico");
        }
        else {
            return ok("Robo");
        }
    }

    public JSONObject desEnvolver(String crypted)
    {
        JSONObject plaintext = null;
        StringEnvelope env = new StringEnvelope();
        try {
            plaintext = new JSONObject(env.unwrap(crypted, "aa09cee77e1d606d5ab06500ac95729c"));
        }
        catch(IllegalArgumentException e){
            Logger.info(e);
        } catch (NoSuchPaddingException e) {
            Logger.info(e);
        } catch (UnsupportedEncodingException e) {
            Logger.info(e);
        } catch (InvalidAlgorithmParameterException e) {
            Logger.info(e);
        } catch (NoSuchAlgorithmException e) {
            Logger.info(e);
        } catch (IllegalBlockSizeException e) {
            Logger.info(e);
        } catch (BadPaddingException e) {
            Logger.info(e);
        } catch (InvalidKeyException e) {
            Logger.info(e);
        }
     catch (Exception e) {
         Logger.info(e);
    }
        return plaintext;
    }
}
