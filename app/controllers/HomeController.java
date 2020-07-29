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

    private final Form<PersonForm> personform;

    @Inject
    public HomeController(FormFactory factory){
        this.personform = factory.form(PersonForm.class);
    }

    public Result add(){
        return ok(views.html.add.render("Please fill out the form", personform));
    }

    public Result create(){
        Form<PersonForm> formdata = personform.bindFromRequest();
        PersonForm form = formdata.get();
        String name = form.getName();
        String mail = form.getMail();
        String telephonenumber = form.getTelephoneNumber();
        try{
            Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into people values(default, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, mail);
            preparedStatement.setString(3, telephonenumber);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            return ok(views.html.add.render("Please fill out the form", formdata));
        }
        return redirect(routes.HomeController.index());
    }

}

