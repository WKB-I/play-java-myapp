package controllers;

import play.mvc.*;

import java.util.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render(
                "This is a message I prepared for you at the controller."
        ));
    }

    public Result form(){
        Map<String, String[]> param = request().body().asFormUrlEncoded();
        String name = param.get("name")[0];
        String password = param.get("name")[0];
        return ok(views.html.index.render(
           "name:" + name + ", password:" + password
        ));
    }
}

