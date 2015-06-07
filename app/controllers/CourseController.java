package controllers;

import Exceptions.CourseException;
import Exceptions.ErrorMessage;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import models.Course;
import models.Semester;
import play.libs.Json;
import play.mvc.*;

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
            Course old=Course.find.byId(course.getCode());
            if(old!=null){
                throw new CourseException(old, ErrorMessage.ALREADY_CREATED);
            }
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
            return badRequest(e.getMessage());
        }
    }

    /**
     * This method deletes a course by id.<br>
     * @return Result depending if the transaction was successful or not
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteCourseId(){
        try {
            String idCourse = Controller.request().body().asJson().findPath("idCourse").asText();
            Course course=Course.find.byId(idCourse);
            if(course==null){
                throw new CourseException(idCourse, ErrorMessage.NOT_CREATED);
            }
            course.delete();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }

    }

    /**
     * This method gets a course by id.<br>
     * @return Result
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result getCourseId(){
        try {
            String idCourse = Controller.request().body().asJson().findPath("idCourse").asText();
            Course course=Course.find.byId(idCourse);
            if (course==null){
                throw new CourseException(idCourse,ErrorMessage.NOT_CREATED);
            }
            return ok(Json.toJson(course));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     * This method adds a semester to a course. Both they have to be created in the system.
     * @return Result
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result addSemesterToCourse(){
        try{
            JsonNode j=Controller.request().body().asJson();
            Semester semester=Semester.transformJson(j);

            String idCourse = Controller.request().body().asJson().findPath("idCourse").asText();
            Course course=Course.find.byId(idCourse);
            if(course==null){
                throw new CourseException(idCourse,ErrorMessage.NOT_CREATED);
            }
            semester.setCourse(course);
            course.addSemesterToCourse(semester);
            course.save();
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     * This method deletes a semester from a course.
     * The information received throw the json have to be ok.
     * @return Result
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteSemesterFromCourse(){
        try {
            Long idSemester = Controller.request().body().asJson().findPath("idSemester").asLong();
            String idCourse = Controller.request().body().asJson().findPath("idCourse").asText();
            Course course = Course.find.byId(idCourse);
            if (course == null) {
                throw new CourseException(idCourse, ErrorMessage.NOT_CREATED);
            }
            course.deleteSemesterFromCourse(idSemester);
            course.update();
            return ok();

        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     * This method gets the semesters of a course.
     * If the course have not semesters, returns an ok whit an empty json.
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result getSemestersFromCourse(){
        try{
            String idCourse = Controller.request().body().asJson().findPath("idCourse").asText();
            Course course = Course.find.byId(idCourse);
            if(course==null){
                throw new CourseException(idCourse,ErrorMessage.NOT_CREATED);
            }
            List<Semester>semesters=course.getSemesters();
            return ok(Json.toJson(semesters));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    /**
     * This method gets a semester from a course.
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result getASemesterFromCourse(){
        try {
            Long idSemester = Controller.request().body().asJson().findPath("idSemester").asLong();
            String idCourse = Controller.request().body().asJson().findPath("idCourse").asText();
            Course course = Course.find.byId(idCourse);
            if (course == null) {
                throw new CourseException(idCourse, ErrorMessage.NOT_CREATED);
            }
            Semester semester=course.getASemesterFromCourse(idSemester);
            return ok(Json.toJson(semester));
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }
    /**
     * This method edits the basic information of a course.
     * @return Result
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result editCourse(){
        try {
            JsonNode newCourse = Controller.request().body().asJson();
            String oldId=newCourse.findPath("oldId").asText();
            Course course = Course.transformJson(newCourse);
            Course course1 = Course.find.byId(oldId);
            if (course1 == null) {
                throw new CourseException(course.getCode(), ErrorMessage.NOT_CREATED);
            }
            if((!course.getCode().equals(oldId))&&(Course.find.byId(course.getCode())!=null)){
                throw new CourseException(CourseException.CODE_REPEATED);
            }
            Ebean.execute(() -> {
                course1.setCredits(course.getCredits());
                course1.setCrn(course.getCrn());
                course1.setDepartment(course.getDepartment());
                course1.setName(course.getName());
                course1.update();
                Ebean.createSqlUpdate("update course set code = :code where code = :id")
                        .setParameter("code", course.getCode())
                        .setParameter("id", oldId)
                        .execute();
            });
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

}
