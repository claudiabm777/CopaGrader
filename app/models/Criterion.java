package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Criterion extends Model {

    //--------------------------------------------------------------------------------------------------------------------------
    //Attributes----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;

    @OneToMany( cascade = CascadeType.ALL)
    private List<OptionRequest>optionRequests;

    @OneToOne(cascade = CascadeType.ALL)
    private Claim claim;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Option>options;

    public static Finder<Long,Criterion> find = new Finder<Long,Criterion>(
            Long.class, Criterion.class
    );

    //--------------------------------------------------------------------------------------------------------------------------
    //Constructor---------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Criterion(String description){
        this.optionRequests=new ArrayList<OptionRequest>();
        this.options=new ArrayList<Option>();
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

    //--------------------------------------------------------------------------------------------------------------------------
    //Methods-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

    public void addOptionToCriterion(Option option){
        options.add(option);
    }
}
