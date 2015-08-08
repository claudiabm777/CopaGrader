package controllers;

import Exceptions.ErrorMessage;
import Exceptions.GraderException;
import Exceptions.StudentException;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import models.Grader;
import models.Student;
import play.*;
import play.db.ebean.Transactional;
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
    @Transactional
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
    @Transactional
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

    /**
     *
     * @return
     */
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result editStudentId(){
        try{
            JsonNode newStudent = Controller.request().body().asJson();
            Long oldId=newStudent.findPath("oldId").asLong();
            Student student = Student.transformJson(newStudent);
            Student student1 = Student.find.byId(oldId);
            if (student1 == null) {
                throw new StudentException(ErrorMessage.NOT_CREATED);
            }
            student1.setCareer(student.getCareer());
            student1.setCode(student.getCode());
            student1.setComplSection(student.getComplSection());
            student1.setEmail(student.getEmail());
            student1.setLastNames(student.getLastNames());
            student1.setMagisSection(student.getMagisSection());
            student1.setNames(student.getNames());
            student1.update();
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
    public Result deleteStudent(){
        try{
            Long idStudent = Controller.request().body().asJson().findPath("idStudent").asLong();
            Student student=Student.find.byId(idStudent);
            if(student==null){
                throw new StudentException( ErrorMessage.NOT_CREATED);
            }
            student.delete();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }



}
