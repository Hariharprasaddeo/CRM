package com.zohoCRM;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

@SpringBootApplication
public class ZohoCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZohoCrmApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}



//	@Value("${spring.mail.host}")
//	private String host;
//
//	@Value("${spring.mail.port}")
//	private int port;
//
//	@Value("${spring.mail.username}")
//	private String username;
//
//	@Value("${spring.mail.password}")
//	private String password;

//	@Bean
//	public JavaMailSender javaMailSender() {
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		mailSender.setHost(host);
//		mailSender.setPort(port);
//		mailSender.setUsername(username);
//		mailSender.setPassword(password);
//
//		// Additional properties can be set as needed
//		// For example, you might want to set properties for TLS or SSL
//		// mailSender.setJavaMailProperties(getMailProperties());
//
//		 return mailSender;
//	}

}

