package com.evolenthealth.contactmanagement.controller;

import com.evolenthealth.contactmanagement.service.ContactService;
import com.evolenthealth.contactmanagement.http.response.ContactPostResponse;
import com.evolenthealth.contactmanagement.http.request.ContactRequest;
import com.evolenthealth.contactmanagement.http.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;


@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class ContactController {

    private final ContactService contactService;

    @GetMapping(value = "/contacts")
    public ResponseEntity<ContactResponse> getContacts() {
        ContactResponse contactList = contactService.getContactList();
        return ResponseEntity.ok().body(contactList);
    }

    @PostMapping(value = "/contacts")
    public ResponseEntity<ContactPostResponse> addContact(@Valid @RequestBody ContactRequest request) {
        ContactPostResponse id = contactService.addContact(request);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<String> updateContact( @Valid @RequestBody ContactRequest request, @PathVariable Integer id) {
        contactService.updateContact(request, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Integer id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
