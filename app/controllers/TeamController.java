package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
/**
 * Created by Asus on 04/06/2015.
 */
public class TeamController extends Controller {

    public Result addTeam(){
        try {
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    public Result deleteTeam(){
        try {
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    public Result editTeam(){
        try {
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
}
