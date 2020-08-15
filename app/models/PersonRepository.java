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

    public CompletionStage<PersonEntity> update(PersonEntity person){
        return supplyAsync(
                () -> withTransaction(em -> update(em, person)),
                executionContext);
    }

    private PersonEntity update(EntityManager em, PersonEntity person){
        em.merge(person);
        return person;
    }

    public CompletionStage<PersonEntity> delete(int id){
        return supplyAsync(
                () -> withTransaction(em -> delete(em, id)),
                executionContext
        );
    }

    private PersonEntity delete(EntityManager em, int id){
        PersonEntity p = get(em, id);
        em.remove(p);
        return p;
    }

    public CompletionStage<List<PersonEntity>> find(String find){
        return supplyAsync(
                () -> withTransaction(em -> find(em, find)),
                executionContext);
    }

    private List<PersonEntity> find(EntityManager em, String find){
        return em.createQuery("select p from PersonEntity p where name like ?1", PersonEntity.class).
                setParameter(1, "%" + find + "%").getResultList();
    }
}
