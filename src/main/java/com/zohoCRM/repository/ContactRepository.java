package com.zohoCRM.repository;

import com.zohoCRM.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, String> {


}
