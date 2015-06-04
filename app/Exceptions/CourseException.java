package Exceptions;

/**
 * Created by Asus on 04/06/2015.
 */
public class CourseException extends Exception {
    public CourseException() {
    }

    public CourseException(String message) {
        super(message);
    }

    public CourseException(Throwable cause) {
        super(cause);
    }

    public CourseException(String message, Throwable cause) {
        super(message, cause);
    }
}
