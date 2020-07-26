package controllers;

import domain.Password;

public class MyForm {
    protected String name;
    protected String password;
    protected String radio;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPassword(String password){
        //Password password = new Password(input);
        //this.password = password.getPassword();
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setRadio(String radio){
        this.radio = radio;
    }

    public String getRadio(){
        return radio;
    }
}
