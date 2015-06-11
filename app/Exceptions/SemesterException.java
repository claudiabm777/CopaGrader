package Exceptions;

import models.Course;
import models.Semester;

/**
 * Created by Asus on 05/06/2015.
 */
public class SemesterException extends Exception {


    public SemesterException(String cause) {
        super("El semestre"+cause);
    }

    public SemesterException(String idGrader,String cause){
        super("El calificador con email "+idGrader+cause+"en este semestre.");
    }
}
