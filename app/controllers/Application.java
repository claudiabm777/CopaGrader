package controllers;

import Exceptions.MessagesViews;
import com.fasterxml.jackson.databind.JsonNode;
import models.Admin;
import models.Course;
import models.Grader;
import models.Semester;
import play.*;
import play.data.DynamicForm;
import play.mvc.*;
import play.libs.mailer.Email;
import play.api.libs.mailer.MailerClient;

import views.html.*;

import javax.inject.Inject;

import java.util.List;

import static play.data.Form.form;



public class Application extends Controller {

    @Inject
    MailerClient mailerClient;

    public String userP="";
    public Course courseP=null;
    public String rol="";
    public Semester semesterP=Semester.find.byId(Long.parseLong("1"));


    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result homeAdmin() {
        if(userP.equals("")||courseP==null||rol.equals("")||semesterP==null){
            return badRequest(errorMessage.render(""));
        }
        return ok(homeGrader.render(semesterP.getActivities()));
    }
    public Result homeGrader() {
        return ok(teamsGrader.render());
    }


    public Result login() {
       List<Course> listC= Course.find.where().orderBy("name asc").findList();
        System.out.println(listC.size());
        return ok(login.render(listC));
    }
    public Result enterLogin(){
        try {
            DynamicForm bindedForm = form().bindFromRequest();
            String user = bindedForm.get("user");
            user = user.split("@")[0];
            String pwd = bindedForm.get("pwd");
            String course = bindedForm.get("course");
            String or = bindedForm.get("optradio");
            if (or.equals("A")) {
                Admin admin = Admin.find.byId(user.trim().toLowerCase() + "@uniandes.edu.co");
                if (admin == null) {
                    return badRequest(views.html.errorMessage.render(MessagesViews.ADMIN_NOT_VALID));
                } else {
                    List<Course> courses = admin.getCourses();
                    Course cc = null;
                    for (Course c : courses) {
                        if (c.getCode().equals(course.split(":")[0])) {
                            cc = c;
                        }
                    }
                    if (cc == null) {
                        return badRequest(views.html.errorMessage.render(MessagesViews.ADMIN_COURSE_NOT_VALID));
                    } else {
                        if (admin.getPassword().equals(pwd)) {
                            userP=admin.getEmail();
                            courseP=cc;
                            rol="A";
                            return redirect(routes.Application.homeGrader());
                        } else {
                            return badRequest(views.html.errorMessage.render(MessagesViews.PASSWORD_FAIL));
                        }
                    }
                }
            } else {
                Grader grader = Grader.find.byId(user.trim().toLowerCase() + "@uniandes.edu.co");
                if (grader == null) {
                    return badRequest(views.html.errorMessage.render(MessagesViews.GRADER_NOT_VALID));
                } else {
                    Course course1 = Course.find.byId(course.split(":")[0]);
                    if (course1 == null) {
                        return badRequest(views.html.errorMessage.render(MessagesViews.ERROR_MESSAGE));
                    }
                    Semester semester=course1.getASemesterFromCourse(semesterP.getId());
                    if(semester==null){
                        return badRequest(views.html.errorMessage.render(MessagesViews.ERROR_MESSAGE));
                    }
                    List<Grader>graders= semester.getGraders();
                    Grader gg=null;
                    for(Grader g:graders){
                        if(g.getEmail().equals(grader.getEmail())){
                            gg=g;
                        }
                    }

                    if (gg == null) {
                        return badRequest(views.html.errorMessage.render(MessagesViews.GRADER_COURSE_NOT_VALID));
                    } else {
                        if (grader.getPassword().equals(pwd)) {
                            userP=grader.getEmail();
                            courseP=course1;
                            rol="G";
                            return redirect(routes.Application.homeAdmin());
                        } else {
                            return badRequest(views.html.errorMessage.render(MessagesViews.PASSWORD_FAIL));
                        }
                    }
                }
            }
        }catch (Throwable e){
            return badRequest(views.html.errorMessage.render(MessagesViews.ERROR_MESSAGE+" "+e.getMessage()));
        }
    }
    public Result index2() {
        DynamicForm bindedForm = form().bindFromRequest();
        System.out.println(bindedForm.get("email")+"  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String em=bindedForm.get("email");
        String tx=bindedForm.get("tx");
        String subj=bindedForm.get("sbj");
        String messag=bindedForm.get("message");
        sendEmail(em,messag,subj,tx);
        return ok(succesEmailContact.render());
    }

    public Result sendEmail(String emailN, String text, String subject, String name){//String idAdmin,OptionRequest optionRequest) {
        Email email = new Email();
        email.setSubject(subject);
        email.setFrom("CopaGrader <copa.grader@gmail.com>");
        email.addTo("TO <cd.bedoya212@uniandes.edu.co>");
        // adds attachment
        //email.addAttachment("favicon.png", new File(Play.application().classloader().getResource("public/images/favicon.png").getPath()));
        // adds inline attachment from byte array
        //email.addAttachment("data.txt", "data".getBytes(), "text/plain", "Simple data", EmailAttachment.INLINE);
        // sends text, HTML or both...
        String body =views.html.homeContact.render(text,name,emailN).body();
        email.setBodyHtml(body);
        mailerClient.send(email);
        return ok();
    }

}
