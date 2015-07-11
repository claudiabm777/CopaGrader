package controllers;

import Exceptions.MessagesViews;
import com.fasterxml.jackson.databind.JsonNode;
import models.*;
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
    public Result ppp() {
        try {
            DynamicForm bindedForm = form().bindFromRequest();
            String act = bindedForm.get("act");
            System.out.println(act + "---------------------------------------");
            List<Team>l=GraderController.getTeamGraderActivity(courseP.getCode(), semesterP.getId(), userP, Long.parseLong(act));
            return ok(teamsGrader.render(l));
        }catch (Throwable e){
            return  badRequest(errorMessage.render("ffffffff   g "+e.getMessage()));
        }
    }
    public Result homeAdmin() {
        try {
            if (userP.equals("") || courseP == null || rol.equals("") || semesterP == null) {
                return badRequest(errorMessage.render(""));
            }
            List<Activity>activities=semesterP.getActivities();
            if(activities==null){
                return badRequest(errorMessage.render(""));
            }
            return ok(homeAdmin.render());
        }catch (Throwable e){
            return badRequest(errorMessage.render(MessagesViews.ERROR_MESSAGE+" "+e.getMessage()));
        }
    }
    public Result homeGrader() {
        try {
            if (userP.equals("") || courseP == null || rol.equals("") || semesterP == null) {
                return badRequest(errorMessage.render(""));
            }
            List<Activity>activities=semesterP.getActivities();
            if(activities==null){
                return badRequest(errorMessage.render(""));
            }
            return ok(homeGrader.render(activities));
        }catch (Throwable e){
            return badRequest(errorMessage.render(MessagesViews.ERROR_MESSAGE+" "+e.getMessage()));
        }
    }


    public Result login() {
        try {
            List<Course> listC = Course.find.where().orderBy("name asc").findList();
            System.out.println(listC.size());
            return ok(login.render(listC));
        }catch (Throwable e){
            return badRequest(errorMessageHome.render(MessagesViews.ERROR_MESSAGE+" "+e.getMessage()));
        }
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
                            return redirect(routes.Application.homeAdmin());
                        } else {
                            return badRequest(views.html.errorMessage.render(MessagesViews.PASSWORD_FAIL));
                        }
                    }
                }
            } else {
                Grader grader = Grader.find.byId(user.trim().toLowerCase() + "@uniandes.edu.co");
                System.out.println("11111111111111!!!!!!!!!!!!!!!!!!!!!!!!!");
                if (grader == null) {
                    return badRequest(views.html.errorMessage.render(MessagesViews.GRADER_NOT_VALID));
                } else {
                    System.out.println("2222222222111111111!!!!!!!!!!!!!!!!!!!!!!!!!");
                    Course course1 = Course.find.byId(course.split(":")[0]);
                    if (course1 == null) {
                        return badRequest(views.html.errorMessage.render(MessagesViews.ERROR_MESSAGE));
                    }
                    Semester semester=course1.getASemesterFromCourse(semesterP.getId());
                    if(semester==null){
                        System.out.println("3333333333333311111111111111!!!!!!!!!!!!!!!!!!!!!!!!!");
                        return badRequest(views.html.errorMessage.render(MessagesViews.ERROR_MESSAGE));
                    }
                    List<Grader>graders= semester.getGraders();
                    Grader gg=null;
                    for(Grader g:graders){
                        if(g.getEmail().equals(grader.getEmail())){
                            gg=g;
                        }
                    }
                    System.out.println("444444444444411111111111111!!!!!!!!!!!!!!!!!!!!!!!!!");
                    if (gg == null) {
                        return badRequest(views.html.errorMessage.render(MessagesViews.GRADER_COURSE_NOT_VALID));
                    } else {
                        if (grader.getPassword().equals(pwd)) {
                            userP=grader.getEmail();
                            courseP=course1;
                            rol="G";
                            return redirect(routes.Application.homeGrader());
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
        try {
            DynamicForm bindedForm = form().bindFromRequest();
            System.out.println(bindedForm.get("email") + "  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            String em = bindedForm.get("email");
            String tx = bindedForm.get("tx");
            String subj = bindedForm.get("sbj");
            String messag = bindedForm.get("message");
            sendEmail(em, messag, subj, tx);
            return ok(succesEmailContact.render());
        }catch (Throwable e){
            return badRequest(errorMessageHome.render(MessagesViews.ERROR_MESSAGE+" "+e.getMessage()));
        }
    }

    public Result sendEmail(String emailN, String text, String subject, String name){//String idAdmin,OptionRequest optionRequest) {
        try {
            Email email = new Email();
            email.setSubject(subject);
            email.setFrom("CopaGrader <copa.grader@gmail.com>");
            email.addTo("TO <cd.bedoya212@uniandes.edu.co>");
            //email.addTo("<claudiabm777@gmail.com>");
            //System.out.println(email.getTo().toString());

            // adds attachment
            //email.addAttachment("favicon.png", new File(Play.application().classloader().getResource("public/images/favicon.png").getPath()));
            // adds inline attachment from byte array
            //email.addAttachment("data.txt", "data".getBytes(), "text/plain", "Simple data", EmailAttachment.INLINE);
            // sends text, HTML or both...
            String body = views.html.homeContact.render(text, name, emailN).body();
            email.setBodyHtml(body);
            mailerClient.send(email);
            return ok();
        }catch (Throwable e){
            return badRequest(errorMessageHome.render(MessagesViews.ERROR_MAIL_MESSAGE+" "+e.getMessage()));
        }
    }

}
