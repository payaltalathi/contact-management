package com.evolenthealth.contactmanagement.http.request;

import com.evolenthealth.contactmanagement.Validator.ValidStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    @ValidStatus
    private String status;
}
