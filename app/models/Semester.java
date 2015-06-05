package models;

import com.avaje.ebean.Model;


import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Semester extends Model {

    //--------------------------------------------------------------------------------------------------------------------------
    //Attributes----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    @Id
     private String period;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Grader>graders;

    @OneToMany(mappedBy="semester",cascade = CascadeType.ALL)
    private List<Student>students;

    @OneToMany(mappedBy="semester",cascade = CascadeType.ALL)
    private List<Activity>activities;

    public static Finder<String,Semester> find = new Finder<String,Semester>(
            String.class, Semester.class
    );

    //--------------------------------------------------------------------------------------------------------------------------
    //Constructor---------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Semester(String period){
        this.period=period;
        this.graders=new ArrayList<Grader>();
        this.students=new ArrayList<Student>();
        this.activities=new ArrayList<Activity>();
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public String getPeriod() {
        return period;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Grader> getGraders() {
        return graders;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Setters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void setGraders(List<Grader> graders) {
        this.graders = graders;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
