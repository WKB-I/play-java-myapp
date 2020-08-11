package models;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import javax.inject.*;
import javax.persistence.*;

import models.jdatabase.DatabaseExecutionContext;
import play.db.jpa.*;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class PersonRepository {
    private JPAApi jpaApi;
    private DatabaseExecutionContext executionContext;

    @Inject
    public PersonRepository(JPAApi api, DatabaseExecutionContext executionContext){
        this.jpaApi = api;
        this.executionContext = executionContext;
    }

    //wrap transaction
    private <T> T withTransaction(Function<EntityManager, T> function){
        return jpaApi.withTransaction(function);
    }

    public CompletionStage<List<PersonEntity>> list() {
        return supplyAsync(
                () -> withTransaction(em -> list(em)),
                executionContext);
    }

    private List<PersonEntity> list(EntityManager em){
        return em.createQuery("select p from PersonEntity p", PersonEntity.class).getResultList();
    }

    public CompletionStage<PersonEntity> get(int id){
        return supplyAsync(
                () -> withTransaction(em -> get(em, id)),
                executionContext
        );
    }

    private PersonEntity get(EntityManager em, int id){
        return em.find(PersonEntity.class, id);
    }

    public CompletionStage<PersonEntity> add(PersonEntity person){
        return supplyAsync(
                () -> withTransaction(em -> add(em, person)),
                executionContext);
    }

    private PersonEntity add(EntityManager em, PersonEntity person){
        em.persist(person);
        return person;
    }
}
