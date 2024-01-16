package proiect.Exceptions;

public class Unauthorised extends BaseRuntimeException {

    public Unauthorised(final String message) {
        super(message);
    }
}