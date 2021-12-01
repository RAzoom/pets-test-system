package razoom.exception.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import razoom.exception.ApplicationException;
import razoom.exception.pojo.InternalErrorMessage;

@RestControllerAdvice
public class BusinessLogicExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ApplicationException.class})
    protected ResponseEntity<InternalErrorMessage> handle(RuntimeException ex) {
        return new ResponseEntity<>(new InternalErrorMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}