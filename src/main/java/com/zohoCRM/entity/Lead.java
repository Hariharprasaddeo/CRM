package com.zohoCRM.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "leads")
@Data
public class Lead {
    @Id
    private String lid;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "mobile", nullable = false, unique = true)
    private long mobile;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "company", nullable = false)
    private String company;
    @Column(name = "designation", nullable = false)
    private String designation;
    @Column(name = "lead_type")
    private String leadType;
    @Column(name = "note")
    private String note;

}
