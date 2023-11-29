package com.zohoCRM.controller;

import com.zohoCRM.exception.LeadExists;
import com.zohoCRM.payload.EmailDto;
import com.zohoCRM.repository.LeadRepository;
import com.zohoCRM.service.EmailService;
import com.zohoCRM.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<EmailDto> sendMail(@RequestBody EmailDto emailDto) {

        EmailDto dto = emailService.sendMail(emailDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

}
