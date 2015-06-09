package Exceptions;

import models.Grader;

/**
 * Created by Asus on 09/06/2015.
 */
public class GraderException extends Exception {

    public final static String CODE_REPEATED ="Ya existe un calificador con este mismo email y no deben estar repeidos. Por favor ingrese un nuevo email para este.";

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
