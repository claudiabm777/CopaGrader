package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Activity extends Model {

    //--------------------------------------------------------------------------------------------------------------------------
    //Attributes----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date deadline;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date creationDate;

    @OneToMany(mappedBy="activity",cascade = CascadeType.ALL)
    private List<Task>tasks;

    @OneToMany(mappedBy="activity",cascade = CascadeType.ALL)
    private List<Team>teams;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Admin>adminsInCharge;

    @ManyToOne
    private Semester semester;

    public static Finder<Long,Activity> find = new Finder<Long,Activity>(
            Long.class, Activity.class
    );

    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Setters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
