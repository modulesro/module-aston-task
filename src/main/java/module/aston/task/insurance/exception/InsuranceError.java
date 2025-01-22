package module.aston.task.insurance.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * general error object used to deliver error info from insurance API
 *
 * @param httpCode
 * @param timestamp
 * @param message
 */
public record InsuranceError(

        HttpStatus httpCode,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime timestamp,
        String message

) {
}
