package com.evolenthealth.contactmanagement.Exception;

import com.evolenthealth.contactmanagement.http.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ContactManagementControllerAdvice  {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ContactNotFoundException ex) {
        log.error("Contact details not found: {}", ex.getMessage());

        ErrorResponse errorsResponse = buildErrorDetails(ex.getMessage(), "id", String.valueOf(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(errorsResponse, HttpStatus.NOT_FOUND);

      }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> validationException(MethodArgumentNotValidException ex) {
        log.error("Exception Occurred: ", ex);

        List<ErrorResponse> errors = new LinkedList<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                        error -> {
                            String fieldName = ((FieldError) error).getField();
                            String errorMessage = error.getDefaultMessage();
                            String status = error.getCode();
                            errors.add(
                                    buildErrorDetails(
                                            errorMessage, fieldName, "400 BAD_REQUEST"));
                        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
      }

    private ErrorResponse buildErrorDetails(String errorMessage, String fieldName, String status) {
        return ErrorResponse.builder().message(errorMessage).field(fieldName).status(status).timestamp(ZonedDateTime.now()).build();
    }
}
