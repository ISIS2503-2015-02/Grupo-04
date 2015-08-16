package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Tranvia;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by sergio on 8/16/15.
 */
public class TranviaController extends Controller{

    /**
     * Metodo encargado de crear un tranvia
     * @return si se logra crear el tranvia
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = request().body().asJson();
        Tranvia tranvia = Json.fromJson(j, Tranvia.class);
        tranvia.save();
        return ok(Json.toJson(tranvia));
    }



}
