package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Grader extends Model {

    //--------------------------------------------------------------------------------------------------------------------------
    //Attributes----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------


    private String names;
    private String lastNames;
    private String identityCard;
    private String phone;
    @Id
    private String email;
    private String password;
    private Boolean enable;
    private Integer cargo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Team>teams;

    public static Finder<Long,Grader> find = new Finder<Long,Grader>(
            Long.class, Grader.class
    );

    //--------------------------------------------------------------------------------------------------------------------------
    //Constructor---------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Grader(String names, String lastNames, String identityCard, String phone,String email, String password, Boolean enable,Integer cargo){
        this.teams=new ArrayList<Team>();
        this.names=names;
        this.lastNames=lastNames;
        this.identityCard=identityCard;
        this.phone=phone;
        this.email=email;
        this.password=password;
        this.enable=enable;
        this.cargo=cargo;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Boolean getEnable() {
        return enable;
    }

    public Integer getCargo() {
        return cargo;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getLastNames() {
        return lastNames;
    }

    public String getNames() {
        return names;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Setters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public void setCargo(Integer cargo) {
        this.cargo = cargo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
