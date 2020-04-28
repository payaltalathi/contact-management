package com.evolenthealth.contactmanagement.Service;

import com.evolenthealth.contactmanagement.http.ContactPostResponse;
import com.evolenthealth.contactmanagement.http.ContactRequest;
import com.evolenthealth.contactmanagement.http.ContactResponse;

public interface ContactService {

    ContactResponse getContactList();

    ContactPostResponse addContact(ContactRequest contactRequest);

    void updateContact(ContactRequest contactRequest, Integer id);

    void deleteContact(Integer id);
}
