package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Reporte;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by sergio on 8/16/15.
 */
public class ReporteController extends Controller {

    public Result getAll(){
        List<Reporte> reportes = new Model.Finder(String.class, Reporte.class).all();
        return ok(Json.toJson(reportes));
    }

    public Result get(Long id){
        Reporte reporte = (Reporte) new Model.Finder(Long.class, Reporte.class).byId(id);
        ObjectNode result = Json.newObject();
        if(reporte == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(reporte));
        }
    }
}
