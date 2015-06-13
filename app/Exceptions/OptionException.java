package Exceptions;

/**
 * Created by Asus on 09/06/2015.
 */
public class OptionException extends Exception {

    public OptionException(String cause) {
        super("Esta opción"+cause);
    }
}
