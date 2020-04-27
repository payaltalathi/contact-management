package com.evolenthealth.contactmanagement.controller;

import com.evolenthealth.contactmanagement.Service.ContactService;
import com.evolenthealth.contactmanagement.dto.ContactDTO;
import com.evolenthealth.contactmanagement.http.ContactRequest;
import com.evolenthealth.contactmanagement.http.ContactResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/contacts")
    public ResponseEntity<ContactResponse> getContacts() {
        ContactResponse contactList = contactService.getContactList();
        return ResponseEntity.ok().body(contactList);
    }

    @PostMapping("/contacts")
    public ResponseEntity<String> addContact(@Valid @RequestBody ContactRequest request) {
        contactService.addContact(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<String> updateContact(@Valid @RequestBody ContactRequest request, @PathVariable Integer id) {
        contactService.updateContact(request, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Integer id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }
}
