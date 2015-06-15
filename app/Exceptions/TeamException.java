package Exceptions;

/**
 * Created by Asus on 09/06/2015.
 */
public class TeamException extends  Exception {
    public TeamException(String cause) {
        super("Este equipo"+cause);
    }
}
