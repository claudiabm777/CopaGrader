package controllers;

import Exceptions.ErrorMessage;
import Exceptions.GraderException;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import models.Grader;
import play.*;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import java.util.List;

/**
 * Created by Asus on 04/06/2015.
 */
public class GraderController extends Controller {

    /**
     *
     * @return
     */
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result createGrader(){
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

    /**
     *
     * @return
     */
    @Transactional
    public Result getGraders(){
        try {
            List<Grader> grader = Grader.find.all();
            return ok(Json.toJson(grader));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result getGraderId(){
        try {
            String idGrader = Controller.request().body().asJson().findPath("idGrader").asText();
            Grader grader=Grader.find.byId(idGrader);
            if (grader==null){
                throw new GraderException(grader,ErrorMessage.NOT_CREATED);
            }
            return ok(Json.toJson(grader));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result editGrader(){
        try {
            JsonNode newGrader = Controller.request().body().asJson();
            String oldId=newGrader.findPath("oldId").asText();
            Grader grader = Grader.transformJson(newGrader);
            Grader grader1 = Grader.find.byId(oldId);
            if (grader1 == null) {
                throw new GraderException(grader1.getEmail(), ErrorMessage.NOT_CREATED);
            }
            if((!grader.getEmail().equals(oldId))&&(Grader.find.byId(grader.getEmail())!=null)){
                throw new GraderException(GraderException.CODE_REPEATED);
            }
                grader1.setIdentityCard(grader.getIdentityCard());
                grader1.setLastNames(grader.getLastNames());
                grader1.setNames(grader.getNames());
                grader1.setPhone(grader.getPhone());
                grader1.setCargo(grader.getCargo());
                grader1.update();
                Ebean.createSqlUpdate("update grader set email = :email where email = :id")
                        .setParameter("email", grader.getEmail())
                        .setParameter("id", oldId)
                        .execute();

            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteGrader(){
        try {
            String idGrader = Controller.request().body().asJson().findPath("idGrader").asText();
            Grader grader=Grader.find.byId(idGrader);
            if(grader==null){
                throw new GraderException(idGrader, ErrorMessage.NOT_CREATED);
            }
            grader.delete();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }


}
