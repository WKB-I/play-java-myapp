package models;

import play.data.validation.Constraints;

import javax.persistence.*;
import play.data.validation.Constraints.*;

import java.util.ArrayList;
import java.util.List;

import io.ebean.*;

@Entity
@Table(name = "people")
public class PersonEntity extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Required(message = "nameは必須項目です。")
    @MinLength(value = 3, message = "3文字以上入力してください。")
    @MaxLength(value = 10, message = "10文字以内にしてください。")
    private String name;
    @Required(message = "mailは必須項目です。")
    @Email(message = "メールアドレスを入力してください。")
    private String mail;
    @Required(message = "telは必須項目です。")
    @Pattern(value = "[0-9- ]+", message = "半角英数字とハイフンのみ入力可能です。")
    private String tel;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    public List<MessageEntity> messages = new ArrayList<MessageEntity>();

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
        String list = "<div><ul>";
        for(MessageEntity entity: messages){
            list += "<li>" + entity.getMessage() + "</li>";
        }
        return "<b>" + id + ": " + name + "</b> ("  + mail + ", " + tel + ") " + list;
    }
}
