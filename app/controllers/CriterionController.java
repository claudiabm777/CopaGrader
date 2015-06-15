package controllers;

import Exceptions.*;
import com.fasterxml.jackson.databind.JsonNode;
import models.Admin;
import models.Claim;
import models.Criterion;
import models.Option;
import play.*;
import play.mvc.*;
import views.html.*;

import java.util.Date;

/**
 * Created by Asus on 04/06/2015.
 */

public class CriterionController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result editCriterion(){
        try{
            JsonNode j = Controller.request().body().asJson();
            Long oldId=j.findPath("oldId").asLong();
            String description=j.findPath("description").asText();
            Criterion t = Criterion.find.byId(oldId);
            if (t == null) {
                throw new CriterionException( ErrorMessage.NOT_CREATED);
            }
            t.setDescription(description);
            t.update();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteCriterion(){
        try{
            Long idCriterion = Controller.request().body().asJson().findPath("idCriterion").asLong();
            Criterion b=Criterion.find.byId(idCriterion);
            if(b==null){
                throw new CriterionException( ErrorMessage.NOT_CREATED);
            }
            b.delete();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    public Result addOptionToCriterion(){
        try{
            JsonNode j = Controller.request().body().asJson();
            Long idCriterion = Controller.request().body().asJson().findPath("idCriterion").asLong();
            Option option=Option.transformJson(j);
            Criterion criterion=Criterion.find.byId(idCriterion);
            if(criterion==null){
                throw new CriterionException( ErrorMessage.NOT_CREATED);
            }
            if(option==null){
                throw new OptionException( ErrorMessage.NOT_CREATED);
            }
            criterion.addOptionToCriterion(option);
            criterion.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    public Result addClaimToCriterion(){
        try {
            Long idCriterion = Controller.request().body().asJson().findPath("idCriterion").asLong();
            String idAdmin = Controller.request().body().asJson().findPath("idAdmin").asText();
            Long idOption = Controller.request().body().asJson().findPath("idOption").asLong();
            Criterion criterion=Criterion.find.byId(idCriterion);
            Admin admin=Admin.find.byId(idAdmin);
            Option option=Option.find.byId(idOption);
            if(criterion==null){
                throw new CriterionException(ErrorMessage.NOT_CREATED);
            }
            if(admin==null){
                throw new AdminException(idAdmin,ErrorMessage.NOT_CREATED);
            }
            if(option==null){
                throw new OptionException(ErrorMessage.NOT_CREATED);
            }
            Date creationDate=new Date(System.currentTimeMillis()- 3600 * 5000 );
            Claim claim=new Claim(creationDate,admin,option);
            if (claim==null){
                throw new ClaimException(ErrorMessage.NOT_CREATED);
            }
            criterion.setClaim(claim);
            criterion.save();

            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

}
