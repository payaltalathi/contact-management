package com.evolenthealth.contactmanagement.http;

import lombok.*;

import java.time.ZonedDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ErrorResponse {

    private String status;
    private String message;
    private ZonedDateTime timestamp;

}

