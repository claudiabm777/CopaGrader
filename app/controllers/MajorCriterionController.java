package controllers;

import Exceptions.ErrorMessage;
import Exceptions.MajorCriterionException;
import com.fasterxml.jackson.databind.JsonNode;
import models.MajorCriterion;
import play.*;
import play.mvc.*;
import views.html.*;
/**
 * Created by Asus on 04/06/2015.
 */
public class MajorCriterionController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result editMajorCriterion(){
        try{
            JsonNode j = Controller.request().body().asJson();
            Long oldId=j.findPath("oldId").asLong();
            String description=j.findPath("description").asText();
            MajorCriterion t = MajorCriterion.find.byId(oldId);
            if (t == null) {
                throw new MajorCriterionException( ErrorMessage.NOT_CREATED);
            }
            t.setDescription(description);
            t.update();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteMajorCriterion(){
        try{
            Long idMCriterion = Controller.request().body().asJson().findPath("idMCriterion").asLong();
            MajorCriterion b=MajorCriterion.find.byId(idMCriterion);
            if(b==null){
                throw new MajorCriterionException( ErrorMessage.NOT_CREATED);
            }
            b.delete();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
}
