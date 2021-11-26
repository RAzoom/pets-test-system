package razoom.exception.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import razoom.exception.pojo.InternalErrorMessage;

/**
 * Закрывает возможность выпадение стектрейса из сервиса целиком.
 *
 * @author razoom
 */
@RestControllerAdvice
public class InternalErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Throwable.class})
    protected ResponseEntity<InternalErrorMessage> handle(RuntimeException ex) {
        //todo добавить логирование
        return new ResponseEntity<>(new InternalErrorMessage("Внутренняя ошибка работы"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}