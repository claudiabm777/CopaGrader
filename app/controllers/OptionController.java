package controllers;

import Exceptions.ErrorMessage;
import Exceptions.OptionException;
import com.fasterxml.jackson.databind.JsonNode;
import models.Option;
import play.*;
import play.mvc.*;
import views.html.*;
/**
 * Created by Asus on 04/06/2015.
 */
public class OptionController extends Controller {

    /**
     *
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result editOption(){
        try{
            JsonNode j = Controller.request().body().asJson();
            Long oldId=j.findPath("oldId").asLong();
            String description=j.findPath("description").asText();
            Double score=j.findPath("score").asDouble();
            Boolean isPenalty=j.findPath("isPenalty").asBoolean();
            Option t = Option.find.byId(oldId);
            if (t == null) {
                throw new OptionException( ErrorMessage.NOT_CREATED);
            }
            t.setDescription(description);
            t.setIsPenalty(isPenalty);
            t.setScore(score);
            t.update();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteOption(){
        try{
            Long idOption = Controller.request().body().asJson().findPath("idOption").asLong();
            Option b=Option.find.byId(idOption);
            if(b==null){
                throw new OptionException( ErrorMessage.NOT_CREATED);
            }
            b.delete();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
}
