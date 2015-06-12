package controllers;

/**
 * Created by Asus on 04/06/2015.
 */
import Exceptions.BulletException;
import Exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.JsonNode;
import models.Bullet;
import play.*;
import play.mvc.*;
import views.html.*;

public class BulletController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result editBullet(){
        try{
            JsonNode j = Controller.request().body().asJson();
            Long oldId=j.findPath("oldId").asLong();
            String description=j.findPath("description").asText();
            Bullet t = Bullet.find.byId(oldId);
            if (t == null) {
                throw new BulletException( ErrorMessage.NOT_CREATED);
            }
            t.setDescription(description);
            t.update();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteBullet(){
        try{
            Long idBullet = Controller.request().body().asJson().findPath("idBullet").asLong();
            Bullet b=Bullet.find.byId(idBullet);
            if(b==null){
                throw new BulletException( ErrorMessage.NOT_CREATED);
            }
            b.delete();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
}
