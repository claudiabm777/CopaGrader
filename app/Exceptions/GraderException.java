package Exceptions;

import models.Grader;

/**
 * Created by Asus on 09/06/2015.
 */
public class GraderException extends Exception {
    public GraderException(Grader grader,String cause) {
        super("El calificador "+grader.getNames()+" "+grader.getLastNames()+" con email "+grader.getEmail()+cause);
    }

    public GraderException(String email,String cause) {
        super("El calificador con email "+email+cause);
    }

    public GraderException(String message) {
        super(message);
    }


}
