package controllers;

import Exceptions.ClaimException;
import Exceptions.ErrorMessage;
import models.Claim;
import play.*;
import play.mvc.*;
import views.html.*;

/**
 * Created by Asus on 04/06/2015.
 */
public class ClaimController extends Controller {


    public Result deleteClaim(){
        try {
            Long idClaim = Controller.request().body().asJson().findPath("idClaim").asLong();
            Claim claim=Claim.find.byId(idClaim);
            if(claim==null){
                throw new ClaimException(ErrorMessage.NOT_CREATED);
            }
            claim.delete();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
}
