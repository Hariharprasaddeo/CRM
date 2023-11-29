package com.zohoCRM.controller;

import com.zohoCRM.payload.ContactDto;
import com.zohoCRM.payload.EmailDto;
import com.zohoCRM.repository.ContactRepository;
import com.zohoCRM.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactService contactService;

    @PostMapping("{leadId}")
    public ResponseEntity<ContactDto> saveContact(@PathVariable String leadId){
        ContactDto dto = contactService.createContact(leadId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

//    @PostMapping
//    public ResponseEntity<ContactDto> saveContact(@RequestParam("leadId") String leadId){
//        ContactDto dto = contactService.createContact(leadId);
//        return new ResponseEntity<>(dto, HttpStatus.CREATED);
//    }


}
