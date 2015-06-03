package models;

import com.avaje.ebean.Model;
import scala.Int;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Student extends Model {

    //--------------------------------------------------------------------------------------------------------------------------
    //Attributes----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String lastNames;
    private Integer code;
    private String career;
    private String email;
    private Integer magisSection;
    private Integer complSection;


    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Integer getCode() {
        return code;
    }

    public Integer getComplSection() {
        return complSection;
    }

    public Integer getMagisSection() {
        return magisSection;
    }

    public Long getId() {
        return id;
    }

    public String getCareer() {
        return career;
    }

    public String getEmail() {
        return email;
    }

    public String getLastNames() {
        return lastNames;
    }

    public String getNames() {
        return names;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //Setters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public void setCareer(String career) {
        this.career = career;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setComplSection(Integer complSection) {
        this.complSection = complSection;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public void setMagisSection(Integer magisSection) {
        this.magisSection = magisSection;
    }

    public void setNames(String names) {
        this.names = names;
    }

}
