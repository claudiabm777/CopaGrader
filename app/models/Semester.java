package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;


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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

     private String period;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Grader>graders;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Student>students;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Activity>activities;

    public static Finder<Long,Semester> find = new Finder<Long,Semester>(
            Long.class, Semester.class
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

    public Long getId() {
        return id;
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

        public void setId(Long id) {
        this.id = id;
    }
    //--------------------------------------------------------------------------------------------------------------------------
    //Methods-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

    public static Semester transformJson(JsonNode j){
        String period1 = j.findPath("period").asText();
        Semester semester = new Semester(period1);
        return semester;
    }

    public static Integer searchSemesterInAList(List<Semester>semesters,Long idSemester){
        for(int i=0;i<semesters.size();i++){
            Semester semester=semesters.get(i);
            if(semester.getId().equals(idSemester)){
                return i;
            }
        }
        return -1;
    }
}
