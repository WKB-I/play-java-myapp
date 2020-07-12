package controllers;

import play.mvc.*;
import akka.util.*;

import java.net.http.HttpRequest;
import java.util.*;
import play.http.*;

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
    public Result index(int id, Optional name) {
        return ok("<title>Hello!</title><h1>Hello!</h1><p>ID ="+ id + ", NAME = "+ name.orElse("no-name") + "です。</p>")
                .as("text/html");
    }
}

