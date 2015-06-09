package controllers;

import Exceptions.ErrorMessage;
import Exceptions.GraderException;
import com.fasterxml.jackson.databind.JsonNode;
import models.Grader;
import play.*;
import play.mvc.*;
import views.html.*;
/**
 * Created by Asus on 04/06/2015.
 */
public class GraderController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result createAdmin(){
        try {
            JsonNode grader = Controller.request().body().asJson();
            Grader grader1 = Grader.transformJson(grader);
            Grader old=Grader.find.byId(grader1.getEmail());
            if(old!=null){
                throw new GraderException(old, ErrorMessage.ALREADY_CREATED);
            }
            grader1.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
}
