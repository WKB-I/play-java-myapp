package controllers;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

import javax.inject.*;

import models.*;
import play.mvc.*;
import play.data.*;
import play.libs.concurrent.*;

import static play.libs.Scala.asScala;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    private final Form<PersonForm> personform;
    private final FormFactory formFactory;
    private final PersonRepository personRepository;
    private final HttpExecutionContext ec;

    @Inject
    public HomeController(FormFactory formFactory, PersonRepository personRepository, HttpExecutionContext ec){
        this.formFactory = formFactory;
        this.personform = formFactory.form(PersonForm.class);
        this.personRepository = personRepository;
        this.ec = ec;
    }

    public CompletionStage<Result> index(){
        return personRepository.list().thenApplyAsync(personlist ->{
            return ok(views.html.index.render("People List.", personlist));
        }, ec.current());
    }

    public CompletionStage<Result> show(int id){
        return personRepository.get(id).thenApplyAsync(p ->{
            return ok(views.html.show.render("Show Person", p, id));
        }, ec.current());
    }
}

