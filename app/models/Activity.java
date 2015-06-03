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
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date deadline;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date creationDate;

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
