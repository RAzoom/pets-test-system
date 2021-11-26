package razoom.exception.mapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import razoom.exception.pojo.BadRequestMessage;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Мапит человеческую ошибку структуры запроса
 *
 * @author razoom
 */
@RestControllerAdvice
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<BadRequestMessage> handle(ConstraintViolationException ex) {
        BadRequestMessage errorMessage = new BadRequestMessage();
        for (ConstraintViolation<?> errorRow : ex.getConstraintViolations()) {
            errorMessage.add(errorRow.getPropertyPath().toString(), errorRow.getMessage());
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BadRequestMessage errorMessage = new BadRequestMessage();
        for (ObjectError el : ex.getAllErrors()) {
            errorMessage.add(el.getCode(), el.getDefaultMessage());
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}