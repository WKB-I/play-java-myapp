package models;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import javax.inject.*;
import javax.persistence.*;

import models.jdatabase.DatabaseExecutionContext;
import play.db.jpa.*;

import static java.util.concurrent.CompletableFuture.supplyAsync;
public class MessageRepository {
    private JPAApi jpaApi;
    private DatabaseExecutionContext executionContext;

    @Inject
    public MessageRepository(JPAApi api, DatabaseExecutionContext executionContext){
        this.jpaApi = api;
        this.executionContext = executionContext;
    }

    //wrap transaction
    private <T> T withTransaction(Function<EntityManager, T> function){
        return jpaApi.withTransaction(function);
    }

    public CompletionStage<List<MessageEntity>> list(){
        return supplyAsync(
                () -> withTransaction(em -> list(em)),
                executionContext);
    }

    private List<MessageEntity> list(EntityManager em){
        return em.createQuery("select p from MessageEntity p", MessageEntity.class).getResultList();
    }

    public CompletionStage<MessageEntity> get(int id){
        return supplyAsync(
                () -> withTransaction(em -> get(em, id)),
                executionContext
        );
    }

    private MessageEntity get(EntityManager em, int id){
        return em.find(MessageEntity.class, id);
    }

    public CompletionStage<MessageEntity> add(MessageEntity message){
        return supplyAsync(
                () -> withTransaction(em -> add(em, message)),
                executionContext);
    }

    private MessageEntity add(EntityManager em, MessageEntity message){
        em.persist(message);
        return message;
    }
}
