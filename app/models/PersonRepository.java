package models;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import javax.inject.*;
import javax.persistence.*;
import play.db.jpa.*;
import play.libs.concurrent.*;

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
}
