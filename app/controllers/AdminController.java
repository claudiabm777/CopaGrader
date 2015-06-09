package controllers;

import Exceptions.AdminException;
import Exceptions.CourseException;
import Exceptions.ErrorMessage;
import Exceptions.SuperAdminException;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import models.Admin;
import models.Course;
import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import java.util.List;

/**
 * Created by Asus on 04/06/2015.
 */
public class AdminController extends  Controller {
    /**
     * This method creates a new admin in the system.
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result createAdmin(){
        try {
            JsonNode admin = Controller.request().body().asJson();
            Admin admin1 = Admin.transformJson(admin);
            Admin old=Admin.find.byId(admin1.getEmail());
            if(old!=null){
                throw new AdminException(old, ErrorMessage.ALREADY_CREATED);
            }
            admin1.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     * This method gets all the admins in the system
     * @return
     */
    public Result getAdmins(){
        try {
            List<Admin> admin = Admin.find.all();
            return ok(Json.toJson(admin));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     * This method gets an admin in the system by id (email).
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result getAnAdminId(){
        try {
            String idAdmin = Controller.request().body().asJson().findPath("idAdmin").asText();
            Admin admin=Admin.find.byId(idAdmin);
            if (admin==null){
                throw new AdminException(admin,ErrorMessage.NOT_CREATED);
            }
            return ok(Json.toJson(admin));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }

    }

    /**
     *
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result editAdmin(){
        try {
            JsonNode newAdmin = Controller.request().body().asJson();
            String oldId=newAdmin.findPath("oldId").asText();
            Admin admin = Admin.transformJson(newAdmin);
            Admin admin1 = Admin.find.byId(oldId);
            if (admin1 == null) {
                throw new AdminException(admin1.getEmail(), ErrorMessage.NOT_CREATED);
            }
            if((!admin.getEmail().equals(oldId))&&(Admin.find.byId(admin.getEmail())!=null)){
                throw new AdminException(AdminException.CODE_REPEATED);
            }
            Ebean.execute(() -> {
                admin1.setIdentityCard(admin.getIdentityCard());
                admin1.setLastNames(admin.getLastNames());
                admin1.setNames(admin.getNames());
                admin1.setPhone(admin.getPhone());
                admin1.update();
                Ebean.createSqlUpdate("update admin set email = :email where email = :id")
                        .setParameter("email", admin.getEmail())
                        .setParameter("id", oldId)
                        .execute();
            });
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteAdminId(){
        try {
            String idAdmin = Controller.request().body().asJson().findPath("idAdmin").asText();
            Admin admin=Admin.find.byId(idAdmin);
            if(admin==null){
                throw new AdminException(idAdmin, ErrorMessage.NOT_CREATED);
            }
            admin.delete();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }

    }

    /**
     *
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result addCourseAdmin(){
        try {
            String idAdmin = Controller.request().body().asJson().findPath("idAdmin").asText();
            String idCourse = Controller.request().body().asJson().findPath("idCourse").asText();
            Admin admin = Admin.find.byId(idAdmin);
            Course course = Course.find.byId(idCourse);
            if (admin == null) {
                throw new AdminException(idAdmin, ErrorMessage.NOT_CREATED);
            }
            if(course==null){
                throw new CourseException(idCourse,ErrorMessage.NOT_CREATED);
            }
            admin.addCourseAdmin(course);
            admin.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteCourseAdmin(){
        try{
            String idAdmin = Controller.request().body().asJson().findPath("idAdmin").asText();
            String idCourse = Controller.request().body().asJson().findPath("idCourse").asText();
            Admin admin = Admin.find.byId(idAdmin);
            Course course = Course.find.byId(idCourse);
            if (admin == null) {
                throw new AdminException(idAdmin, ErrorMessage.NOT_CREATED);
            }
            if(course==null){
                throw new CourseException(idCourse,ErrorMessage.NOT_CREATED);
            }
            admin.deleteCourseAdmin(course);
            admin.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

}
