package proiect.Exceptions;

public class UserNotFound extends BaseRuntimeException {

    public UserNotFound(final String message) {
        super(message);
    }
}
