package models;

import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;

import play.data.validation.*;
import play.data.validation.Constraints.*;

@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @Required(message = "必須項目です。")
    private PersonEntity person;

    @Required(message = "必須項目です。")
    private String message;

    private Timestamp created;

    public MessageEntity(){
        super();
    }

    public MessageEntity(int id, PersonEntity person, String message){
        this.id = id;
        this.person = person;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPersonId(PersonEntity person) {
        this.person = person;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreated() {
        return created;
    }

    @Override
    public String toString(){
        return id + ": " + message + "[ " + person.getName() + "] " + created;
    }
}
