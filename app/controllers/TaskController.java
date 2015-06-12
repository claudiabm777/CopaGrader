package controllers;

import Exceptions.ErrorMessage;
import Exceptions.TaskException;
import com.fasterxml.jackson.databind.JsonNode;
import models.Task;
import play.*;
import play.mvc.*;
import views.html.*;
/**
 * Created by Asus on 04/06/2015.
 */
public class TaskController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result editTask(){
        try{
            JsonNode j = Controller.request().body().asJson();
            Long oldId=j.findPath("oldId").asLong();
            String name=j.findPath("name").asText();
            Task task = Task.find.byId(oldId);
            if (task == null) {
                throw new TaskException( ErrorMessage.NOT_CREATED);
            }
            task.setName(name);
            task.update();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteTask(){
        try{
            Long idTask = Controller.request().body().asJson().findPath("idTask").asLong();
            Task t=Task.find.byId(idTask);
            if(t==null){
                throw new TaskException( ErrorMessage.NOT_CREATED);
            }
            t.delete();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
}
