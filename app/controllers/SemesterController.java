package controllers;

import models.Semester;
import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import java.util.List;

/**
 * Created by Asus on 04/06/2015.
 */
public class SemesterController extends Controller {

    /**
     * This method returns all semesters created in the system.
     * @return
     */
    public Result getSemesters(){
        try{
            List<Semester> semesters=Semester.find.all();
            return ok(Json.toJson(semesters));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }


}
