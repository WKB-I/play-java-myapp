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

    public Result add(){
        return ok(views.html.add.render(
                "Please fill out the form.",
                personform
                ));
    }

    public CompletionStage<Result> create(){
        PersonEntity person = formFactory.form(PersonEntity.class).bindFromRequest().get();
        return personRepository.add(person).thenApplyAsync(p -> {
            return redirect(routes.HomeController.index());
        }, ec.current());
    }

    public CompletionStage<Result> edit(int id){
        return personRepository.get(id).thenApplyAsync(p ->{
            PersonForm form = new PersonForm(id);
            form.setName(p.getName());
            form.setMail(p.getMail());
            form.setTel(p.getTel());
            Form<PersonForm> formdata = personform.fill(form);
            return ok(views.html.edit.render(
                    "Edit Person", formdata, id
            ));
        }, ec.current());
    }

    public CompletionStage<Result> update(int id){
        PersonForm form = formFactory.form(PersonForm.class).bindFromRequest().get();
        PersonEntity person = new PersonEntity(id, form.getName(), form.getMail(), form.getTel());
        return personRepository.update(person).thenApplyAsync(p -> {
            return redirect(routes.HomeController.index());
        }, ec.current());
    }

    public CompletionStage<Result> delete(int id){
        return personRepository.get(id).thenApplyAsync(p -> {
            return ok(views.html.delete.render(
                    "Delete Person", p, id
            ));
        }, ec.current());
    }

    public CompletionStage<Result> remove(int id){
        return personRepository.delete(id).thenApplyAsync(p -> {
            return redirect(routes.HomeController.index());
        }, ec.current());
    }

    public Result find(){
        return ok(views.html.find.render(
           "Please enter your search text",
            formFactory.form(PersonFind.class),
            new ArrayList<PersonEntity>()
        ));
    }

    public CompletionStage<Result> search(){
        Form<PersonFind> form = formFactory.form(PersonFind.class).bindFromRequest();
        String find = form.get().getFind();
        return personRepository.find(find).thenApplyAsync(p -> {
            return ok(views.html.find.render(
                "find word：" + find, form, p
            ));
        }, ec.current());
    }

}

