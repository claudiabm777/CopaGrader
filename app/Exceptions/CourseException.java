package Exceptions;

import models.Course;

/**
 * Created by Asus on 04/06/2015.
 */

public class CourseException extends Exception {
    public final static String CODIGO_REPETIDO="Ya existe un curso con este mismo código y no deben estar repeidos. Por favor ingrese un nuevo código para el curso";

    public CourseException(Course course,String cause) {
        super("El curso "+course.getName()+" con código "+course.getCode()+cause);
    }

    public CourseException(String code,String cause) {
        super("El curso con código "+code+cause);
    }

    public CourseException(String message) {
        super(message);
    }
}
