package controllers;

import models.Tranvia;
import play.data.Form;
import play.db.ebean.Transactional;
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
    @Transactional
    public Result create(){
        Tranvia tranvia= Form.form(Tranvia.class).bindFromRequest().get();
        tranvia.save();

        return ok("Se ha creado el nuevo tranvia");
    }



}
