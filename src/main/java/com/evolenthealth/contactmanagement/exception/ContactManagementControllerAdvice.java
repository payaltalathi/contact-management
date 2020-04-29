package com.evolenthealth.contactmanagement.exception;

import com.evolenthealth.contactmanagement.http.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

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
    public ResponseEntity<List<ErrorResponse>> handleValidationException(MethodArgumentNotValidException ex) {
        log.error("exception Occurred: ", ex);

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

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalException(Exception ex) {
        log.error("Exception Occurred: ", ex);

        ErrorResponse errorResponse = buildErrorDetails(ex.getMessage(), "", String.valueOf(INTERNAL_SERVER_ERROR.value()));

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorResponse);
    }


    private ErrorResponse buildErrorDetails(String errorMessage, String fieldName, String status) {
        return ErrorResponse.builder().message(errorMessage).field(fieldName).status(status).timestamp(ZonedDateTime.now()).build();
    }
}
