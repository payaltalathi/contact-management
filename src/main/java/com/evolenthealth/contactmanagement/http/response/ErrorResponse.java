package com.evolenthealth.contactmanagement.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private String status;
    private String message;
    private ZonedDateTime timestamp;
    private String field;

}

