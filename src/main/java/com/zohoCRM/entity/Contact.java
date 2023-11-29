package com.zohoCRM.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    private String cid;
    private String firstName;
    private String lastName;
    private String email;
    private long mobile;
    private String address;
    private String company;
    private String designation;
    private String leadType;
    private String note;
}
