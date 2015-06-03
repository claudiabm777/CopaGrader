package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Asus on 02/06/2015.
 */
@Entity
public class Criterion extends Model {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;
}
