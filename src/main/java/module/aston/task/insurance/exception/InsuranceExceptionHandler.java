package module.aston.task.insurance.exception;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "module.aston.task.insurance")
public class InsuranceExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(InsuranceExceptionHandler.class);

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<InsuranceError> handleConstraintViolationException(ConstraintViolationException e) {
        return handleException(e, HttpStatus.BAD_REQUEST, "handleConstraintViolationException");
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<InsuranceError> handleEntityNotFoundException(EntityNotFoundException e) {
        return handleException(e, HttpStatus.NOT_FOUND, "handleEntityNotFoundException");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<InsuranceError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return handleException(e, HttpStatus.BAD_REQUEST, "handleMethodArgumentNotValidException");
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<InsuranceError> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return handleException(e, HttpStatus.BAD_REQUEST, "handleHttpMessageNotReadableException");
    }

    private ResponseEntity<InsuranceError> handleException(Exception e, HttpStatus httpStatus, String logMessage) {
        log.error("{}: {}", logMessage, e.getMessage());
        final var error = new InsuranceError(httpStatus, LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(error, httpStatus);
    }

}
