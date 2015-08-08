package controllers;

import Exceptions.*;
import models.*;
import play.*;
import play.db.ebean.Transactional;
import play.mvc.*;
import views.html.*;

import java.util.List;

/**
 * Created by Asus on 04/06/2015.
 */
public class TeamController extends Controller {

    @Transactional
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
            List<Task>tasks=activity.getTasks();
            if(tasks.size()==0){
                throw new Exception(ErrorMessage.NOT_TASK_CREATED);
            }
            team.setTasks(tasks);
            activity.addTeam(team);
            grader.addTeam(team);
            activity.update();
            grader.update();

            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    @Transactional
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

    @Transactional
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

    @Transactional
    public Result addTaskToTeam(String name, long idTeam){
        try{

            Task task=new Task(name);
            Team team=Team.find.byId(idTeam);
            if(team==null){
                throw new TeamException( ErrorMessage.NOT_CREATED);
            }
            if(task==null){
                throw new TaskException( ErrorMessage.NOT_CREATED);
            }
            team.addTaskToActivity(task);
            team.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    public Result teamStudentAdd(){
        try {
            Long idTeam = Controller.request().body().asJson().findPath("idTeam").asLong();
            Long idStudent = Controller.request().body().asJson().findPath("idStudent").asLong();
            Team team = Team.find.byId(idTeam);
            Student student = Student.find.byId(idStudent);
            if (team == null) {
                throw new TeamException(ErrorMessage.NOT_CREATED);
            }
            if (student == null) {
                throw new StudentException(ErrorMessage.NOT_CREATED);
            }
            team.getStudents().add(student);
            team.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
}
