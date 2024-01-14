package proiect.Micunelte;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import proiect.Exceptions.BaseRuntimeException;
import proiect.Exceptions.ErrorResponse;
import proiect.Exceptions.UserNotFound;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler({UserNotFound.class})
    public ResponseEntity<ErrorResponse> handle(final BaseRuntimeException exception) {
        final var response = new ErrorResponse(NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(response);
}
}