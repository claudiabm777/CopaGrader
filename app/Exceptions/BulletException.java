package Exceptions;

/**
 * Created by Asus on 09/06/2015.
 */
public class BulletException extends Exception {
    public BulletException(String cause) {
        super("Este apartado"+cause);
    }
}
