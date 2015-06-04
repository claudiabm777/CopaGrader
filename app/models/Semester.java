package models;

import com.avaje.ebean.Model;


import javax.persistence.GenerationType;
import javax.persistence.*;
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

    private Integer period;
    private Integer year;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Grader>graders;

    @OneToMany(mappedBy="semester",cascade = CascadeType.ALL)
    private List<Student>students;

    @OneToMany(mappedBy="semester",cascade = CascadeType.ALL)
    private List<Activity>activities;

    public static Finder<Long,Semester> find = new Finder<Long,Semester>(
            Long.class, Semester.class
    );
    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Integer getPeriod() {
        return period;
    }

    public Integer getYear() {
        return year;
    }

    public Long getId() {
        return id;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Setters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public void setId(Long id) {
        this.id = id;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
