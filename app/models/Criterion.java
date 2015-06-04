package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
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

    @OneToMany(mappedBy="criterion", cascade = CascadeType.ALL)
    private List<OptionRequest>optionRequests;

    @OneToOne(cascade = CascadeType.ALL)
    private Claim claim;

    @OneToMany(mappedBy="criterion",cascade = CascadeType.ALL)
    private List<Option>options;

    @ManyToOne
    private MajorCriterion majorCriterion;

    public static Finder<Long,Criterion> find = new Finder<Long,Criterion>(
            Long.class, Criterion.class
    );
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
