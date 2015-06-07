package controllers;

import Exceptions.CourseException;
import Exceptions.ErrorMessage;
import Exceptions.SuperAdminException;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import models.Course;
import models.SuperAdmin;
import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import java.util.List;

/**
 * Created by Asus on 04/06/2015.
 */
public class SuperAdminController extends Controller {
    /**
     * This method creates a super admin in the system.
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result createSuperAdmin(){
        try {
            JsonNode newSAdmin = Controller.request().body().asJson();
            SuperAdmin sAdmin = SuperAdmin.transformJson(newSAdmin);
            SuperAdmin old=SuperAdmin.find.byId(sAdmin.getEmail());
            if(old!=null){
                throw new SuperAdminException(old, ErrorMessage.ALREADY_CREATED);
            }
            sAdmin.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     * This method gets all the super admins in the system.
     * @return
     */
    public Result getSuperAdmins(){
        try {
            List<SuperAdmin> superAdmins = SuperAdmin.find.all();
            return ok(Json.toJson(superAdmins));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     * This method get a super admin by id (email).
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result getASuperAdminId(){
        try {
            String idSAdmin = Controller.request().body().asJson().findPath("idSAdmin").asText();
            SuperAdmin superAdmin=SuperAdmin.find.byId(idSAdmin);
            if (superAdmin==null){
                throw new SuperAdminException(idSAdmin,ErrorMessage.NOT_CREATED);
            }
            return ok(Json.toJson(superAdmin));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     * This method edits the information of a super admin.
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result editSuperAdmin(){
        try {
            JsonNode newSAdmin = Controller.request().body().asJson();
            String oldId=newSAdmin.findPath("oldId").asText();
            SuperAdmin superAdmin = SuperAdmin.transformJson(newSAdmin);
            SuperAdmin superAdmin1 = SuperAdmin.find.byId(oldId);
            if (superAdmin1 == null) {
                throw new SuperAdminException(superAdmin1.getEmail(), ErrorMessage.NOT_CREATED);
            }
            if((!superAdmin.getEmail().equals(oldId))&&(SuperAdmin.find.byId(superAdmin.getEmail())!=null)){
                throw new SuperAdminException(SuperAdminException.CODE_REPEATED);
            }
            Ebean.execute(() -> {
                superAdmin1.setIdentityCard(superAdmin.getIdentityCard());
                superAdmin1.setLastNames(superAdmin.getLastNames());
                superAdmin1.setNames(superAdmin.getNames());
                superAdmin1.setPhone(superAdmin.getPhone());
                superAdmin1.update();
                Ebean.createSqlUpdate("update super_admin set email = :email where email = :id")
                        .setParameter("email", superAdmin.getEmail())
                        .setParameter("id", oldId)
                        .execute();
            });
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     * This method deletes a super admin by id.
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteSuperAdminId(){
        try {
            String idSAdmin = Controller.request().body().asJson().findPath("idSAdmin").asText();
            SuperAdmin superAdmin=SuperAdmin.find.byId(idSAdmin);
            if(superAdmin==null){
                throw new SuperAdminException(idSAdmin, ErrorMessage.NOT_CREATED);
            }
            superAdmin.delete();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

}
