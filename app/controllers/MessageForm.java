package controllers;

import java.sql.Timestamp;

public class MessageForm {
    protected int id;
    protected int personId;
    protected String message;
    protected Timestamp created;

    public MessageForm(){
        super();
    }

    public MessageForm(int id, int personId, String message) {
        this.id = id;
        this.personId = personId;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

}
