package models;

import Exceptions.AdminException;
import Exceptions.ErrorMessage;
import Exceptions.TaskException;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.format.Formats;
import play.libs.Json;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    @Formats.DateTime(pattern="dd/MM/yyyy hh:mm:ss")
    private Date deadline;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    @Formats.DateTime(pattern="dd/MM/yyyy hh:mm:ss")
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
    public Activity(String name, Date deadline, Date creationDate ){
        this.name=name;
        this.deadline=deadline;
        this.creationDate=creationDate;
        this.tasks=new ArrayList<Task>();
        this.teams=new ArrayList<Team>();
        this.adminsInCharge=new ArrayList<Admin>();
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

    public void addAdminInCharge(String idAdmin) throws Exception {
        Admin newAdmin=Admin.find.byId(idAdmin);
        if(newAdmin==null){
            throw new AdminException(idAdmin, ErrorMessage.NOT_CREATED);
        }
        for(int i=0;i<adminsInCharge.size();i++){
            if((adminsInCharge.get(i).getEmail()).equals(newAdmin.getEmail()))
            {
                throw new AdminException(this,idAdmin,ErrorMessage.EXISTS);
            }
        }
        adminsInCharge.add(newAdmin);
    }

    public void addTaskToActivity(Task newTask) throws Exception {
        tasks.add(newTask);
    }

    public void addTeam(Team team){
        teams.add(team);
    }
    public static Activity transformJson(JsonNode j) throws Exception {
        String name = j.findPath("name").asText();
        Date deadline= new Date(j.findPath("deadline").asLong());
        JsonNode lista=j.findValue("adminsInCharge");
        List<String> idsAdmins=Json.fromJson(lista, List.class);
        Date creationDate=new Date(System.currentTimeMillis()- 3600 * 5000 );
        Activity activity = new Activity(name,deadline,creationDate);
        if(lista!=null) {
            for (int i = 0; i < idsAdmins.size(); i++) {
                activity.addAdminInCharge(idsAdmins.get(i));
            }
        }
        return activity;
    }
}
