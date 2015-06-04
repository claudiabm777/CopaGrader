package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Grader extends Model {

    //--------------------------------------------------------------------------------------------------------------------------
    //Attributes----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String names;
    private String lastNames;
    private Long identityCard;
    private Long phone;
    private String email;
    private String contrasenia;
    private Boolean habilitado;
    private Integer cargo;

    @OneToMany(mappedBy="grader",cascade = CascadeType.ALL)
    private List<Team>teams;

    public static Finder<Long,Grader> find = new Finder<Long,Grader>(
            Long.class, Grader.class
    );
    //--------------------------------------------------------------------------------------------------------------------------
    //Getters-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    public Boolean getHabilitado() {
        return habilitado;
    }

    public Integer getCargo() {
        return cargo;
    }

    public Long getId() {
        return id;
    }

    public Long getIdentityCard() {
        return identityCard;
    }

    public Long getPhone() {
        return phone;
    }

    public String getContrasenia() {
        return contrasenia;
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
    public void setCargo(Integer cargo) {
        this.cargo = cargo;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentityCard(Long identityCard) {
        this.identityCard = identityCard;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

}
