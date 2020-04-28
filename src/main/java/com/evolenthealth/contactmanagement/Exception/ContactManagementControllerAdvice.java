package com.evolenthealth.contactmanagement.Exception;

import com.evolenthealth.contactmanagement.http.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ContactManagementControllerAdvice {

@ExceptionHandler
public ResponseEntity<ErrorResponse> handleException(ContactNotFoundException ex) {
    log.error("Contact details not found: {}", ex.getMessage());

    ErrorResponse errorsResponse = new ErrorResponse();
    errorsResponse.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
    errorsResponse.setMessage(ex.getMessage());
    errorsResponse.setTimestamp(ZonedDateTime.now());

    return new ResponseEntity<>(errorsResponse, HttpStatus.NOT_FOUND);
}

@ExceptionHandler(InvalidFormatException.class)
public ResponseEntity<ErrorResponse> handleValidationException(InvalidFormatException ex) {
    log.error("Validation Exception: {}", ex.getMessage());

    ErrorResponse errorsResponse = new ErrorResponse();
    errorsResponse.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
    errorsResponse.setMessage("Invalid Status Field:"+ex.getMessage().substring(ex.getMessage().indexOf(":")+1, ex.getMessage().indexOf("]")+1));
    errorsResponse.setTimestamp(ZonedDateTime.now());

    return new ResponseEntity<>(errorsResponse, HttpStatus.BAD_REQUEST);

}

}
