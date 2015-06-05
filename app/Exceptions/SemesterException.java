package Exceptions;

import models.Course;
import models.Semester;

/**
 * Created by Asus on 05/06/2015.
 */
public class SemesterException extends Exception {


    public SemesterException(String code,String cause) {
        super("El semestre "+code+cause);
    }
}
