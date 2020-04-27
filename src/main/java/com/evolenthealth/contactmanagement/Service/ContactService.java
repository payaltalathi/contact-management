package com.evolenthealth.contactmanagement.Service;

import com.evolenthealth.contactmanagement.dto.ContactDTO;
import com.evolenthealth.contactmanagement.http.ContactRequest;
import com.evolenthealth.contactmanagement.http.ContactResponse;

import java.util.List;

public interface ContactService {

    ContactResponse getContactList();

    void addContact(ContactRequest contactRequest);

    void updateContact(ContactRequest contactRequest, Integer id);

    void deleteContact(Integer id);
}
