package controllers;

import play.mvc.*;
import java.util.*;
import javax.inject.*;
import play.data.*;

import static play.libs.Scala.asScala;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    private final Form<MyForm> myform;

    @Inject
    public HomeController(FormFactory factory){
        this.myform = factory.form(MyForm.class);
    }

    public Result index() {
        return ok(views.html.index.render(
                "This is a message I prepared for you at the controller.",
                this.myform
        ));
    }

    public Result form(){
        Form<MyForm> form = this.myform.bindFromRequest();
        MyForm myform = form.get();
        String name = myform.getName();
        String password = myform.getPassword();
        return ok(views.html.index.render(
           "This message is after send form.\n" + "name:" + name + ",password:" + password,
            form
        ));
    }
}

