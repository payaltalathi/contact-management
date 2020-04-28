package com.evolenthealth.contactmanagement.service;

import com.evolenthealth.contactmanagement.http.response.ContactPostResponse;
import com.evolenthealth.contactmanagement.http.request.ContactRequest;
import com.evolenthealth.contactmanagement.http.response.ContactResponse;

public interface ContactService {

    ContactResponse getContactList();

    ContactPostResponse addContact(ContactRequest contactRequest);

    void updateContact(ContactRequest contactRequest, Integer id);

    void deleteContact(Integer id);
}
