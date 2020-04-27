package com.evolenthealth.contactmanagement.http;

import com.evolenthealth.contactmanagement.entity.Status;
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

    private Status status;
}
