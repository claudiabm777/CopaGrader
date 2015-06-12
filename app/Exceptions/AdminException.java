package Exceptions;

import models.Activity;
import models.Admin;
import models.Course;
import models.Semester;

/**
 * Created by Asus on 07/06/2015.
 */
public class AdminException extends Exception {

    public final static String CODE_REPEATED ="Ya existe un administrador con este mismo email y no deben estar repeidos. Por favor ingrese un nuevo email para este.";

    public AdminException(Admin admin,String cause) {
        super("El administrador "+admin.getNames()+" "+admin.getLastNames()+" con email "+admin.getEmail()+cause);
    }

    public AdminException(String email,String cause) {
        super("El administrador con email "+email+cause);
    }

    public AdminException(String message) {
        super(message);
    }

    public AdminException(Course course,String idAdmin,String cause){
        super("El administrador con email "+idAdmin+cause+"en el curso "+course.getName()+" con código "+course.getCode()+".");
    }

    public AdminException(Activity activity,String idAdmin,String cause){
        super("El administrador con email "+idAdmin+cause+"en la actividad "+activity.getName()+".");
    }

}
