package models;

import Exceptions.CourseException;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Course extends Model{

    //--------------------------------------------------------------------------------------------------------------------------
    //Attributes----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

    private String name;
    private Integer credits;
    private String department;
    @Id
    private String code;
    private Integer crn;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Semester>semesters;

    public static Finder<String,Course> find = new Finder<String,Course>(
            String.class, Course.class
    );

    //--------------------------------------------------------------------------------------------------------------------------
    //Constructor---------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Course(String name, Integer credits, String department, String code, Integer crn){
        this.name=name;
        this.credits=credits;
        this.department=department;
        this.code=code;
        this.crn=crn;
        semesters=new ArrayList<Semester>();
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Integer getCredits() {
        return credits;
    }

    public Integer getCrn() {
        return crn;
    }

    public String getCode() {
        return code;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Setters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public void setCode(String code) {
        this.code = code;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public void setCrn(Integer crn) {
        this.crn = crn;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Methods-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public static Course transformJson(JsonNode j)throws Throwable{
        String name = j.findPath("name").asText();
        Integer credits=j.findPath("credits").asInt();
        String department = j.findPath("department").asText();
        String code = j.findPath("code").asText();
        Integer crn = j.findPath("crn").asInt();

        Course p = new Course(name, credits, department,code,crn);
        return p;
    }
}
