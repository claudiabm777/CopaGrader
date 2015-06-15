package controllers;

/**
 * Created by Asus on 04/06/2015.
 */
import Exceptions.BulletException;
import Exceptions.ErrorMessage;
import Exceptions.MajorCriterionException;
import com.fasterxml.jackson.databind.JsonNode;
import models.Bullet;
import models.MajorCriterion;
import play.*;
import play.db.ebean.Transactional;
import play.mvc.*;
import views.html.*;

public class BulletController extends Controller {

    /**
     *
     * @return
     */
    @Transactional
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

    /**
     *
     * @return
     */
    @Transactional
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

    /**
     *
     * @return
     */
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result addMajorCriterionToBullet(){
        try{
            String description = Controller.request().body().asJson().findPath("description").asText();
            Long idBullet = Controller.request().body().asJson().findPath("idBullet").asLong();
            MajorCriterion majorCriterion=new MajorCriterion(description);

            Bullet bullet=Bullet.find.byId(idBullet);
            if(bullet==null){
                throw new BulletException( ErrorMessage.NOT_CREATED);
            }
            if(majorCriterion==null){
                throw new MajorCriterionException( ErrorMessage.NOT_CREATED);
            }
            bullet.addMajorCriterionToBullet(majorCriterion);
            bullet.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
}
