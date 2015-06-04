package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Course;
import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import java.util.List;

/**
 * Created by Asus on 04/06/2015.
 */
public class CourseController extends Controller {

    /**
     * This method creates a new course in the system.<br>
     * The course will always be created. It doesn't matter if there is another course with the same name or CRN or whatever.<br>
     * <b>pre:</b>The information obtained through the json is already properly.<br>
     * <b>post:</b>The course was created successfully.
     * @return result ok() if everything is ok, or badRequest if there was any problem.
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result createCourse() {
        try {
            JsonNode newCourse = Controller.request().body().asJson();
            Course course = Course.transformJson(newCourse);
            course.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     * This method gets all the courses in the system.<br>
     * @return ok() with the json with the list of courses if everything is ok, or badRequest() if there was a problem.<br>
     */
    public Result getCourses(){
        try{
            List<Course> courses=Course.find.all();
            return ok(Json.toJson(courses));
        }catch (Throwable e){
            return badRequest();
        }
    }

    /**
     * This method deletes a course by id.<br>
     * @return Result depending if the transaction was successful or not
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteCourseId(){
        try {
            String idCourse = Controller.request().body().asJson().findPath("id").asText();
            Course.find.byId(idCourse).delete();
            return ok();
        }catch (Throwable e){
            return badRequest();
        }

    }

    /**
     * This method gets a course by id.<br>
     * @return Result
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result getCourseId(){
        try {
            String idCourse = Controller.request().body().asJson().findPath("id").asText();
            Course course=Course.find.byId(idCourse);
            return ok(Json.toJson(course));
        }catch (Throwable e){
            return badRequest();
        }

    }


}
