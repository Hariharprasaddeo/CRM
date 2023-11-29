package com.zohoCRM.repository;

import com.zohoCRM.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeadRepository extends JpaRepository<Lead, String> {
    Optional<Lead> findByEmail(String email);
    Optional<Lead> findByMobile(long mobile);


    Boolean existsByEmail(String email);
    Boolean existsByMobile(long mobile);

}
