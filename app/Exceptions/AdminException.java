package Exceptions;

import models.Admin;

/**
 * Created by Asus on 07/06/2015.
 */
public class AdminException extends Exception {

    public final static String NOT_FOUND_EMAIL="No se encontro el administrador con el email especificado.";
    public final static String CODE_REPEATED ="Ya existe un administrador con este mismo email y no deben estar repeidos. Por favor ingrese un nuevo email para el curso";

    public AdminException(Admin admin,String cause) {
        super("El administrador "+admin.getNames()+" "+admin.getLastNames()+" con email "+admin.getEmail()+cause);
    }

    public AdminException(String email,String cause) {
        super("El administrador con email "+email+cause);
    }

    public AdminException(String message) {
        super(message);
    }

}
