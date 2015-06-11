package controllers;

import Exceptions.ErrorMessage;
import Exceptions.StudentException;
import models.Student;
import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import java.util.List;

/**
 * Created by Asus on 04/06/2015.
 */
public class StudentController extends Controller {
    /**
     *
     * @return
     */
    public Result getStudents(){
        try {
            List<Student>students=Student.find.all();
            return ok(Json.toJson(students));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result getStudentId(){
        try{
            Long idStudent = Controller.request().body().asJson().findPath("idStudent").asLong();
            Student student=Student.find.byId(idStudent);
            if (student==null){
                throw new StudentException( ErrorMessage.NOT_CREATED);
            }
            return ok(Json.toJson(student));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }


}
