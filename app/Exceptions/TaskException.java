package Exceptions;

/**
 * Created by Asus on 09/06/2015.
 */
public class TaskException extends Exception {
    public TaskException(String cause) {
        super("Este punto"+cause);
    }

}
