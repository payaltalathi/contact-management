package com.evolenthealth.contactmanagement.http.response;

import com.evolenthealth.contactmanagement.dto.ContactDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactResponse {

    private List<ContactDTO> contactList;
}
