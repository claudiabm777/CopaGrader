package controllers;

import play.*;
import play.data.DynamicForm;
import play.mvc.*;
import play.libs.mailer.Email;
import play.api.libs.mailer.MailerClient;

import views.html.*;

import javax.inject.Inject;

import static play.data.Form.form;



public class Application extends Controller {

    @Inject
    MailerClient mailerClient;

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
    public Result login() {
        return ok(login.render());
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
