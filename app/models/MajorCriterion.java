package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class MajorCriterion extends Model {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
