package com.zohoCRM.service.Impl;

import com.zohoCRM.entity.Email;
import com.zohoCRM.exception.LeadExists;
import com.zohoCRM.payload.EmailDto;
import com.zohoCRM.repository.EmailRepository;
import com.zohoCRM.repository.LeadRepository;
import com.zohoCRM.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;
import java.util.UUID;

/*
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
*/


@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmailRepository emailRepository;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public EmailDto sendMail(EmailDto emailDto) {
        leadRepository.findByEmail(emailDto.getTo()).orElseThrow(
                () -> new LeadExists("Email is not found/ registered: " + emailDto.getTo())
        );
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(emailDto.getTo());
        mailMessage.setSubject(emailDto.getSubject());
        mailMessage.setText(emailDto.getMessage());

        javaMailSender.send(mailMessage);

        Email email = mapToEntity(emailDto);
        String eid = UUID.randomUUID().toString();
        email.setEid(eid);

        Email sentEmail = emailRepository.save(email);

        return mapToDto(sentEmail);

    }

    Email mapToEntity(EmailDto emailDto) {
        Email email = modelMapper.map(emailDto, Email.class);
        return email;
    }

    EmailDto mapToDto(Email email) {
        EmailDto dto = modelMapper.map(email, EmailDto.class);
        return dto;
    }
}

//-----------------------------Method 2 for sending mail--------------------------------------------------------------//

//        @Autowired
//        JavaMailSenderImpl sender = new JavaMailSenderImpl();
//        sender.setHost(host);
//
//        MimeMessage message = sender.createMimeMessage();
//
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        try {
//            helper.setFrom(from);
//            helper.setTo(emailDto.getTo());
//            helper.setSubject(emailDto.getSubject());
//            helper.setText(emailDto.getMessage());
//
//            sender.send(helper.getMimeMessage());
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }

//--------------------------------------------------------------------------------------------------------------------//
