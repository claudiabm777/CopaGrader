package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class MajorCriterion extends Model {

    //--------------------------------------------------------------------------------------------------------------------------
    //Attributes----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Criterion>criterions;

    public static Finder<Long,MajorCriterion> find = new Finder<Long,MajorCriterion>(
            Long.class, MajorCriterion.class
    );

    //--------------------------------------------------------------------------------------------------------------------------
    //Constructor---------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public MajorCriterion(String description){
        this.criterions=new ArrayList<Criterion>();
        this.description=description;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Setters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
