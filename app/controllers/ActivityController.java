package controllers;

import Exceptions.ActivityException;
import Exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.JsonNode;
import models.Activity;
import play.*;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import java.util.List;

/**
 * Created by Asus on 04/06/2015.
 */
public class ActivityController extends Controller {
    /**
     *
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteActivity(){
        try {
            Long idActivity = Controller.request().body().asJson().findPath("idActivity").asLong();
            Activity activity = Activity.find.byId(idActivity);
            if (activity == null) {
                throw new ActivityException(ErrorMessage.NOT_CREATED);
            }
            activity.delete();
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
    public Result editActivity(){
        try {
            JsonNode newActivity = Controller.request().body().asJson();
            Long oldId=newActivity.findPath("oldId").asLong();
            //PENDIENTE EDITAR ADMINISTRADORES ENCARGADOS.
            Activity activity = Activity.transformJson(newActivity);
            Activity activity1 = Activity.find.byId(oldId);
            if (activity1 == null) {
                throw new ActivityException(ErrorMessage.NOT_CREATED);
            }
            activity1.setDeadline(activity.getDeadline());
            activity1.setName(activity.getName());
            activity1.update();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    public Result getActivities(){
        try{
            List<Activity>activities=Activity.find.all();
            return ok(Json.toJson(activities));
        }catch(Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result getActivityId(){
        try{
            Long idActivity = Controller.request().body().asJson().findPath("idActivity").asLong();
            Activity activity=Activity.find.byId(idActivity);
            if (activity==null){
                throw new ActivityException(ErrorMessage.NOT_CREATED);
            }
            return ok(Json.toJson(activity));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
}
