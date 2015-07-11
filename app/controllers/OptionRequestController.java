package controllers;

import Exceptions.ActivityException;
import Exceptions.CriterionException;
import Exceptions.ErrorMessage;
import Exceptions.GraderException;
import com.avaje.ebean.Ebean;
import models.*;
import play.*;
import play.db.ebean.Transactional;
import play.mvc.*;
import views.html.*;
import play.libs.mailer.Email;
import play.api.libs.mailer.MailerClient;
import java.io.File;
import java.util.Date;
import java.util.List;
import org.apache.commons.mail.EmailAttachment;
import javax.inject.Inject;

/**
 * Created by Asus on 04/06/2015.
 */
public class OptionRequestController extends Controller {

    @Inject
    MailerClient mailerClient;


    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result proposeNewOption(){
        try{
            String idGrader = Controller.request().body().asJson().findPath("idGrader").asText();
            String description = Controller.request().body().asJson().findPath("description").asText();
            Boolean isPenalty=Controller.request().body().asJson().findPath("isPenalty").asBoolean();
            Long idCriterion=Controller.request().body().asJson().findPath("idCriterion").asLong();
            Long idActivity=Controller.request().body().asJson().findPath("idActivity").asLong();
            Grader grader=Grader.find.byId(idGrader);
            Criterion criterion=Criterion.find.byId(idCriterion);
            Activity activity=Activity.find.byId(idActivity);
            if(grader==null){
                throw new GraderException(idGrader, ErrorMessage.NOT_CREATED);
            }
            if(criterion==null){
                throw new CriterionException(ErrorMessage.NOT_CREATED);
            }
            if(activity==null){
                throw new ActivityException(ErrorMessage.NOT_CREATED);
            }
            Date creationDate=new Date(System.currentTimeMillis()- 3600 * 5000 );
            OptionRequest optionRequest=new OptionRequest(description,isPenalty,creationDate,grader);
            criterion.addOptionRequest(optionRequest);
            criterion.save();
           // List<String>admins=activity.getAdminsInCharge();
            //if(admins!=null ){
            //}
            return ok();
        }catch (Throwable e){
            return badRequest(e.getMessage());
        }
    }

    public Result sendEmail(){//String idAdmin,OptionRequest optionRequest) {
        Email email = new Email();
        email.setSubject(MailMessages.NEW_REQUEST);
        email.setFrom("CopaGrader <copa.grader@gmail.com>");
        email.addTo("TO <cd.bedoya212@uniandes.edu.co>");
        // adds attachment
        //email.addAttachment("favicon.png", new File(Play.application().classloader().getResource("public/images/favicon.png").getPath()));
        // adds inline attachment from byte array
        //email.addAttachment("data.txt", "data".getBytes(), "text/plain", "Simple data", EmailAttachment.INLINE);
        // sends text, HTML or both...
        String body =views.html.notasMail.render("").body();
        email.setBodyHtml(body);
        mailerClient.send(email);
        return ok();
    }
}