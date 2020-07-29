package controllers;

import play.mvc.*;
import java.util.*;
import javax.inject.*;
import play.data.*;
import play.db.*;
import java.sql.*;

import static play.libs.Scala.asScala;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    @Inject Database db;

    public Result index() {
        String msg = "database record:<br>";
        try{
            Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from people");
            msg += "<ul>";
            while (resultSet.next()){
                msg += "<li>" + resultSet.getInt("id") + ":" + resultSet.getString("name") + "</li>";
            }
            msg += "</ul>";
        }catch (SQLException e){}
        return ok(views.html.index.render(msg));
    }
}

