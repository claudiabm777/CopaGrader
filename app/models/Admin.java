package models;

import Exceptions.AdminException;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Admin extends Model{

    //--------------------------------------------------------------------------------------------------------------------------
    //Attributes----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    @Id
    private String email;

    private String names;
    private String lastNames;
    private String identityCard;
    private String phone;
    private String password;
    private Boolean enable;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course>courses;

    public static Finder<String,Admin> find = new Finder<String,Admin>(
            String.class, Admin.class
    );

    //--------------------------------------------------------------------------------------------------------------------------
    //Constructor---------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Admin(String email, String names, String lastNames,String identityCard,String phone,String password, Boolean enable){
        this.email=email;
        this.names=names;
        this.lastNames=lastNames;
        this.identityCard=identityCard;
        this.phone=phone;
        this.password=password;
        this.enable=enable;
        this.courses=new ArrayList<Course>();
    }
    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Boolean getEnable() {
        return enable;
    }

    public String getLastNames() {
        return lastNames;
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

    public String getNames() {
        return names;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Setters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
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

    public void setNames(String names) {
        this.names = names;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Methods-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public static Admin transformJson(JsonNode j){
        String names = j.findPath("names").asText();
        String lastNames = j.findPath("lastNames").asText();
        String identityCard=j.findPath("identityCard").asText();
        String phone=j.findPath("phone").asText();
        String email = j.findPath("email").asText();
        String password = j.findPath("password").asText();
        Boolean enable=j.findPath("enable").asBoolean();
        Admin admin = new Admin(names, lastNames, identityCard,phone,email,password,enable);
        return admin;
    }

    public void addCourseAdmin(Course course)throws Exception{
        Integer i=Course.searchCourseInAList(courses,course.getCode());
        if(i!=-1){
            throw new AdminException(course,this.email);
        }
        courses.add(course);

    }
}
