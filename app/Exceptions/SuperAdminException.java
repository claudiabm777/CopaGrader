package Exceptions;


import models.SuperAdmin;

/**
 * Created by Asus on 06/06/2015.
 */
public class SuperAdminException extends Exception {
    public final static String NOT_FOUND_EMAIL="No se encontro el administrador con el email especificado.";
    public final static String CODE_REPEATED ="Ya existe un administrador con este mismo email y no deben estar repeidos. Por favor ingrese un nuevo email para el curso";

    public SuperAdminException(SuperAdmin superAdmin,String cause) {
        super("El administrador "+superAdmin.getNames()+" "+superAdmin.getLastNames()+" con email "+superAdmin.getEmail()+cause);
    }

    public SuperAdminException(String email,String cause) {
        super("El administrador con email "+email+cause);
    }

    public SuperAdminException(String message) {
        super(message);
    }

}
