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

    public Claim getClaim() {
        return claim;
    }

    public List<Option> getOptions() {
        return options;
    }

    public List<OptionRequest> getOptionRequests() {
        return optionRequests;
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

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public void setOptionRequests(List<OptionRequest> optionRequests) {
        this.optionRequests = optionRequests;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
    //--------------------------------------------------------------------------------------------------------------------------
    //Methods-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

    public void addOptionToCriterion(Option option){
        options.add(option);
    }
    public void addOptionRequest(OptionRequest optionRequest){
        optionRequests.add(optionRequest);
    }
}
