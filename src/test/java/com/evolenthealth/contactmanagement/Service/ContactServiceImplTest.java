package com.evolenthealth.contactmanagement.Service;

import com.evolenthealth.contactmanagement.exception.ContactNotFoundException;
import com.evolenthealth.contactmanagement.service.ContactServiceImpl;
import com.evolenthealth.contactmanagement.dao.ContactRepository;
import com.evolenthealth.contactmanagement.entity.ContactEntity;
import com.evolenthealth.contactmanagement.entity.Status;
import com.evolenthealth.contactmanagement.http.request.ContactRequest;
import com.evolenthealth.contactmanagement.http.response.ContactPostResponse;
import com.evolenthealth.contactmanagement.http.response.ContactResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    @Test
    public void shouldReturnContactList(){

        ContactEntity contactEntity = createContactEntity(Status.valueOf("ACTIVE"));

        when(contactRepository.findAll()).thenReturn(Collections.singletonList(contactEntity));

        ContactResponse contactResponse = contactService.getContactList();

        assertThat(contactResponse.getContactList().get(0).getPhoneNumber()).isEqualTo("9823358182");
        assertThat(contactResponse.getContactList().get(0).getStatus()).isEqualTo(Status.ACTIVE.name());

    }

    @Test
    public void shouldAddContact(){

        ContactRequest request = createContactRequest("INACTIVE");
        ContactEntity contactEntity = createContactEntity(Status.valueOf("INACTIVE"));
        when(contactRepository.save(any(ContactEntity.class))).thenReturn(contactEntity);
        ContactPostResponse contactPostResponse = contactService.addContact(request);

        assertThat(contactPostResponse).isNotNull();
        verify(contactRepository, times(1)).save(any(ContactEntity.class));

    }

    @Test
    public void shouldUpdateContact(){
        Integer id = 1;
        ContactRequest request = createContactRequest("Active");
        ContactEntity contactEntity = createContactEntity(Status.valueOf("ACTIVE"));
        when(contactRepository.findById(id)).thenReturn(Optional.of(contactEntity));
        contactService.updateContact(request, id);

        verify(contactRepository, times(1)).save(any(ContactEntity.class));

    }

    @Test
    public void shouldThrowExceptionForUpdateContact(){
        Integer id = 99;
        ContactRequest request = createContactRequest("Inactive");
        when(contactRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() ->contactService.updateContact(request, id)).isInstanceOf(ContactNotFoundException.class)
                .hasMessage("No contact records found for the given id: 99");

    }

    @Test
    public void shouldDeleteContact(){
        Integer id = 1;
        ContactEntity contactEntity = createContactEntity(Status.valueOf("ACTIVE"));
        when(contactRepository.findById(id)).thenReturn(Optional.of(contactEntity));
        contactService.deleteContact(id);

        verify(contactRepository, times(1)).deleteById(id);

    }

    @Test
    public void shouldThrowExceptionForDeleteContact(){
        Integer id = 99;
        when(contactRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() ->contactService.deleteContact(id)).isInstanceOf(ContactNotFoundException.class)
                .hasMessage("No contact records found for the given id: 99");

    }

    private ContactEntity createContactEntity(Status status){
        return new ContactEntity(1,"Yash", "Talathi", "yashtalathi1998@gmail.com", "9823358182", status);
    }

    private ContactRequest createContactRequest(String status){
        return new ContactRequest("Yash", "Talathi", "yashtalathi1998@gmail.com", "9823358182", status);
    }

}
