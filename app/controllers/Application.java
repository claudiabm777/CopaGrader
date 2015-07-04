package controllers;

import play.*;
import play.data.DynamicForm;
import play.mvc.*;

import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
    public Result index2() {
        DynamicForm bindedForm = form().bindFromRequest();
        System.out.println(bindedForm.get("email")+"  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return ok(index.render("Your new application is ready."));
    }

}
