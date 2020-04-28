package com.evolenthealth.contactmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String status;
}
