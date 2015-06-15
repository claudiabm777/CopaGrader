package controllers;

import Exceptions.BulletException;
import Exceptions.ErrorMessage;
import Exceptions.TaskException;
import com.fasterxml.jackson.databind.JsonNode;
import models.Bullet;
import models.Task;
import play.*;
import play.db.ebean.Transactional;
import play.mvc.*;
import views.html.*;
/**
 * Created by Asus on 04/06/2015.
 */
public class TaskController extends Controller {

    /**
     *
     * @return
     */
    @Transactional
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

    /**
     *
     * @return
     */
    @Transactional
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

    /**
     *
     * @return
     */
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result addBulletToTask(){
        try{
            String description = Controller.request().body().asJson().findPath("description").asText();
            Long idTask = Controller.request().body().asJson().findPath("idTask").asLong();
            Bullet bullet=new Bullet(description);
            Task task=Task.find.byId(idTask);
            if(task==null){
                throw new TaskException( ErrorMessage.NOT_CREATED);
            }
            if(bullet==null){
                throw new BulletException( ErrorMessage.NOT_CREATED);
            }
            task.addBulletToTask(bullet);
            task.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
}
