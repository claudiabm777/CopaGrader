package Exceptions;

/**
 * Created by Asus on 09/06/2015.
 */
public class StudentException extends  Exception {

    public StudentException(String cause) {
        super("El estudiante"+cause);
    }
}
