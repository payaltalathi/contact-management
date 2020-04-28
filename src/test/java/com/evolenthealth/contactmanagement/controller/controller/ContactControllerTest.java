package com.evolenthealth.contactmanagement.controller.controller;

import com.evolenthealth.contactmanagement.service.ContactService;
import com.evolenthealth.contactmanagement.controller.ContactController;
import com.evolenthealth.contactmanagement.dto.ContactDTO;
import com.evolenthealth.contactmanagement.http.request.ContactRequest;
import com.evolenthealth.contactmanagement.http.response.ContactPostResponse;
import com.evolenthealth.contactmanagement.http.response.ContactResponse;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    private static final Integer ID = 1;

    private static final String CONTACT_REQUEST = "{"
            + "\"firstName\":\"Payal\",\"lastName\":\"Talathi\","
            + "\"email\":\"payaltalathi@gmail.com\","
            + "\"phoneNumber\":\"8080274116\","
            +  "\"status\":\"active\"}";

    private static final String UPDATE_CONTACT_REQUEST = "{"
            + "\"firstName\":\"Yash\",\"lastName\":\"Talathi\","
            + "\"email\":\"yashtalathi@gmail.com\","
            + "\"phoneNumber\":\"8080274116\","
            +  "\"status\":\"inactive\"}";


    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    @Autowired
    private MockMvc mvc;

    @Before
    public void init() {
        mvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    public void testAddContact() throws Exception {

        ContactPostResponse contactPostResponse = new ContactPostResponse(ID);

        when(contactService.addContact(any(ContactRequest.class))).thenReturn(contactPostResponse);

        mvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CONTACT_REQUEST))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }


    @Test
    public void testRetrieveContactList() throws Exception {

        List<ContactDTO> contactList = new ArrayList<>();
        ContactDTO contactDTO1 = new ContactDTO(1, "Yash", "Talathi", "yashtalathi1998@gmail.com", "9823358182", "Active");
        ContactDTO contactDTO2 = new ContactDTO(2, "Payal", "Talathi", "payaltalathi@gmail.com", "8080274116", "Inactive");
        contactList.add(contactDTO1);
        contactList.add(contactDTO2);

        ContactResponse contactResponse = new ContactResponse(contactList);

        when(contactService.getContactList()).thenReturn(contactResponse);

        mvc.perform(get("/contacts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.contactList").exists())
                .andExpect(jsonPath("$.contactList[0].status", Matchers.is("Active")))
                .andExpect(jsonPath("$.contactList[0].phoneNumber", Matchers.is("9823358182")))
                .andExpect(jsonPath("$.contactList[1].status", Matchers.is("Inactive")));

    }

    @Test
    public void testUpdateContact() throws Exception {

        mvc.perform(put("/contacts/{id}", ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UPDATE_CONTACT_REQUEST))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteContact() throws Exception {

        mvc.perform(delete("/contacts/{id}", ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

