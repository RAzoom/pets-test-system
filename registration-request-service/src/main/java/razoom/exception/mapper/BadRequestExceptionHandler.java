package razoom.exception.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.io.UnsupportedEncodingException;

@RestControllerAdvice
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<byte[]> handle(RuntimeException ex) throws UnsupportedEncodingException {
        // fixme fucking encode. WTF?! Why not UTF?! WHERE U GET FUCKING WINDOWS ENCODE ?!
        return new ResponseEntity<>(ex.getMessage().getBytes("CP1251"), HttpStatus.BAD_REQUEST);
    }
}