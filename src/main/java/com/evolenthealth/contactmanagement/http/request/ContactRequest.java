package com.evolenthealth.contactmanagement.http.request;

import com.evolenthealth.contactmanagement.validator.ValidStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {

    @NotEmpty
    private String firstName;

    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "(\\+91|0)[0-9]{10}")
    private String phoneNumber;

    @ValidStatus
    private String status;
}
