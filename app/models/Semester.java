package models;

import com.avaje.ebean.Model;


import javax.persistence.GenerationType;
import javax.persistence.*;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Semester extends Model {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Integer period;
    private Integer year;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPeriod() {
        return period;
    }

    public Integer getYear() {
        return year;
    }

    public Long getId() {
        return id;
    }

}
