package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import org.owasp.StringEnvelope;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.*;
import java.security.MessageDigest;

import static play.mvc.Controller.request;
import static play.mvc.Results.*;
import org.json.*;
public class UsuarioController {
    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = request().body().asJson();
        Usuario usuario = Json.fromJson(j, Usuario.class);
        usuario.save();
        return ok(Json.toJson(usuario));
    }


//        Iterator<JsonNode> it = j.elements();
//        int i=0;
//        String res[]=new String[2];
//        while(it.hasNext()){
//            res[i]= it.next().asText();
//            i++;
//        }
//        String hashEntry = new JSONObject(res[0]).getString("hashContent");

        //hashEntry = (new JSONObject(j.findPath("envio1").asText())).getString("hashContent");


//        JSONObject body = new JSONObject(res[1]);
        //JSONObject body = new JSONObject(j.findPath("envio2").asText());
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(j.findPath("envio2").asText().getBytes());
//            byte hola [] = md.digest();
//            StringBuffer hexString = new StringBuffer();
//            for (int k = 0; k < hola.length; k++) {
//                if ((0xff & hola[k]) < 0x10) {
//                    hexString.append("0" + Integer.toHexString((0xFF & hola[k])));
//                }
//                else {
//                    hexString.append(Integer.toHexString(0xFF & hola[k]));
//               }
//            }
//            if(hashEntry.equals(hexString.toString()))
//            {
//                Usuario usuario = Json.fromJson(j, Usuario.class);
//                usuario.save();
//                return ok(Json.toJson(usuario));


//            }
//        }
//        catch(Exception e){return internalServerError(e.getMessage());}
//        return internalServerError("holySh");

 //   }
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

  /**
     * Devuelve un usuario dado el correo y la clave.
     * @param pCedula clave del usuario.
     * @param pCorreo correo del usuario.
     * @return JSon con loa informacion del usuario.
     */
    public boolean login(String pCorreo, int pCedula){
        List<Usuario> users=new Model.Finder(Long.class, Usuario.class).all();
        Usuario usuario = null;
        for(Usuario u:users){
            if(u.getCorreo().equals(pCorreo) && u.getCedula()== pCedula)
            {
                usuario = u;
            }
        }
        return usuario == null;
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
                LOGGER.info(e);
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
                LOGGER.info(e);
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
