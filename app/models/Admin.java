package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Admin extends Model{
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

    public Boolean getHabilitado() {
        return habilitado;
    }

    public String getLastNames() {
        return lastNames;
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

    public String getNames() {
        return names;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
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

    public void setNames(String names) {
        this.names = names;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
