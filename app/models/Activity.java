package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.*;
import java.util.function.BooleanSupplier;

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
    private Date deadline;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    private Date creationDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task>tasks;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Team>teams;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Admin>adminsInCharge;



    public static Finder<Long,Activity> find = new Finder<Long,Activity>(
            Long.class, Activity.class
    );

    //--------------------------------------------------------------------------------------------------------------------------
    //Constructor---------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Activity(String name, Date deadline, Date creationDate, Semester semester){
        this.name=name;
        this.deadline=deadline;
        this.creationDate=creationDate;
    }

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

    public List<Admin> getAdminsInCharge() {
        return adminsInCharge;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Team> getTeams() {
        return teams;
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

    public void setAdminsInCharge(List<Admin> adminsInCharge) {
        this.adminsInCharge = adminsInCharge;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Methods-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    /*
    Add an admin in charge
     */
    public void addAdminInCharge(String idAdmin) throws Exception {
        Admin newAdmin=Admin.find.byId(idAdmin);
        if(newAdmin==null){
            throw new Exception("El asistente a cargo que esta tratando de agregar no existe.");
        }
        for(int i=0;i<adminsInCharge.size();i++){
            if((adminsInCharge.get(i).getEmail()).equals(newAdmin.getEmail()))
            {
                throw new Exception("El asistente a cargo que se trata de agregar ya había sido agregado en esta actividad.");
            }
        }
        adminsInCharge.add(newAdmin);
    }

    public void addTask(Long idTask) throws Exception {
        Task newTask=Task.find.byId(idTask);
        if(newTask==null){
            throw new Exception("El punto que se trata de agregar no existe.");
        }
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).getId()==newTask.getId())
            {
                throw new Exception("El punto que se esta tratando de agregar ya se había agregado.");
            }
        }
        tasks.add(newTask);
    }


}
