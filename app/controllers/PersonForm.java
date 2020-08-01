package controllers;

import java.util.*;

public class PersonForm {
    protected int id;
    protected String name;
    protected String mail;
    protected String tel;

    public PersonForm(){
        super();
    }



    public PersonForm(int id){
        super();
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String telephoneNumber) {
        this.tel = telephoneNumber;
    }
}
