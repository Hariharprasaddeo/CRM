package com.zohoCRM.service.Impl;

import com.zohoCRM.entity.Contact;
import com.zohoCRM.entity.Lead;
import com.zohoCRM.exception.LeadExists;
import com.zohoCRM.payload.ContactDto;
import com.zohoCRM.repository.ContactRepository;
import com.zohoCRM.repository.LeadRepository;
import com.zohoCRM.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ContactDto createContact(String leadId) {
        Lead lead = leadRepository.findById(leadId).orElseThrow(
                () -> new LeadExists("Lead is not exists with this id: " + leadId)
        );
        Contact contact = convertLeadToContact(lead);
        String cid = UUID.randomUUID().toString();
        contact.setCid(cid);

        Contact savedContact = contactRepository.save(contact);

        if (savedContact.getCid() != null){
            leadRepository.deleteById(lead.getLid());
        }

        ContactDto dto = mapToDto(savedContact);

        return dto;
    }

    Contact convertLeadToContact(Lead lead){
        Contact contact = modelMapper.map(lead, Contact.class);
        return contact;
    }
    ContactDto mapToDto(Contact contact){
        ContactDto dto = modelMapper.map(contact, ContactDto.class);
        return dto;

    }

}
