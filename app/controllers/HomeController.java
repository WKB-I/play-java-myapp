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
    public Result index(Optional<String> name, Optional<String> value) {
        String s_name = name.orElse("");
        String s_value = value.orElse("");
        if(s_name != ""){
            session(s_name, s_value);
        }
        Map sessions = session();
        String message = "<pre>" + sessions + "</p>";
        return ok("<title>Hello!</title><h1>Hello!</h1>" + message).as("text/html");
    }
}

