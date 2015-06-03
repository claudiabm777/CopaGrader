package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class OptionRequest extends Model {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;

    private Boolean isPenalty;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date creationDate;

    public Boolean getIsPenalty() {
        return isPenalty;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsPenalty(Boolean isPenalty) {
        this.isPenalty = isPenalty;
    }

}
