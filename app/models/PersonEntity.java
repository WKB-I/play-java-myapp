package models;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String mail;
    private String tel;

    public PersonEntity(){
        super();
    }

    public PersonEntity(int id, String name, String mail, String tel){
        super();
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.tel = tel;
    }

    public Integer getId(){
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

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString(){
        return id + ": " + name + " [" + mail + ", " + tel +"]";
    }
}
