package controllers;

import Exceptions.ActivityException;
import Exceptions.ErrorMessage;
import Exceptions.GraderException;
import Exceptions.TeamException;
import models.Activity;
import models.Grader;
import models.Team;
import play.*;
import play.mvc.*;
import views.html.*;
/**
 * Created by Asus on 04/06/2015.
 */
public class TeamController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result addTeam(){
        try {
            String idGrader = Controller.request().body().asJson().findPath("idGrader").asText();
            Long idActivity = Controller.request().body().asJson().findPath("idActivity").asLong();
            String name = Controller.request().body().asJson().findPath("name").asText();
            Team team=new Team(name);
            if(team==null){
                throw new TeamException(ErrorMessage.NOT_CREATED);
            }
            Grader grader=Grader.find.byId(idGrader);
            Activity activity=Activity.find.byId(idActivity);
            if(grader==null){
                throw new GraderException(idGrader,ErrorMessage.NOT_CREATED);
            }
            if(activity==null){
                throw new ActivityException(ErrorMessage.NOT_CREATED);
            }
            activity.addTeam(team);
            grader.addTeam(team);
            activity.update();
            grader.update();

            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteTeam(){
        try {
            Long idTeam = Controller.request().body().asJson().findPath("idTeam").asLong();
            Team team=Team.find.byId(idTeam);
            if(team==null){
                throw new TeamException(ErrorMessage.NOT_CREATED);
            }
            team.delete();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result editTeam(){
        try {
            String name = Controller.request().body().asJson().findPath("name").asText();
            Long idTeam = Controller.request().body().asJson().findPath("idTeam").asLong();
            Team team=Team.find.byId(idTeam);
            if(team==null){
                throw new TeamException(ErrorMessage.NOT_CREATED);
            }
            team.setName(name);
            team.update();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
}
