package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Claim extends Model {

    //--------------------------------------------------------------------------------------------------------------------------
    //Attributes----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    private Date creationDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Admin processor;

    @OneToOne(cascade = CascadeType.ALL)
    private Option newOption;
    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Date getCreationDate() {
        return creationDate;
    }

    public Long getId() {
        return id;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Setters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
