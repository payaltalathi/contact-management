package com.evolenthealth.contactmanagement.dao;

import com.evolenthealth.contactmanagement.dto.ContactDTO;
import com.evolenthealth.contactmanagement.entity.ContactEntity;
import com.evolenthealth.contactmanagement.entity.Status;
import com.evolenthealth.contactmanagement.http.response.ContactResponse;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class ContactDomainEntityMapper {

    static ContactDTO fromEntity(ContactEntity contactEntity){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contactEntity.getId());
        contactDTO.setFirstName(contactEntity.getFirstName());
        contactDTO.setLastName(contactEntity.getLastName());
        contactDTO.setEmail(contactEntity.getEmail());
        contactDTO.setPhoneNumber(contactEntity.getPhoneNumber());
        contactDTO.setStatus(contactEntity.getStatus().toString());

        return contactDTO;
    }

    public static ContactResponse fromEntity(List<ContactEntity> contactEntityList) {
        return new ContactResponse(contactEntityList.stream().map(ContactDomainEntityMapper::fromEntity).collect(toList()));
    }

    public static ContactEntity toEntity(ContactDTO contactDTO){
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setFirstName(contactDTO.getFirstName());
        contactEntity.setLastName(contactDTO.getLastName());
        contactEntity.setEmail(contactDTO.getEmail());
        contactEntity.setPhoneNumber(contactDTO.getPhoneNumber());
        contactEntity.setStatus(Status.fromValue(contactDTO.getStatus()));

        return contactEntity;
    }
}
