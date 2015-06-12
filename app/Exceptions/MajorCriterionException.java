package Exceptions;

/**
 * Created by Asus on 09/06/2015.
 */
public class MajorCriterionException extends Exception {
    public MajorCriterionException(String cause) {
        super("Este criterio mayor"+cause);
    }
}
