package com.evolenthealth.contactmanagement.Service;

import com.evolenthealth.contactmanagement.dao.ContactRepository;
import com.evolenthealth.contactmanagement.dto.ContactDTO;
import com.evolenthealth.contactmanagement.entity.ContactEntity;
import com.evolenthealth.contactmanagement.http.ContactRequest;
import com.evolenthealth.contactmanagement.http.ContactResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.evolenthealth.contactmanagement.dao.ContactDomainEntityMapper.fromEntity;
import static com.evolenthealth.contactmanagement.dao.ContactDomainEntityMapper.toEntity;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;}

    @Override
    public ContactResponse getContactList() {
        return fromEntity(contactRepository.findAll());
    }

    @Override
    public void addContact(ContactRequest contactRequest){
        ContactEntity contactEntity = toEntity(convertRequestToDTO(contactRequest));
        contactRepository.save(contactEntity);
    }

    @Override
    public void updateContact(ContactRequest contactRequest, Integer id){
        Optional<ContactEntity> contactEntity = contactRepository.findById(id);
        if (contactEntity.isPresent()) {
            ContactEntity contactEntity1 = contactEntity.get();
            contactEntity1.setFirstName(contactRequest.getFirstName());
            contactEntity1.setLastName(contactRequest.getLastName());
            contactEntity1.setEmail(contactRequest.getEmail());
            contactEntity1.setPhoneNumber(contactRequest.getPhoneNumber());
            contactEntity1.setStatus(contactRequest.getStatus());
            contactRepository.save(contactEntity1);
        } else {
           // throw new ValidationException("No entry found for given Id " + indexRateRequest.getId() + " and Date " + indexRateRequest.getEffectiveDate());
        }
    }

    @Override
    public void deleteContact(Integer id) {
        contactRepository.deleteById(id);
    }

    private ContactDTO convertRequestToDTO(ContactRequest request) {
        ContactDTO contactDTO = new ContactDTO();
      //  contactDTO.setId(0);
        contactDTO.setFirstName(request.getFirstName());
        contactDTO.setLastName(request.getLastName());
        contactDTO.setEmail(request.getEmail());
        contactDTO.setPhoneNumber(request.getPhoneNumber());
        contactDTO.setStatus(request.getStatus());

        return contactDTO;
    }
}
