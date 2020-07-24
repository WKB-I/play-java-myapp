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
    public Result index(Optional<Integer> p) {
        List<List<String>> arr = new ArrayList<List<String>>(
                Arrays.asList(
                        new ArrayList<String>(
                                Arrays.asList("Taro","taro@yamada","999-999")
                        ),
                        new ArrayList<String>(
                                Arrays.asList("hanako","hanako@flower","888-888")
                        ),
                        new ArrayList<String>(
                                Arrays.asList("sachiko","sachiko@tekito","777-777")
                        )
                )
        );
        return ok(views.html.index.render(
                "This is setController message"
                , arr
                , new ArrayList(Arrays.asList("Name","Mail","Tel"))
        ));
    }
}

