package controllers;

import Exceptions.ErrorMessage;
import Exceptions.SemesterException;
import com.fasterxml.jackson.databind.JsonNode;
import models.Activity;
import models.Semester;
import models.Student;
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

    @BodyParser.Of(BodyParser.Json.class)
    public Result addActivityToSemester(){
        try{
            JsonNode j=Controller.request().body().asJson();
            Activity activity=Activity.transformJson(j);
            //PENDIENTE AGREGAR ADMINISTRADORES ENCARGADOS!!
            Long idSemester = Controller.request().body().asJson().findPath("idSemester").asLong();
            Semester semester=Semester.find.byId(idSemester);
            if(semester==null){
                throw new SemesterException( ErrorMessage.NOT_CREATED);
            }
            semester.addActivityToSemester(activity);
            semester.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }


    @BodyParser.Of(BodyParser.Json.class)
    public Result addStudentToSemester(){
        try {
            JsonNode j = Controller.request().body().asJson();
            Student student = Student.transformJson(j);
            Long idSemester = Controller.request().body().asJson().findPath("idSemester").asLong();
            Semester semester = Semester.find.byId(idSemester);
            if (semester == null) {
                throw new SemesterException(ErrorMessage.NOT_CREATED);
            }
            semester.addStudentToSemester(student);
            semester.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }

    }

}
