package razoom.exception;

/**
 * Стандартная ошибка приложения. Кидаем в любой непонятной ситуации
 *
 * @author razoom
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable ex) {
        super(message, ex);
    }
}
