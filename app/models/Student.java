package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import scala.Int;

import javax.persistence.*;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Student extends Model {

    //--------------------------------------------------------------------------------------------------------------------------
    //Attributes----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String lastNames;
    private String code;
    private String career;
    private String email;
    private Integer magisSection;
    private Integer complSection;

    public static Finder<Long,Student> find = new Finder<Long,Student>(
            Long.class, Student.class
    );

    //--------------------------------------------------------------------------------------------------------------------------
    //Constructor---------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Student(String names, String lastNames, String career, String code, String email, Integer magisSection, Integer complSection){
        this.career=career;
        this.code=code;
        this.email=email;
        this.names=names;
        this.lastNames=lastNames;
        this.magisSection=magisSection;
        this.complSection=complSection;
    }
    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public String getCode() {
        return code;
    }

    public Integer getComplSection() {
        return complSection;
    }

    public Integer getMagisSection() {
        return magisSection;
    }

    public Long getId() {
        return id;
    }

    public String getCareer() {
        return career;
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
    public void setCareer(String career) {
        this.career = career;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setComplSection(Integer complSection) {
        this.complSection = complSection;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public void setMagisSection(Integer magisSection) {
        this.magisSection = magisSection;
    }

    public void setNames(String names) {
        this.names = names;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Methods-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public static Student transformJson(JsonNode j){
        String names = j.findPath("names").asText();
        String lastNames = j.findPath("lastNames").asText();
        String code=j.findPath("code").asText();
        String career=j.findPath("career").asText();
        String email = j.findPath("email").asText();
        Integer magisSection=j.findPath("magisSection").asInt();
        Integer complSection=j.findPath("complSection").asInt();
        Student student = new Student(names, lastNames, career,code,email,magisSection,complSection);
        return student;
    }
}
