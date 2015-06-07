package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class SuperAdmin extends Model {

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

    public static Finder<String,SuperAdmin> find = new Finder<String,SuperAdmin>(
            String.class, SuperAdmin.class
    );

    //--------------------------------------------------------------------------------------------------------------------------
    //Constructor---------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public SuperAdmin(String names, String lastNames, String identityCard, String phone, String email, String password){
        this.names=names;
        this.lastNames=lastNames;
        this.identityCard=identityCard;
        this.phone=phone;
        this.email=email;
        this.password =password;
    }
    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
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
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
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


    //--------------------------------------------------------------------------------------------------------------------------
    //Methods-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

    public static SuperAdmin transformJson(JsonNode j){
        String names = j.findPath("names").asText();
        String lastNames = j.findPath("lastNames").asText();
        String identityCard=j.findPath("identityCard").asText();
        String phone=j.findPath("phone").asText();
        String email = j.findPath("email").asText();
        String password = j.findPath("password").asText();
        SuperAdmin superAdmin = new SuperAdmin(names, lastNames, identityCard,phone,email,password);
        return superAdmin;
    }

}
