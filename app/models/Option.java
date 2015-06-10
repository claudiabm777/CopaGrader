package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.DoubleSummaryStatistics;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Option extends Model {

    //--------------------------------------------------------------------------------------------------------------------------
    //Attributes----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String description;

    private Double score;
    private Boolean isSelected;
    private Boolean isPenalty;


    public static Finder<Long,Option> find = new Finder<Long,Option>(
            Long.class, Option.class
    );

    //--------------------------------------------------------------------------------------------------------------------------
    //Constructor---------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Option(String description, Double score, Boolean isPenalty){
        this.description=description;
        this.score=score;
        this.isPenalty=isPenalty;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Boolean getIsPenalty() {
        return isPenalty;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public Double getScore() {
        return score;
    }

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

    public void setIsPenalty(Boolean isPenalty) {
        this.isPenalty = isPenalty;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setScore(Double score) {
        this.score = score;
    }

}
